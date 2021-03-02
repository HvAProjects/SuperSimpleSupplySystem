package nl.jed.supersimplesupplysystem.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import nl.jed.supersimplesupplysystem.util.GeneralUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.OidcUserInfo;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;

import java.util.Collection;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
public class LocalUser extends User implements OidcUser {

    /**
     *
     */
    private static final long serialVersionUID = -2845160792248762779L;
    @Getter
    private final OidcIdToken idToken;
    @Getter
    private final OidcUserInfo userInfo;
    @Getter @Setter
    private Map<String, Object> attributes;
    @Getter
    private final nl.jed.supersimplesupplysystem.models.User user;

    public LocalUser(final String userID, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired,
                     final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities, final nl.jed.supersimplesupplysystem.models.User user) {
        this(userID, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities, user, null, null);
    }

    public LocalUser(final String userID, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired,
                     final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities, final nl.jed.supersimplesupplysystem.models.User user, OidcIdToken idToken,
                     OidcUserInfo userInfo) {
        super(userID, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.user = user;
        this.idToken = idToken;
        this.userInfo = userInfo;
    }

    public static LocalUser create(nl.jed.supersimplesupplysystem.models.User user, Map<String, Object> attributes, OidcIdToken idToken, OidcUserInfo userInfo) {
        LocalUser localUser = new LocalUser(user.getEmail(), user.getPassword(), user.isEnabled(), true, true, true, GeneralUtils.buildSimpleGrantedAuthorities(user.getRoles()),
                user, idToken, userInfo);
        localUser.setAttributes(attributes);
        return localUser;
    }

    @Override
    public String getName() {
        return this.user.getDisplayName();
    }

    @Override
    public Map<String, Object> getClaims() {
        return this.attributes;
    }
}

package nl.jed.supersimplesupplysystem.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.experimental.UtilityClass;
import nl.jed.supersimplesupplysystem.dto.LocalUser;
import nl.jed.supersimplesupplysystem.dto.SocialProvider;
import nl.jed.supersimplesupplysystem.dto.UserInfo;
import nl.jed.supersimplesupplysystem.models.Role;
import nl.jed.supersimplesupplysystem.models.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@UtilityClass
public class GeneralUtils {

    public List<SimpleGrantedAuthority> buildSimpleGrantedAuthorities(final Set<Role> roles) {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
        return authorities;
    }

    public SocialProvider toSocialProvider(String providerId) {
        for (SocialProvider socialProvider : SocialProvider.values()) {
            if (socialProvider.getProviderType().equals(providerId)) {
                return socialProvider;
            }
        }
        return SocialProvider.LOCAL;
    }

    public UserInfo buildUserInfo(LocalUser localUser) {
        List<String> roles = localUser.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
        User user = localUser.getUser();
        return new UserInfo(user.getId().toString(), user.getDisplayName(), user.getEmail(), roles);
    }
}

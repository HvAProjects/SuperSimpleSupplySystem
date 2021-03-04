package nl.jed.supersimplesupplysystem.security.oauth2.user;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

@AllArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class OAuth2UserInfo {
    @Getter(AccessLevel.PROTECTED)
    protected Map<String, Object> attributes;

    public abstract String getId();

    public abstract String getName();

    public abstract String getEmail();

    public abstract String getImageUrl();
}

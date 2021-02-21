package nl.jed.supersimplesupplysystem.dto;

import lombok.Value;

@Value
public class JwtAuthenticationResponse {
    String accessToken;
    UserInfo user;

    public JwtAuthenticationResponse(String accessToken, UserInfo user) {
        this.accessToken = accessToken;
        this.user = user;
    }
}

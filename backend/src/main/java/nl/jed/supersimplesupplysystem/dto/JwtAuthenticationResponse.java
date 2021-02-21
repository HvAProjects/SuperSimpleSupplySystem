package nl.jed.supersimplesupplysystem.dto;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Value;

@Value
@RequiredArgsConstructor
public class JwtAuthenticationResponse {
    @NonNull
    String accessToken;
    @NonNull
    UserInfo user;
}

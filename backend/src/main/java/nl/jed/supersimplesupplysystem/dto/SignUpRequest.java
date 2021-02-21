package nl.jed.supersimplesupplysystem.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.jed.supersimplesupplysystem.validator.PasswordMatches;

@Data
@PasswordMatches
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignUpRequest {

    private Long userID;

    private String providerUserId;

    @NotEmpty
    private String displayName;

    @NotEmpty
    private String email;

    private SocialProvider socialProvider;

    @Size(min = 6, message = "{Size.userDto.password}")
    private String password;

    @NotEmpty
    private String matchingPassword;

}

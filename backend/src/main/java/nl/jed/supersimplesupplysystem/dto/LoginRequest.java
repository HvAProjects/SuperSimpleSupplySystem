package nl.jed.supersimplesupplysystem.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank
    @Getter @Setter
    private String email;

    @NotBlank
    @Getter @Setter
    private String password;
}

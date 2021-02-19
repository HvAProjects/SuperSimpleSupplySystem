package nl.jed.supersimplesupplysystem.dto;

import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class LoginRequest {
    @NotBlank
    @Getter @Setter
    private String email;

    @NotBlank
    @Getter
    @Setter
    private String password;
}

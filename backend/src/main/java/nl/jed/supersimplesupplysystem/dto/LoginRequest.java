package nl.jed.supersimplesupplysystem.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
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

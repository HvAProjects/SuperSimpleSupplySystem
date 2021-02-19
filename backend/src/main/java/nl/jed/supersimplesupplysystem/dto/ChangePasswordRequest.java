package nl.jed.supersimplesupplysystem.dto;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private String oldPassword;
    private String newPassword;
    private String newPasswordConfirm;
    private String token;
}

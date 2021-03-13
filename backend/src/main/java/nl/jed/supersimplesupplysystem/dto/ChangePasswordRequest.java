package nl.jed.supersimplesupplysystem.dto;

import lombok.Data;

@Data
public class ChangePasswordRequest {
    private int userId;
    private String oldPassword;
    private String newPassword;
    private String token;
}

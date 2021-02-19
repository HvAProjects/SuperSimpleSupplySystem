package nl.jed.supersimplesupplysystem.dto;

import lombok.Value;

@Value
public class ApiResponse {
    private Boolean success;
    private String message;

    public ApiResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}

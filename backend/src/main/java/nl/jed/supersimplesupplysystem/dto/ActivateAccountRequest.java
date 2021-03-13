package nl.jed.supersimplesupplysystem.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class ActivateAccountRequest {
    String token;
}

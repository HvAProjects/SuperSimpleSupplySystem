package nl.jed.supersimplesupplysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Value;

import java.util.List;

@Value
@AllArgsConstructor
public class UserInfo {
    String id;
    String displayName;
    String email;
    List<String> roles;
}

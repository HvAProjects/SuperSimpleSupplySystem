package nl.jed.supersimplesupplysystem.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class UserInfo {
    String id;
    String displayName;
    String email;
    List<String> roles;
}

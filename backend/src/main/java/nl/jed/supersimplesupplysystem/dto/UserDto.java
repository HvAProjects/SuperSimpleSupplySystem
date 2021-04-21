package nl.jed.supersimplesupplysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class UserDto {
    private long id;
    private String name;
}

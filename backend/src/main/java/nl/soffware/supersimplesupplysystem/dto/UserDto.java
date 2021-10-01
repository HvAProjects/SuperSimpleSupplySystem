package nl.soffware.supersimplesupplysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.soffware.supersimplesupplysystem.models.User;

@AllArgsConstructor
@Data
public class UserDto {
    private long id;
    private String name;

    public UserDto(User user) {
        this.id = user.getId();
        this.name = user.getEmail();
    }
}

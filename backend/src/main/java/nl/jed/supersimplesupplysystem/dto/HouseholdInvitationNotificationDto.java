package nl.jed.supersimplesupplysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
public class HouseholdInvitationNotificationDto extends NotificationDto {
    private UserDto sender;
}

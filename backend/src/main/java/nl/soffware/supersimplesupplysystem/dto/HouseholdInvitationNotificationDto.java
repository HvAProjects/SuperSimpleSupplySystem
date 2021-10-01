package nl.soffware.supersimplesupplysystem.dto;

import lombok.Data;

@Data
public class HouseholdInvitationNotificationDto extends NotificationDto {
    private UserDto sender;
}

package nl.soffware.supersimplesupplysystem.models.notification;

import lombok.Data;
import nl.soffware.supersimplesupplysystem.dto.HouseholdInvitationNotificationDto;
import nl.soffware.supersimplesupplysystem.dto.NotificationDto;
import nl.soffware.supersimplesupplysystem.dto.UserDto;
import nl.soffware.supersimplesupplysystem.models.User;
import nl.soffware.supersimplesupplysystem.models.household.Household;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Data
@Entity
public class HouseholdInvitationNotification extends Notification {

    @OneToOne
    private Household household;

    @ManyToOne
    private User sender;

    public NotificationDto getNotificationDto() {
        HouseholdInvitationNotificationDto householdInvitationNotificationDto = new HouseholdInvitationNotificationDto();
        householdInvitationNotificationDto.setId(super.getId());
        householdInvitationNotificationDto.setSender(new UserDto(this.sender));
        householdInvitationNotificationDto.setDate(super.getDate());
        householdInvitationNotificationDto.setState(super.getState());
        householdInvitationNotificationDto.setNotificationType(super.getNotificationType());
        return householdInvitationNotificationDto;
    }

}

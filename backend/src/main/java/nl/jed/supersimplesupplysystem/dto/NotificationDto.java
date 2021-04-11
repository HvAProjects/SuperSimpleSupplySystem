package nl.jed.supersimplesupplysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.jed.supersimplesupplysystem.models.notification.NotificationState;
import nl.jed.supersimplesupplysystem.models.notification.NotificationType;

import java.util.Date;

@AllArgsConstructor
@Data
public class NotificationDto {
    private Long id;

    private UserDto sender;

    private Date date;

    private NotificationState state;

    private NotificationType notificationType;
}

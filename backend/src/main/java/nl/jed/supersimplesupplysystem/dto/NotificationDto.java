package nl.jed.supersimplesupplysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.jed.supersimplesupplysystem.models.notification.NotificationState;
import nl.jed.supersimplesupplysystem.models.notification.NotificationType;

import java.util.Date;

@Data
public abstract class NotificationDto {
    private Long id;

    private Date date;

    private NotificationState state;

    private NotificationType notificationType;
}

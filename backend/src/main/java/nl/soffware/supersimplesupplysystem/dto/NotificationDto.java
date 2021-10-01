package nl.soffware.supersimplesupplysystem.dto;

import lombok.Data;
import nl.soffware.supersimplesupplysystem.models.notification.NotificationState;
import nl.soffware.supersimplesupplysystem.models.notification.NotificationType;

import java.util.Date;

@Data
public abstract class NotificationDto {
    private Long id;

    private Date date;

    private NotificationState state;

    private NotificationType notificationType;
}

package nl.jed.supersimplesupplysystem.models.notification;

import lombok.Data;
import nl.jed.supersimplesupplysystem.dto.NotificationDto;
import nl.jed.supersimplesupplysystem.models.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public abstract class Notification implements Comparable<Notification> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Hier bewust geen directe relatie met de User entiteit, zodat
    // Gebruikers die nog geen account hebben ook al notificiaties kunnen krijgen
    // Wanneer er een account aangemaakt wordt door een dergelijke gebruiker krijgt hij daarna automatisch zijn notificatie
    private String userEmail;

    private Date date;

    private NotificationState state;

    private NotificationType notificationType;

    public abstract NotificationDto getNotificationDto();

    @Override
    public int compareTo(Notification o) {
        return this.getDate().compareTo(o.getDate());
    }
}

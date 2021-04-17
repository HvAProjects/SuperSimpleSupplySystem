package nl.jed.supersimplesupplysystem.models.notification;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.jed.supersimplesupplysystem.dto.NotificationDto;
import nl.jed.supersimplesupplysystem.models.User;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Notification implements Comparable<Notification> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    // Hier bewust geen directe relatie met de User entiteit, zodat
    // Gebruikers die nog geen account hebben ook al notificiaties kunnen krijgen
    // Wanneer er een account aangemaakt wordt door een dergelijke gebruiker krijgt hij daarna automatisch zijn notificatie
    private String userEmail;

    @ManyToOne
    private User sender;

    private Date date;

    private NotificationState state;

    private NotificationType notificationType;

    public NotificationDto getNotificationDto() {
        return new NotificationDto(this.id, this.sender.toUserDto(), this.date, this.state, this.notificationType);
    }

    @Override
    public int compareTo(Notification o) {
        return this.getDate().compareTo(o.getDate());
    }
}

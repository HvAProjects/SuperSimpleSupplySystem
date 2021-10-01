package nl.soffware.supersimplesupplysystem.models.notification;

import lombok.*;
import nl.soffware.supersimplesupplysystem.dto.NotificationDto;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public abstract class Notification implements Comparable<Notification> {

    @Id
    private Long id;

    private String userEmail;

    private Date date;

    private NotificationState state;

    private NotificationType notificationType;

    public abstract NotificationDto getNotificationDto();

    @Override
    public int compareTo(Notification o) {
        return this.getDate().compareTo(o.getDate());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Notification that = (Notification) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 436862861;
    }
}

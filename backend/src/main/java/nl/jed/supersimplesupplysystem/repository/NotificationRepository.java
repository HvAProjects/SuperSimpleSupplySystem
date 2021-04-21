package nl.jed.supersimplesupplysystem.repository;

import nl.jed.supersimplesupplysystem.models.notification.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> getNotificationsByUserEmail(String emailAddress);
    Notification getById(long id);
}

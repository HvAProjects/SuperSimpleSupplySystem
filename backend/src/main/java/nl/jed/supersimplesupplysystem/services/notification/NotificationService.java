package nl.jed.supersimplesupplysystem.services.notification;

import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.models.notification.Notification;
import org.springframework.beans.PropertyValues;

import java.util.List;

public interface NotificationService {
    void inviteUserToHousehold(String emailAddress, long householdId, User sender);
    List<Notification> getNotifications(String email);
    List<Notification> setNotificationsSeen(User user);
    List<Notification> acceptOrDeclineHouseholdInvitation(long notificationId, User user, boolean accept);
}

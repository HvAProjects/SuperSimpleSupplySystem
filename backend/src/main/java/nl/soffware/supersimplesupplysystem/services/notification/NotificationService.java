package nl.soffware.supersimplesupplysystem.services.notification;

import nl.soffware.supersimplesupplysystem.models.User;
import nl.soffware.supersimplesupplysystem.models.notification.Notification;

import java.util.List;

public interface NotificationService {
    void inviteUserToHousehold(String emailAddress, long householdId, User sender);
    List<Notification> getNotifications(String email);
    List<Notification> setNotificationsSeen(User user);
    List<Notification> acceptOrDeclineHouseholdInvitation(long notificationId, User user, boolean accept);
}

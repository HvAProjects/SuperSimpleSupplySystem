package nl.soffware.supersimplesupplysystem.services.notification;

import nl.soffware.supersimplesupplysystem.models.notification.Notification;

import java.util.List;

public interface NotificationService {
    void inviteUserToHousehold(String emailAddress, long householdId,  String principalName);
    List<Notification> getNotifications( String principalName);
    List<Notification> setNotificationsSeen( String principalName);
    List<Notification> acceptOrDeclineHouseholdInvitation(long notificationId, String principalName, boolean accept);
}

package nl.jed.supersimplesupplysystem.services.notification;

import nl.jed.supersimplesupplysystem.mail.NonExistingUserHouseholdInvitationMail;
import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.models.household.Household;
import nl.jed.supersimplesupplysystem.models.notification.HouseholdInvitationNotification;
import nl.jed.supersimplesupplysystem.models.notification.Notification;
import nl.jed.supersimplesupplysystem.models.notification.NotificationState;
import nl.jed.supersimplesupplysystem.models.notification.NotificationType;
import nl.jed.supersimplesupplysystem.repository.NotificationRepository;
import nl.jed.supersimplesupplysystem.repository.UserRepository;
import nl.jed.supersimplesupplysystem.repository.household.HouseholdRepository;
import nl.jed.supersimplesupplysystem.services.mail.MailService;
import nl.jed.supersimplesupplysystem.util.MailFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private HouseholdRepository householdRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MailService mailService;

    @Autowired
    private MailFactory mailFactory;

    @Override
    public void inviteUserToHousehold(String emailAddress, long householdId, User sender) {
        User user = userRepository.findByEmail(emailAddress);
        Household household = householdRepository.findById(householdId).orElseThrow();
        boolean userExists = user != null;
        HouseholdInvitationNotification notification = new HouseholdInvitationNotification();
        notification.setNotificationType(NotificationType.householdInvitation);
        notification.setUserEmail(emailAddress);
        notification.setDate(new Date());
        notification.setSender(sender);
        notification.setState(NotificationState.unseen);
        notification.setHousehold(household);
        notificationRepository.save(notification);

        if (!userExists) {
            mailService.sendEmail(mailFactory.getNonExistingUserHouseholdInvitationMail(emailAddress, sender));
        }
    }

    @Override
    public List<Notification> getNotifications(String email) {
        List<Notification> notifications = notificationRepository.getNotificationsByUserEmail(email);
        notifications.sort(Collections.reverseOrder());
        return notifications;
    }

    @Override
    public List<Notification> setNotificationsSeen(User user) {
        List<Notification> notifications = getNotifications(user.getEmail());
        for (Notification notification : notifications.stream().filter(n -> n.getState() == NotificationState.unseen).collect(Collectors.toList())) {
            notification.setState(NotificationState.seen);
            notificationRepository.save(notification);
        }
        return notifications;
    }

    @Override
    public List<Notification> acceptOrDeclineHouseholdInvitation(long notificationId, User user, boolean accept) {
        HouseholdInvitationNotification notification = (HouseholdInvitationNotification)notificationRepository.findById(notificationId).orElseThrow();
        if (notification.getUserEmail().equals(user.getEmail())) {
            NotificationState newState;
            if (accept) {
                newState = NotificationState.accepted;
                Household household = notification.getHousehold();
                household.addUser(user);
                householdRepository.save(household);
            } else {
                newState = NotificationState.declined;
            }
            notification.setState(newState);
            notificationRepository.save(notification);
        }
        return getNotifications(user.getEmail());
    }
}

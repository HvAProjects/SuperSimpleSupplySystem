package nl.soffware.supersimplesupplysystem.services.notification;

import nl.soffware.supersimplesupplysystem.models.User;
import nl.soffware.supersimplesupplysystem.models.household.Household;
import nl.soffware.supersimplesupplysystem.models.notification.HouseholdInvitationNotification;
import nl.soffware.supersimplesupplysystem.models.notification.Notification;
import nl.soffware.supersimplesupplysystem.models.notification.NotificationState;
import nl.soffware.supersimplesupplysystem.models.notification.NotificationType;
import nl.soffware.supersimplesupplysystem.repositories.HouseholdRepository;
import nl.soffware.supersimplesupplysystem.repositories.UserRepository;
import nl.soffware.supersimplesupplysystem.repository.NotificationRepository;
import nl.soffware.supersimplesupplysystem.services.mail.MailService;
import nl.soffware.supersimplesupplysystem.util.MailFactory;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service("notificationService")
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    private final HouseholdRepository householdRepository;

    private final UserRepository userRepository;

    private final MailService mailService;

    private final MailFactory mailFactory;
    private final Random r = new Random();

    public NotificationServiceImpl(NotificationRepository notificationRepository, HouseholdRepository householdRepository, UserRepository userRepository, MailService mailService, MailFactory mailFactory) {
        this.notificationRepository = notificationRepository;
        this.householdRepository = householdRepository;
        this.userRepository = userRepository;
        this.mailService = mailService;
        this.mailFactory = mailFactory;
    }

    @Override
    public void inviteUserToHousehold(String emailAddress, long householdId,  String principalName) {
        User user = userRepository.findByEmail(emailAddress);
        var sender = userRepository.findUserByProviderUserId(principalName);
        Household household = householdRepository.findById(householdId).orElseThrow(() -> new NotFoundException("household not found"));
        boolean userExists = user != null;
        var id = 1L;
        while(notificationRepository.existsById(id)){
            id = r.nextLong();
        }
        HouseholdInvitationNotification notification = new HouseholdInvitationNotification();
        notification.setNotificationType(NotificationType.householdInvitation);
        notification.setUserEmail(emailAddress);
        notification.setDate(new Date());
        notification.setSender(sender);
        notification.setState(NotificationState.unseen);
        notification.setHousehold(household);
        notification.setId(id);
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
    public List<Notification> setNotificationsSeen( String principalName) {
        var user = userRepository.findUserByProviderUserId(principalName);
        List<Notification> notifications = getNotifications(user.getEmail());
        for (Notification notification : notifications.stream().filter(n -> n.getState() == NotificationState.unseen).collect(Collectors.toList())) {
            notification.setState(NotificationState.seen);
            notificationRepository.save(notification);
        }
        return notifications;
    }

    @Override
    public List<Notification> acceptOrDeclineHouseholdInvitation(long notificationId,  String principalName, boolean accept) {
        HouseholdInvitationNotification notification = (HouseholdInvitationNotification)notificationRepository.findById(notificationId).orElseThrow(() -> new NotFoundException("Notification not found"));
        var user = userRepository.findUserByProviderUserId(principalName);
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

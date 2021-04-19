package nl.jed.supersimplesupplysystem.services;

import nl.jed.supersimplesupplysystem.mail.NonExistingUserHouseholdInvitationMail;
import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.models.household.Household;
import nl.jed.supersimplesupplysystem.models.notification.HouseholdInvitationNotification;
import nl.jed.supersimplesupplysystem.models.notification.Notification;
import nl.jed.supersimplesupplysystem.models.notification.NotificationState;
import nl.jed.supersimplesupplysystem.repository.NotificationRepository;
import nl.jed.supersimplesupplysystem.repository.UserRepository;
import nl.jed.supersimplesupplysystem.repository.household.HouseholdRepository;
import nl.jed.supersimplesupplysystem.services.mail.MailServiceImpl;
import nl.jed.supersimplesupplysystem.services.notification.NotificationServiceImpl;
import nl.jed.supersimplesupplysystem.util.MailFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NotificationServiceTest {

    @Mock
    private MailServiceImpl mailServiceMock;

    @Mock
    private NotificationRepository notificationRepositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private HouseholdRepository householdRepositoryMock;

    @Mock
    private MailFactory mailFactoryMock;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void inviteExistingUserToHouseholdTest() {
        User user = new User();
        user.setEmail("admin@ssss.com");

        User user2 = new User();

        Household household = new Household();
        household.setId(1L);
        when(userRepositoryMock.findByEmail(user.getEmail())).thenReturn(user);
        when(householdRepositoryMock.findById(household.getId())).thenReturn(Optional.of(household));
        notificationService.inviteUserToHousehold(user.getEmail(), household.getId(), user2);
        verify(notificationRepositoryMock, times(1)).save(any(Notification.class));
        verify(mailServiceMock, times(0)).sendEmail(any(NonExistingUserHouseholdInvitationMail.class));
    }

    @Test
    void inviteNonExistingUserToHouseholdTest() {
        String email = "test@gmail.com";
        User user2 = new User();

        Household household = new Household();
        household.setId(1L);
        when(userRepositoryMock.findByEmail(email)).thenReturn(null);
        when(householdRepositoryMock.findById(household.getId())).thenReturn(Optional.of(household));
        when(mailFactoryMock.getNonExistingUserHouseholdInvitationMail(any(String.class), any(User.class))).thenReturn(new NonExistingUserHouseholdInvitationMail(email, user2, ""));
        notificationService.inviteUserToHousehold(email, household.getId(), user2);
        verify(notificationRepositoryMock, times(1)).save(any(Notification.class));
        verify(mailServiceMock, times(1)).sendEmail(any(NonExistingUserHouseholdInvitationMail.class));
    }

    @Test
    void testSetNotificationsSeen() {
        User user = new User();
        user.setEmail("test@email.com");
        List<Notification> notifications = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Notification notification = new Notification();
            notification.setState(NotificationState.unseen);
            notification.setDate(new Date());
            notifications.add(notification);
        }
        when(notificationRepositoryMock.getNotificationsByUserEmail(user.getEmail())).thenReturn(notifications);
        notificationService.setNotificationsSeen(user);
        verify(notificationRepositoryMock, times(5)).save(any(Notification.class));
        assert notifications.stream().allMatch(n -> n.getState().equals(NotificationState.seen));
    }

    @Test
    void testAcceptHouseholdInvitation() {
        User user = new User();
        user.setEmail("test@gmail.com");
        HouseholdInvitationNotification notification = new HouseholdInvitationNotification();
        notification.setState(NotificationState.unseen);
        Household household = new Household();
        household.setUsers(new HashSet<>());
        notification.setHousehold(household);
        notification.setUserEmail(user.getEmail());
        notification.setId(1L);
        when(notificationRepositoryMock.findById(1L)).thenReturn(Optional.of(notification));
        notificationService.acceptOrDeclineHouseholdInvitation(1L, user, true);
        verify(householdRepositoryMock, times(1)).save(household);
        assert notification.getState().equals(NotificationState.accepted);
        verify(notificationRepositoryMock, times(1)).save(notification);
        assert household.getUsers().contains(user);
    }

    @Test
    void testAcceptDeclineHouseholdInvitation() {
        User user = new User();
        user.setEmail("test@gmail.com");
        HouseholdInvitationNotification notification = new HouseholdInvitationNotification();
        notification.setState(NotificationState.unseen);
        Household household = new Household();
        household.setUsers(new HashSet<>());
        notification.setHousehold(household);
        notification.setUserEmail(user.getEmail());
        notification.setId(1L);
        when(notificationRepositoryMock.findById(1L)).thenReturn(Optional.of(notification));
        notificationService.acceptOrDeclineHouseholdInvitation(1L, user, false);
        verify(householdRepositoryMock, times(0)).save(household);
        assert notification.getState().equals(NotificationState.declined);
        verify(notificationRepositoryMock, times(1)).save(notification);
        assert !household.getUsers().contains(user);
    }
}

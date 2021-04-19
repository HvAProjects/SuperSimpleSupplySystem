package nl.jed.supersimplesupplysystem.configuration;

import lombok.Generated;
import nl.jed.supersimplesupplysystem.dto.SocialProvider;
import nl.jed.supersimplesupplysystem.models.Role;
import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.models.household.Household;
import nl.jed.supersimplesupplysystem.models.location.Location;
import nl.jed.supersimplesupplysystem.models.notification.HouseholdInvitationNotification;
import nl.jed.supersimplesupplysystem.models.notification.Notification;
import nl.jed.supersimplesupplysystem.models.notification.NotificationState;
import nl.jed.supersimplesupplysystem.models.notification.NotificationType;
import nl.jed.supersimplesupplysystem.repository.LocationRepository;
import nl.jed.supersimplesupplysystem.repository.NotificationRepository;
import nl.jed.supersimplesupplysystem.repository.RoleRepository;
import nl.jed.supersimplesupplysystem.repository.UserRepository;
import nl.jed.supersimplesupplysystem.repository.household.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Generated
@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private boolean alreadySetup = false;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private HouseholdRepository householdRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    @Transactional
    public void onApplicationEvent(final ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        // Create initial roles
        Role userRole = createRoleIfNotFound(Role.ROLE_USER);
        Role adminRole = createRoleIfNotFound(Role.ROLE_ADMIN);
        Role modRole = createRoleIfNotFound(Role.ROLE_MODERATOR);
        createUserIfNotFound("admin@supersimplesupplysystem.com", Set.of(userRole, adminRole, modRole));
        User joe = createUserIfNotFound("joe@ssss.com", Set.of(userRole, adminRole, modRole));
        User evan = createUserIfNotFound("evan@ssss.com", Set.of(userRole, adminRole, modRole));
        Household joeHousehold = createHousehold("TestHousehold", joe);
        Household household = createHousehold("TestHousehold", evan);
        Location location = createLocation("TestLocation", household);
        HouseholdInvitationNotification invitationNotification = createHouseholdInvitationNotification(evan, joe, joeHousehold);
        alreadySetup = true;
    }

    @Transactional
    public HouseholdInvitationNotification createHouseholdInvitationNotification(User to, User from, Household household) {
        HouseholdInvitationNotification invitation = new HouseholdInvitationNotification();
        invitation.setNotificationType(NotificationType.householdInvitation);
        invitation.setDate(new Date());
        invitation.setState(NotificationState.unseen);
        invitation.setSender(from);
        invitation.setUserEmail(to.getEmail());
        invitation.setHousehold(household);
        notificationRepository.save(invitation);
        return invitation;
    }

    @Transactional
    public User createUserIfNotFound(final String email, Set<Role> roles) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setDisplayName("Admin");
            user.setEmail(email);
            user.setPassword(passwordEncoder.encode("admin@"));
            user.setRoles(roles);
            user.setProvider(SocialProvider.LOCAL.getProviderType());
            user.setEnabled(true);
            Date now = Calendar.getInstance().getTime();
            user.setCreatedDate(now);
            user.setModifiedDate(now);
            user = userRepository.save(user);
        }
        return user;
    }

    @Transactional
    public Role createRoleIfNotFound(final String name) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = roleRepository.save(new Role(name));
        }
        return role;
    }

    public Household createHousehold(String name, User user) {
        Household household = new Household();
        household.setName(name);
        household.setAddress("TestAddress");
        household.setPostalCode("3535CZ");
        HashSet<User> users = new HashSet<>();
        users.add(user);
        household.setUsers(users);
        householdRepository.save(household);
        return household;
    }

    public Location createLocation(String name, Household household) {
        Location location = new Location();
        location.setName(name);
        location.setHousehold(household);
        locationRepository.save(location);
        return location;
    }
}

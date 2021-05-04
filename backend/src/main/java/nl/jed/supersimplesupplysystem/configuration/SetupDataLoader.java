package nl.jed.supersimplesupplysystem.configuration;

import lombok.Generated;
import nl.jed.supersimplesupplysystem.dto.SocialProvider;
import nl.jed.supersimplesupplysystem.models.Role;
import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.models.household.Household;
import nl.jed.supersimplesupplysystem.models.location.Location;
import nl.jed.supersimplesupplysystem.models.notification.HouseholdInvitationNotification;
import nl.jed.supersimplesupplysystem.models.notification.NotificationState;
import nl.jed.supersimplesupplysystem.models.notification.NotificationType;
import nl.jed.supersimplesupplysystem.models.product.Product;
import nl.jed.supersimplesupplysystem.repository.*;
import nl.jed.supersimplesupplysystem.repository.household.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
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

    @Autowired
    private ProductRepository productRepository;


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
        createUserIfNotFound("admin@supersimplesupplysystem.com", Set.of(userRole, adminRole, modRole), "Admin");
        User evan = createUserIfNotFound("evan@ssss.com", Set.of(userRole, adminRole, modRole), "Evan");
        User joe = createUserIfNotFound("joe@ssss.com", Set.of(userRole, adminRole, modRole), "Joe");
        User david = createUserIfNotFound("david@ssss.com", Set.of(userRole, adminRole, modRole), "David");
        Household joeHousehold = createHousehold("JoeHousehold", joe);
        Household household = createHousehold("EvanHousehold", evan);
        Location location = createLocation("TestLocation", household);

        createProduct(10, location, "233423323", java.sql.Date.valueOf(LocalDate.now().plusDays(2)), "Pindakaas", "1kg");
        createProduct(2, location, "323342323", java.sql.Date.valueOf(LocalDate.now().minusDays(1)), "Fanta", "33ml");
        createProduct(5, location, "2322332323", java.sql.Date.valueOf(LocalDate.now().plusDays(20)), "Cola", "33ml");


        Household household1 = createHousehold("Joe House", joe);
        Household household2 = createHousehold("Dave Cave", david);
        Location location2 = createLocation("Fridge", household1);
        Location location3 = createLocation("Cupboard-Left-1", household1);
        Location location4 = createLocation("Closet1", household2);
        Location location5 = createLocation("Cupboard-Left-2", household2);

        createProduct(10, location2, "233423323", java.sql.Date.valueOf(LocalDate.now().plusDays(2)), "Pindakaas", "1kg");
        createProduct(2, location2, "323342323", java.sql.Date.valueOf(LocalDate.now().minusDays(1)), "Fanta", "33ml");
        createProduct(5, location2, "2322332323", java.sql.Date.valueOf(LocalDate.now().plusDays(20)), "Cola", "33ml");

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
    public User createUserIfNotFound(final String email, Set<Role> roles, String name) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setDisplayName(name);
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
        household.setCity("Amsterdammm");
        household.setCountry("Netherlands");
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

    public Product createProduct(long amount, Location location, String barcode, Date expirationDate, String name, String quantity) {
        Product product = new Product();
        product.setBarcode(barcode);
        product.setAmount(amount);
        product.setName(name);
        product.setLocation(location);
        product.setExpirationDate(expirationDate);
        product.setQuantity(quantity);
        productRepository.save(product);
        return product;
    }
}

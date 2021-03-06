package nl.jed.supersimplesupplysystem.helpers;

import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import nl.jed.supersimplesupplysystem.dto.LocalUser;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import org.springframework.core.log.LogMessage;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsPasswordService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.memory.UserAttribute;
import org.springframework.security.core.userdetails.memory.UserAttributeEditor;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.util.Assert;

public class InMemoryUserDetailsManager implements UserDetailsManager, UserDetailsPasswordService {

    protected final Log logger = LogFactory.getLog(getClass());

    private final Map<String, LocalUser> users = new HashMap<>();

    private AuthenticationManager authenticationManager;

    public InMemoryUserDetailsManager() {
    }

    public InMemoryUserDetailsManager(Collection<UserDetails> users) {
        for (UserDetails user : users) {
            createUser(user);
        }
    }

    public InMemoryUserDetailsManager(UserDetails... users) {
        for (UserDetails user : users) {
            createUser(user);
        }
    }

    public InMemoryUserDetailsManager(Properties users) {
        Enumeration<?> names = users.propertyNames();
        UserAttributeEditor editor = new UserAttributeEditor();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            editor.setAsText(users.getProperty(name));
            UserAttribute attr = (UserAttribute) editor.getValue();
            createUser(createUserDetails(name, attr));
        }
    }

    private User createUserDetails(String name, UserAttribute attr) {
        return new User(name, attr.getPassword(), attr.isEnabled(), true, true, true, attr.getAuthorities());
    }

    @Override
    public void createUser(UserDetails user) {
        Assert.isTrue(!userExists(user.getUsername()), "user should not exist");
        this.users.put(user.getUsername().toLowerCase(), (LocalUser) user);
    }

    @Override
    public void deleteUser(String username) {
        this.users.remove(username.toLowerCase());
    }

    @Override
    public void updateUser(UserDetails user) {
        Assert.isTrue(userExists(user.getUsername()), "user should exist");
        this.users.put(user.getUsername().toLowerCase(), (LocalUser) user);
    }

    @Override
    public boolean userExists(String username) {
        return this.users.containsKey(username.toLowerCase());
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Authentication currentUser = SecurityContextHolder.getContext().getAuthentication();
        if (currentUser == null) {
            // This would indicate bad coding somewhere
            throw new AccessDeniedException(
                    "Can't change password as no Authentication object found in context " + "for current user.");
        }
        String username = currentUser.getName();
        this.logger.debug(LogMessage.format("Changing password for user '%s'", username));
        // If an authentication manager has been set, re-authenticate the user with the
        // supplied password.
        if (this.authenticationManager != null) {
            this.logger.debug(LogMessage.format("Reauthenticating user '%s' for password change request.", username));
            this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, oldPassword));
        }
        else {
            this.logger.debug("No authentication manager set. Password won't be re-checked.");
        }
        LocalUser user = this.users.get(username);
        Assert.state(user != null, "Current user doesn't exist in database.");
        user.getUser().setPassword(newPassword);
    }

    @Override
    public UserDetails updatePassword(UserDetails user, String newPassword) {
        String username = user.getUsername();
        LocalUser mutableUser = this.users.get(username.toLowerCase());
        mutableUser.getUser().setPassword(newPassword);
        return mutableUser;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        LocalUser user = this.users.get(username.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return user;
    }

    public void setAuthenticationManager(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

}

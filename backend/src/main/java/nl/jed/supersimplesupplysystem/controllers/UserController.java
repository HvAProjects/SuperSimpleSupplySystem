package nl.jed.supersimplesupplysystem.controllers;

import nl.jed.supersimplesupplysystem.configuration.CurrentUser;
import nl.jed.supersimplesupplysystem.dto.LocalUser;
import nl.jed.supersimplesupplysystem.dto.UserInfo;
import nl.jed.supersimplesupplysystem.util.GeneralUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @GetMapping("/user/me")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<UserInfo> getCurrentUser(@CurrentUser LocalUser user) {
        return ResponseEntity.ok(GeneralUtils.buildUserInfo(user));
    }

    @GetMapping("/all")
    public ResponseEntity<String> getContent() {
        return ResponseEntity.ok("Public content goes here");
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> getUserContent() {
        return ResponseEntity.ok("User content goes here");
    }

    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> getAdminContent() {
        return ResponseEntity.ok("Admin content goes here");
    }

    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public ResponseEntity<String> getModeratorContent() {
        return ResponseEntity.ok("Moderator content goes here");
    }
}

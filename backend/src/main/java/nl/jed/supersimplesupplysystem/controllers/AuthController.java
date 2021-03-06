package nl.jed.supersimplesupplysystem.controllers;

import javax.validation.Valid;

import nl.jed.supersimplesupplysystem.dto.*;
import nl.jed.supersimplesupplysystem.exception.UserAlreadyExistAuthenticationException;
import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.security.jwt.TokenProvider;
import nl.jed.supersimplesupplysystem.services.security.SecurityService;
import nl.jed.supersimplesupplysystem.services.user.UserService;
import nl.jed.supersimplesupplysystem.util.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

import java.net.PasswordAuthentication;

@Slf4j
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    TokenProvider tokenProvider;

    @Autowired
    SecurityService securityService;

    @PostMapping("/signin")
    public ResponseEntity authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (DisabledException e) {
            return new ResponseEntity(new ApiResponse(false, "Account should be activated first!"), HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException e) {
            return new ResponseEntity(new ApiResponse(false, "The combination of email and password are not correct!"), HttpStatus.BAD_REQUEST);
        }
        SecurityContextHolder.getContext().setAuthentication(authentication);
        LocalUser principal = (LocalUser) authentication.getPrincipal();
        String jwt = tokenProvider.createToken(principal);
        LocalUser localUser = (LocalUser) authentication.getPrincipal();
        return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, GeneralUtils.buildUserInfo(localUser)));
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        try {
            userService.registerNewUser(signUpRequest);
        } catch (UserAlreadyExistAuthenticationException e) {
            return new ResponseEntity<>(new ApiResponse(false, "Email Address already in use!"), HttpStatus.BAD_REQUEST);
        }
        return ResponseEntity.ok().body(new ApiResponse(true, "User registered successfully"));
    }

    @PostMapping("/forgot-password")
    public void forgotPassword(@Valid @RequestBody ForgotPasswordRequest forgotPasswordRequest) {
        userService.resetPassword(forgotPasswordRequest.getEmail());
    }

    @PostMapping("/change-password-with-token")
    public ResponseEntity<ApiResponse> changePasswordWithToken(@Valid @RequestBody ChangePasswordRequest request) {
        User user = securityService.validatePasswordResetToken(request.getToken());
        if (user == null) {
            return new ResponseEntity<>(new ApiResponse(false, "Token expired or invalid"), HttpStatus.BAD_REQUEST); // Token expired or invalid
        }
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        } catch (DisabledException e) {
            return new ResponseEntity<>(new ApiResponse(false, "Account should be activated first!"), HttpStatus.BAD_REQUEST);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(new ApiResponse(false, "The combination of email and password are not correct!"), HttpStatus.BAD_REQUEST);
        }
        userService.changePassword(user, request.getNewPassword());
        return ResponseEntity.ok(new ApiResponse(true, "Password change successful"));
    }

    @PostMapping("/activate-account")
    public ResponseEntity<ApiResponse> activateAccount(@RequestBody ActivateAccountRequest request) {
        User user = securityService.validateActivateAccountToken(request.getToken());
        if (user == null) {
            return new ResponseEntity<>(new ApiResponse(false, "Invalid token"), HttpStatus.BAD_REQUEST); // Token expired or invalid
        }
        userService.activateAccount(user);
        return ResponseEntity.ok(new ApiResponse(true, "Account activated, you can login now."));
    }
}

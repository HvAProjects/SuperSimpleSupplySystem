package nl.jed.supersimplesupplysystem.controllers;

import lombok.extern.slf4j.Slf4j;
import nl.jed.supersimplesupplysystem.dto.*;
import nl.jed.supersimplesupplysystem.exception.UserAlreadyExistAuthenticationException;
import nl.jed.supersimplesupplysystem.security.jwt.TokenProvider;
import nl.jed.supersimplesupplysystem.services.UserService;
import nl.jed.supersimplesupplysystem.util.GeneralUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

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

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            LocalUser principal = (LocalUser) authentication.getPrincipal();
            String jwt = tokenProvider.createToken(principal);
            return ResponseEntity.ok(new JwtAuthenticationResponse(jwt, GeneralUtils.buildUserInfo(principal)));
        } catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/signup")
    public ResponseEntity<ApiResponse> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
        try {
            userService.registerNewUser(signUpRequest);
        } catch (UserAlreadyExistAuthenticationException e) {
            return ResponseEntity.badRequest().body(new ApiResponse(false, "Email Address already in use!"));
        }
        return ResponseEntity.ok().body(new ApiResponse(true, "User registered successfully"));
    }
}

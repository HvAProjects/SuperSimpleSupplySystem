package nl.jed.supersimplesupplysystem.services.security;

public interface SecurityService {
    boolean validatePasswordResetToken(String token);
}

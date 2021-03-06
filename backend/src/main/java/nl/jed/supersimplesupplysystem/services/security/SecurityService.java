package nl.jed.supersimplesupplysystem.services.security;

import nl.jed.supersimplesupplysystem.models.User;

public interface SecurityService {
    User validatePasswordResetToken(String token);
    User validateActivateAccountToken(String token);
}

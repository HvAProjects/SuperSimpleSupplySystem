package nl.jed.supersimplesupplysystem.services.security;

public class SecurityServiceImpl implements SecurityService {
    @Override
    public boolean validatePasswordResetToken(String token) {
        return false;
    }
}

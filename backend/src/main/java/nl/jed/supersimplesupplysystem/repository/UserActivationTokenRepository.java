package nl.jed.supersimplesupplysystem.repository;

import nl.jed.supersimplesupplysystem.models.UserActivationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserActivationTokenRepository extends JpaRepository<UserActivationToken, Long> {
    UserActivationToken findByToken(String token);
}

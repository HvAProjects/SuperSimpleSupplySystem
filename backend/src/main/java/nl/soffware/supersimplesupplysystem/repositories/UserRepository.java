package nl.soffware.supersimplesupplysystem.repositories;

import nl.soffware.supersimplesupplysystem.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmail(String email);

    boolean existsByProviderUserId(String providerID);

    boolean existsByEmail(String email);

    User findUserByProviderUserId(String id);
}

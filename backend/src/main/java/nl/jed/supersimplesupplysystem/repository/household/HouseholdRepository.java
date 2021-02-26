package nl.jed.supersimplesupplysystem.repository.household;

import nl.jed.supersimplesupplysystem.models.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author joe.vrolijk
 * @since 26/02/2021
 */

@Repository
public interface HouseholdRepository extends JpaRepository<Household, Long> {

}

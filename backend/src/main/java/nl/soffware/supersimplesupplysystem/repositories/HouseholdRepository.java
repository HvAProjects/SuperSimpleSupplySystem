package nl.soffware.supersimplesupplysystem.repositories;

import nl.soffware.supersimplesupplysystem.models.household.Household;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HouseholdRepository extends JpaRepository<Household, Long> {

}

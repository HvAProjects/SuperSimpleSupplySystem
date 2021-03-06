package nl.jed.supersimplesupplysystem.services.household;

import nl.jed.supersimplesupplysystem.models.household.Household;

import java.util.List;

/**
 * @author joe.vrolijk
 * @since 26/02/2021
 */
public interface HouseholdService {

    List<Household> getAllHouseholds();

    Household getHousehold(Long id);

    void addHousehold(Household household);

    void removeHousehold(Long id);

}

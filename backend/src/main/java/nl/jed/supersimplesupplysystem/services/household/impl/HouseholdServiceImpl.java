package nl.jed.supersimplesupplysystem.services.household.impl;

import lombok.val;
import nl.jed.supersimplesupplysystem.models.Household;
import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.repository.household.HouseholdRepository;
import nl.jed.supersimplesupplysystem.services.household.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author joe.vrolijk
 * @since 26/02/2021
 */
@Service
public class HouseholdServiceImpl implements HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;


    @Override
    public List<Household> getAllHouseholds() {
        val households = householdRepository.findAll();
        return households;
    }

    @Override
    public Household getHousehold(Long id) {
        val household = householdRepository.getOne(id);
        return household;
    }

    @Override
    public void addHousehold(Household household) {
        householdRepository.save(household);
    }

    @Override
    public void removeHousehold(Long id) {
        householdRepository.deleteById(id);

    }
}

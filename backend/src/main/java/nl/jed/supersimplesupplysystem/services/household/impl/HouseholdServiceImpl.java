package nl.jed.supersimplesupplysystem.services.household.impl;

import lombok.val;
import nl.jed.supersimplesupplysystem.configuration.AppProperties;
import nl.jed.supersimplesupplysystem.dto.LocalUser;
import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.models.household.Household;
import nl.jed.supersimplesupplysystem.repository.household.HouseholdRepository;
import nl.jed.supersimplesupplysystem.services.household.HouseholdService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author joe.vrolijk
 * @since 26/02/2021
 */
@Service
public class HouseholdServiceImpl implements HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;


    @Override
    @PostFilter("filterObject.hasAccess(authentication.name)")
    public List<Household> getAllHouseholds() {
        val households = householdRepository.findAll();
        return households;
    }

    @Override
    public Household getHousehold(Long id) {
        val household = householdRepository.findById(id);
        return household.orElse(null);
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

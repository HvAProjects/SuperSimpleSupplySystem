package nl.soffware.supersimplesupplysystem.services.household;

import lombok.val;
import nl.soffware.supersimplesupplysystem.models.User;
import nl.soffware.supersimplesupplysystem.models.household.Household;
import nl.soffware.supersimplesupplysystem.repository.household.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseholdService {

    @Autowired
    private HouseholdRepository householdRepository;


    @PostFilter("filterObject.hasAccess(authentication.name)")
    public List<Household> getAllHouseholds() {
        
        val households = householdRepository.findAll();
        return households;
    }

    public Household getHousehold(Long id) {
        val household = householdRepository.findById(id);
        return household.orElse(null);
    }

    public void addHousehold(Household household) {
        householdRepository.save(household);
    }

    public void leaveHousehold(Long id, User user) throws NotFoundException {
        Household household = householdRepository.findById(id).orElseThrow(NotFoundException::new);
        household.getUsers().remove(user);
        householdRepository.save(household);
    }

    public void editHousehold(Long id, Household household) {
        val originalHousehold = getHousehold(id);
        if (originalHousehold != null){
            originalHousehold.setName(household.getName());
            originalHousehold.setAddress(household.getAddress());
            originalHousehold.setPostalCode(household.getPostalCode());
            originalHousehold.setCity(household.getCity());
            originalHousehold.setCountry(household.getCountry() == null ? originalHousehold.getCountry() : household.getCountry() );
            householdRepository.save(originalHousehold);
        }


    }
}

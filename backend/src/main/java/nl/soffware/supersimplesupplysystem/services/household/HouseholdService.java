package nl.soffware.supersimplesupplysystem.services.household;

import lombok.val;
import nl.soffware.supersimplesupplysystem.models.household.Household;
import nl.soffware.supersimplesupplysystem.repositories.HouseholdRepository;
import nl.soffware.supersimplesupplysystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseholdService {

    private final HouseholdRepository householdRepository;
    private final UserRepository userRepository;

    @Autowired
    public HouseholdService(HouseholdRepository householdRepository, UserRepository userRepository) {
        this.householdRepository = householdRepository;
        this.userRepository = userRepository;
    }


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

    public void leaveHousehold(Long id, String principalName) throws NotFoundException {
        var user = userRepository.findUserByProviderUserId(principalName);
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

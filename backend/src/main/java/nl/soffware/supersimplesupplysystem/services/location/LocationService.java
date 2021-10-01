package nl.soffware.supersimplesupplysystem.services.location;

import nl.soffware.supersimplesupplysystem.models.location.Location;
import nl.soffware.supersimplesupplysystem.repository.LocationRepository;
import nl.soffware.supersimplesupplysystem.repository.household.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service("locationService")
public class LocationServiceImpl implements LocationService {

    @PersistenceContext
    public EntityManager entityManager;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private HouseholdRepository householdRepository;

    @Override
    public List<Location> getLocationsOfHousehold(long householdId) {
        return locationRepository.findByHouseholdId(householdId);
    }

    @Override
    public void addLocationToHousehold(long householdId, Location location) {
        location.setTenantId(String.valueOf(householdId));
        locationRepository.save(location);
    }

    @Override
    public void deleteLocation(long locationId) {
        locationRepository.deleteById(locationId);
    }
}

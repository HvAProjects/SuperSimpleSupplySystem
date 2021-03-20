package nl.jed.supersimplesupplysystem.services.location;

import nl.jed.supersimplesupplysystem.models.household.Household;
import nl.jed.supersimplesupplysystem.models.location.Location;
import nl.jed.supersimplesupplysystem.repository.LocationRepository;
import nl.jed.supersimplesupplysystem.repository.household.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("locationService")
public class LocationServiceImpl implements LocationService {

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
        Household household = householdRepository.findById(householdId).orElseThrow();
        location.setHousehold(household);
        locationRepository.save(location);
    }
}

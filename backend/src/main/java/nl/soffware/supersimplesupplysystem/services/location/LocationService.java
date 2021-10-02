package nl.soffware.supersimplesupplysystem.services.location;

import lombok.RequiredArgsConstructor;
import nl.soffware.supersimplesupplysystem.models.location.Location;
import nl.soffware.supersimplesupplysystem.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LocationService {

    @PersistenceContext
    public EntityManager entityManager;

    private final LocationRepository locationRepository;

    public List<Location> getLocationsOfHousehold(Long householdId) {
        return locationRepository.findByTenantId(householdId.toString());
    }

    public void addLocationToHousehold(Long householdId, Location location) {
        location.setTenantId(String.valueOf(householdId));
        locationRepository.save(location);
    }

    public void deleteLocation(Long locationId) {
        locationRepository.deleteById(locationId);
    }
}

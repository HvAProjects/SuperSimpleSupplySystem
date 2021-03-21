package nl.jed.supersimplesupplysystem.services.location;

import nl.jed.supersimplesupplysystem.models.location.Location;

import java.util.List;

public interface LocationService {

    List<Location> getLocationsOfHousehold(long householdId);

    void addLocationToHousehold(long householdId, Location location);

    void deleteLocation(long locationId);
}

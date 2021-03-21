package nl.jed.supersimplesupplysystem.controllers.location;

import lombok.extern.slf4j.Slf4j;
import nl.jed.supersimplesupplysystem.dto.ApiResponse;
import nl.jed.supersimplesupplysystem.models.location.Location;
import nl.jed.supersimplesupplysystem.services.location.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/location")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @GetMapping("/{householdId}")
    public List<Location> getLocationsOfHousehold(@PathVariable("householdId") long householdId) {
        return locationService.getLocationsOfHousehold(householdId);
    }

    @PostMapping("/{householdId}")
    public ResponseEntity<ApiResponse> addLocationToHousehold(@PathVariable("householdId") long householdId, @Valid @RequestBody Location location) {
        locationService.addLocationToHousehold(householdId, location);
        return ResponseEntity.ok().body(new ApiResponse(true, "Location has been created successfully."));
    }

    @DeleteMapping("/{locationId}")
    public ResponseEntity<ApiResponse> deleteLocation(@PathVariable("locationId") long locationId) {
        locationService.deleteLocation(locationId);
        return ResponseEntity.ok().body(new ApiResponse(true, "Location deleted successfully."));
    }
}

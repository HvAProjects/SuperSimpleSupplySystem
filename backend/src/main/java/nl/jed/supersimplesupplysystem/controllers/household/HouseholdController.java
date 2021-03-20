package nl.jed.supersimplesupplysystem.controllers.household;

import lombok.extern.slf4j.Slf4j;
import nl.jed.supersimplesupplysystem.dto.ApiResponse;
import nl.jed.supersimplesupplysystem.models.household.Household;
import nl.jed.supersimplesupplysystem.services.household.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

/**
 * @author joe.vrolijk
 * @since 26/02/2021
 */

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/household")
public class HouseholdController {

    @Autowired
    HouseholdService householdService;

    @GetMapping("/")
    public List<Household> getAllHouseholds(){
        return householdService.getAllHouseholds();
    }

    @GetMapping("/{id}")
    public Household getHousehold(@PathVariable("id") Long id){
        return householdService.getHousehold(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteHousehold(@PathVariable("id") Long id){
        householdService.removeHousehold(id);
        return ResponseEntity.ok().body(new ApiResponse(true, "Household has been successfully removed."));
    }

    @PostMapping(path = "/")
    public ResponseEntity<ApiResponse> addHousehold(@Valid @RequestBody Household household){
        householdService.addHousehold(household);
        return ResponseEntity.ok().body(new ApiResponse(true, "Household has been created successfully."));
    }
}

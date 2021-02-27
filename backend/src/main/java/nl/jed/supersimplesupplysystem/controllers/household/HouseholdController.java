package nl.jed.supersimplesupplysystem.controllers.household;

import lombok.extern.slf4j.Slf4j;
import nl.jed.supersimplesupplysystem.models.household.Household;
import nl.jed.supersimplesupplysystem.services.household.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity deleteHousehold(@PathVariable("id") Long id){
        householdService.removeHousehold(id);
        return ResponseEntity.ok("Household has been successfully removed.");
    }

    @PostMapping(path = "/", consumes = "application/json")
    public ResponseEntity addHousehold(@Valid @RequestBody Household household){
        householdService.addHousehold(household);
        return ResponseEntity.ok("Household has been create successfully.");
    }







}
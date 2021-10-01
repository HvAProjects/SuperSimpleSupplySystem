package nl.soffware.supersimplesupplysystem.controllers.household;

import lombok.extern.slf4j.Slf4j;
import nl.soffware.supersimplesupplysystem.dto.ApiResponse;
import nl.soffware.supersimplesupplysystem.dto.LocalUser;
import nl.soffware.supersimplesupplysystem.dto.UserDto;
import nl.soffware.supersimplesupplysystem.models.User;
import nl.soffware.supersimplesupplysystem.models.household.Household;
import nl.soffware.supersimplesupplysystem.services.household.HouseholdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author joe.vrolijk
 * @since 26/02/2021
 */

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/household")
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

    @GetMapping("householdUsers/{id}")
    public Set<UserDto> getUsersOfHousehold(@PathVariable("id") Long id){
        return householdService.getHousehold(id).getUsers().stream().map(User::toUserDto).collect(Collectors.toSet());
    }

    @PostMapping("/leaveHousehold/{id}")
    public ResponseEntity<ApiResponse> leaveHousehold(@PathVariable("id") Long id, Principal principal){
        LocalUser localUser = (LocalUser) ((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        householdService.leaveHousehold(id, localUser.getUser());
        return ResponseEntity.ok().body(new ApiResponse(true, "You have left the household successfully."));
    }

    @PostMapping(path = "/")
    public ResponseEntity<ApiResponse> addHousehold(@Valid @RequestBody Household household){
        householdService.addHousehold(household);
        return ResponseEntity.ok().body(new ApiResponse(true, "Household has been created successfully."));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<ApiResponse> editHousehold(@PathVariable("id") Long id, @RequestBody Household household){
        householdService.editHousehold(id, household);
        return ResponseEntity.ok().body(new ApiResponse(true, "Household has been edited successfully."));
    }
}

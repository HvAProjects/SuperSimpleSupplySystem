package nl.jed.supersimplesupplysystem.services.household.impl;

import nl.jed.supersimplesupplysystem.models.household.Household;
import nl.jed.supersimplesupplysystem.repository.household.HouseholdRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


import java.util.List;
import java.util.Optional;

/**
 * @author joe.vrolijk
 * @since 27/02/2021
 */
class HouseholdServiceImplTest {

    @Mock
    HouseholdRepository householdRepositoryMock;

    @InjectMocks
    HouseholdServiceImpl householdService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllHouseholds() {

        Household household1 = new Household();
        household1.setId(13L);
        household1.setName("MijnHuis");
        household1.setAddress("Dam 1");
        household1.setPostalCode("1111AB");
        household1.setCountry("Zambia");

        Household household2 = new Household();
        household2.setName("MijnAndereHuis");
        household2.setAddress("Zaandam 1");
        household2.setPostalCode("1111XZ");
        household2.setCountry("Nigeria");


        List<Household> households = List.of(household1, household2);

        when(householdRepositoryMock.findAll()).thenReturn(households);

        List<Household> result = householdService.getAllHouseholds();

        verify(householdRepositoryMock, times(1)).findAll();
        assertThat(result, is(households));
    }

    @Test
    void getHousehold() {
        Household household1 = new Household();
        household1.setId(1L);
        household1.setName("MijnHuis");
        household1.setAddress("Dam 1");
        household1.setPostalCode("1111AB");
        household1.setCountry("Zambia");

        when(householdRepositoryMock.findById(1L)).thenReturn(Optional.of(household1));

        Household result = householdService.getHousehold(1L);

        verify(householdRepositoryMock, times(1)).findById(1L);
        assertThat(result, is(household1));
    }

    @Test
    void addHousehold() {
        Household householdMock = mock(Household.class);
        householdService.addHousehold(householdMock);
        verify(householdRepositoryMock, times(1)).save(householdMock);

    }

    @Test
    void removeHousehold() {
        Household household1 = new Household();
        household1.setId(1L);
        household1.setName("MijnHuis");
        household1.setAddress("Dam 1");
        household1.setPostalCode("1111AB");
        household1.setCountry("Zambia");

        householdService.removeHousehold(1L);
        verify(householdRepositoryMock, times(1)).deleteById(1L);
    }
}

package nl.jed.supersimplesupplysystem.controllers.household;

import nl.jed.supersimplesupplysystem.models.household.Household;
import nl.jed.supersimplesupplysystem.services.household.HouseholdService;
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


/**
 * @author joe.vrolijk
 * @since 27/02/2021
 */
class HouseholdControllerTest {

    @Mock
    HouseholdService householdServiceMock;

    @InjectMocks
    HouseholdController controller;

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

        when(householdServiceMock.getAllHouseholds()).thenReturn(households);

        List<Household> result = controller.getAllHouseholds();

        verify(householdServiceMock, times(1)).getAllHouseholds();
        assertThat(result.size(), is(2));
        assertThat(result, is(households));
        assertThat(result.get(0).getName(), is("MijnHuis"));
        assertThat(result.get(0).getId(), is(13L));
        assertThat(result.get(0).getAddress(), is("Dam 1"));
        assertThat(result.get(0).getCountry(), is("Zambia"));
        assertThat(result.get(0).getPostalCode(), is("1111AB"));

    }

    @Test
    void getHousehold() {
        Household household1 = new Household();
        household1.setId(1L);
        household1.setName("MijnHuis");
        household1.setAddress("Dam 1");
        household1.setPostalCode("1111AB");
        household1.setCountry("Zambia");

        when(householdServiceMock.getHousehold(1L)).thenReturn(household1);

        Household result = controller.getHousehold(1L);

        verify(householdServiceMock, times(1)).getHousehold(1L);
        assertThat(result.getName(), is("MijnHuis"));
    }

    @Test
    void deleteHousehold() {
        doNothing().when(householdServiceMock).removeHousehold(1L);
        ResponseEntity result = controller.deleteHousehold(1L);
        verify(householdServiceMock, times(1)).removeHousehold(1L);
        assertThat(result, is(ResponseEntity.ok(any())));
    }

    @Test
    void addHousehold() {
        Household householdMock = mock(Household.class);

        doNothing().when(householdServiceMock).addHousehold(any());
        ResponseEntity result = controller.addHousehold(householdMock);
        verify(householdServiceMock, times(1)).addHousehold(householdMock);
        assertThat(result, is(ResponseEntity.ok(any())));


    }
}

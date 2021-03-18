package nl.jed.supersimplesupplysystem;

import nl.jed.supersimplesupplysystem.dto.openfoodfacts.GetBrandsResponse;
import nl.jed.supersimplesupplysystem.dto.openfoodfacts.GetProductResponse;
import nl.jed.supersimplesupplysystem.repository.OpenFoodFactsRepository;
import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TempOntwikkeltest {

    OpenFoodFactsRepository openFoodFactsRepository;

    @BeforeAll
    public void setup() {
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(String.format("https://%s.openfoodfacts.org", "nl"))
            .addConverterFactory(GsonConverterFactory.create())
            .build();

        openFoodFactsRepository = retrofit.create(OpenFoodFactsRepository.class);
    }


    @Test
    public void getBrands() throws IOException {
        GetBrandsResponse brands = openFoodFactsRepository.getBrands().execute().body();
        System.out.println();
    }

    @Test
    public void getProduct() throws IOException {
        GetProductResponse product = openFoodFactsRepository.getProduct("8711200428953").execute().body();
        System.out.println();
    }

}

package nl.soffware.supersimplesupplysystem.configuration;

import nl.soffware.supersimplesupplysystem.repositories.OpenFoodFactsRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Configuration
public class RetrofitConfiguration {

    @Bean
    public OpenFoodFactsRepository ConfigureRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(String.format("https://%s.openfoodfacts.org", "en"))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(OpenFoodFactsRepository.class);
    }
}

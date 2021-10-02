package nl.soffware.supersimplesupplysystem.configuration;

import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;
import nl.soffware.supersimplesupplysystem.repositories.OpenFoodFactsRepository;
import org.springframework.context.annotation.Bean;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Auth0AdminAPIConfig {

    @Bean
    public OpenFoodFactsRepository ConfigureRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://supersimplesupplysystem.eu.auth0.com/oauth/token")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit.create(OpenFoodFactsRepository.class);
    }

    @Bean
    private String auth0AdminAccessToken() throws UnirestException {
        var response = Unirest.post("https://supersimplesupplysystem.eu.auth0.com/oauth/token")
                .header("content-type", "application/json")
                .body("{\"client_id\":\"y2wq4hQX82gpypnaRRrguGT9nAt5Jws3\",\"client_secret\":\"wWrHqjYrHKmjA6io6UIoh-P7d9wOpEmoSlejNQBpN6mv383qRa7Lf_-gBcpB-e2w\",\"audience\":\"https://supersimplesupplysystem.eu.auth0.com/api/v2/\",\"grant_type\":\"client_credentials\"}")
                .asJson();
        return response.getBody().getObject().getString("access_token");
    }
}

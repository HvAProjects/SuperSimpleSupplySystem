package nl.jed.supersimplesupplysystem.repository;

import nl.jed.supersimplesupplysystem.dto.openfoodfacts.GetBrandsResponse;
import nl.jed.supersimplesupplysystem.dto.openfoodfacts.GetCategoriesResponse;
import nl.jed.supersimplesupplysystem.dto.openfoodfacts.GetLabelsResponse;
import nl.jed.supersimplesupplysystem.dto.openfoodfacts.GetProductResponse;
import org.springframework.stereotype.Repository;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

@Repository
public interface OpenFoodFactsRepository {
    // https://auth0.com/blog/developing-a-restful-client-with-retrofit-and-spring-boot/
    @GET("/brands.json")
    Call<GetBrandsResponse> getBrands();

    @GET("/api/v0/product/{barcode}.json")
    Call<GetProductResponse> getProduct(@Path("barcode") String barcode);

    @GET("/labels.json")
    Call<GetLabelsResponse> getLabels();

    @GET("/categories.json")
    Call<GetCategoriesResponse> getCategories();
}

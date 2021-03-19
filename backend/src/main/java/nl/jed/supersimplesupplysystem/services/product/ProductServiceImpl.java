package nl.jed.supersimplesupplysystem.services.product;

import nl.jed.supersimplesupplysystem.dto.openfoodfacts.GetProductResponse;
import nl.jed.supersimplesupplysystem.models.ProductType;
import nl.jed.supersimplesupplysystem.repository.OpenFoodFactsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private OpenFoodFactsRepository openFoodFactsRepository;

    public ProductType getProductType(String barcode) throws IOException {
        GetProductResponse response = openFoodFactsRepository.getProduct(barcode).execute().body();
        return response.toProductType();
    }
}

package nl.jed.supersimplesupplysystem.services.product;

import nl.jed.supersimplesupplysystem.dto.openfoodfacts.GetProductResponse;
import nl.jed.supersimplesupplysystem.models.product.Product;
import nl.jed.supersimplesupplysystem.models.product.ProductType;
import nl.jed.supersimplesupplysystem.repository.OpenFoodFactsRepository;
import nl.jed.supersimplesupplysystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Autowired
    private OpenFoodFactsRepository openFoodFactsRepository;

    @Autowired
    private ProductRepository productRepository;

    public ProductType getProductType(String barcode) throws IOException {
        GetProductResponse response = openFoodFactsRepository.getProduct(barcode).execute().body();
        return response.toProductType();
    }

    public List<Product> getProducts(long locationId) {
        return productRepository.findByLocationId(locationId);
    }
}

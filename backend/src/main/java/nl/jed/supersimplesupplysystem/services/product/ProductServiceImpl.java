package nl.jed.supersimplesupplysystem.services.product;

import lombok.val;
import nl.jed.supersimplesupplysystem.dto.openfoodfacts.GetProductResponse;
import nl.jed.supersimplesupplysystem.models.product.Product;
import nl.jed.supersimplesupplysystem.models.product.ProductType;
import nl.jed.supersimplesupplysystem.repository.LocationRepository;
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

    @Autowired
    private LocationRepository locationRepository;

    public ProductType getProductType(String barcode) throws IOException {
        GetProductResponse response = openFoodFactsRepository.getProduct(barcode).execute().body();
        return response.toProductType();
    }

    public List<Product> getProducts(long locationId) {
        return productRepository.findByLocationId(locationId);
    }

    @Override
    public void addProduct(long locationId, Product product) throws Exception {
        val location = locationRepository.findById(locationId);
        if (location.isPresent()) {
            product.setLocation(location.get());
            productRepository.save(product);
        } else {
            throw new Exception("Location not found");
        }
    }
}

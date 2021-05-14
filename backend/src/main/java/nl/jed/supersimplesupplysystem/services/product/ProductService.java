package nl.jed.supersimplesupplysystem.services.product;

import nl.jed.supersimplesupplysystem.models.product.Product;
import nl.jed.supersimplesupplysystem.models.product.ProductType;
import org.apache.catalina.User;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

public interface ProductService {
    ProductType getProductType(String barcode) throws IOException;

    List<Product> getProductsByHousehold(long householdId);

    List<Product> getProducts(long locationId);

    void addProduct(long locationId, Product product) throws Exception;

    void deleteProducts(long productId, int amount) throws Exception;

    List<Product> getProductsWithBarcode(String barcode, long householdId);

    List<Product> getAllProducts(List<Long> households);
}

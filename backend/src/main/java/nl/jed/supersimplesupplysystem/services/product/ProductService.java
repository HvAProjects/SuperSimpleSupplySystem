package nl.jed.supersimplesupplysystem.services.product;

import nl.jed.supersimplesupplysystem.models.product.Product;
import nl.jed.supersimplesupplysystem.models.product.ProductType;
import org.apache.catalina.User;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductType getProductType(String barcode) throws IOException;

    List<Product> getProducts(long locationId);

    void addProduct(long locationId, Product product) throws Exception;

    void deleteProducts(long productId, int amount) throws Exception;

    List<Product> getProductsWithBarcode(String barcode, long householdId);
}

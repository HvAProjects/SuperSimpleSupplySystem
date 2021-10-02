package nl.soffware.supersimplesupplysystem.services.product;

import nl.soffware.supersimplesupplysystem.models.product.Product;
import nl.soffware.supersimplesupplysystem.models.product.ProductType;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductType getProductType(String barcode) throws IOException;

    List<Product> getProductsByHousehold(Long householdId);

    List<Product> getProducts(Long locationId);

    void addProduct(Long locationId, Product product) throws Exception;

    void deleteProducts(Long productId, int amount) throws Exception;

    List<Product> getProductsWithBarcode(String barcode, Long householdId);

    List<Product> getAllProducts(List<Long> households);
}

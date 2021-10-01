package nl.soffware.supersimplesupplysystem.services.product;

import nl.soffware.supersimplesupplysystem.models.product.Product;
import nl.soffware.supersimplesupplysystem.models.product.ProductType;

import java.io.IOException;
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

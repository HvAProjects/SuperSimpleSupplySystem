package nl.jed.supersimplesupplysystem.services.product;

import nl.jed.supersimplesupplysystem.models.product.Product;
import nl.jed.supersimplesupplysystem.models.product.ProductType;

import java.io.IOException;
import java.util.List;

public interface ProductService {
    ProductType getProductType(String barcode) throws IOException;

    List<Product> getProducts(long locationId);
}

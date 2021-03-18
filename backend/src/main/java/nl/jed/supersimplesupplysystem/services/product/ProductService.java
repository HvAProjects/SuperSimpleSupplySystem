package nl.jed.supersimplesupplysystem.services.product;

import nl.jed.supersimplesupplysystem.models.ProductType;

import java.io.IOException;

public interface ProductService {
    ProductType getProductType(String barcode) throws IOException;
}

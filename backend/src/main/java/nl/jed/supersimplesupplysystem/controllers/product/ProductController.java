package nl.jed.supersimplesupplysystem.controllers.product;

import lombok.extern.slf4j.Slf4j;
import nl.jed.supersimplesupplysystem.models.product.Product;
import nl.jed.supersimplesupplysystem.models.product.ProductType;
import nl.jed.supersimplesupplysystem.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{locationId}")
    public List<Product> getProducts(@PathVariable("locationId") long locationId) {
        return productService.getProducts(locationId);
    }

    @GetMapping("/productType/{barcode}")
    public ProductType getProductType(@PathVariable("barcode") String barcode) throws IOException {
        return productService.getProductType(barcode);
    }
}

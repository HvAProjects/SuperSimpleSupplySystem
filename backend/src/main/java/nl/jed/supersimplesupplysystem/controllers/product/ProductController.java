package nl.jed.supersimplesupplysystem.controllers.product;

import lombok.extern.slf4j.Slf4j;
import nl.jed.supersimplesupplysystem.models.ProductType;
import nl.jed.supersimplesupplysystem.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{barcode}")
    public ProductType GetProduct(@PathVariable("barcode") String barcode) throws IOException {
        return productService.getProductType(barcode);
    }
}

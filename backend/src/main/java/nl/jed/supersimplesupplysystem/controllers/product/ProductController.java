package nl.jed.supersimplesupplysystem.controllers.product;

import lombok.extern.slf4j.Slf4j;
import nl.jed.supersimplesupplysystem.dto.ApiResponse;
import nl.jed.supersimplesupplysystem.dto.DeleteProductsRequest;
import nl.jed.supersimplesupplysystem.dto.LocalUser;
import nl.jed.supersimplesupplysystem.models.product.Product;
import nl.jed.supersimplesupplysystem.models.product.ProductType;
import nl.jed.supersimplesupplysystem.services.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import retrofit2.http.Body;
import retrofit2.http.Path;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{locationId}")
    public List<Product> getProducts(@PathVariable("locationId") long locationId) {
        return productService.getProducts(locationId);
    }

    @GetMapping("/{householdId}/{barcode}")
    public List<Product> getProductsOfUserWithBarcode(@PathVariable("householdId") long householdId, @PathVariable("barcode") String barcode, Principal principal) {
        return productService.getProductsWithBarcode(barcode, householdId);
    }

    @GetMapping("/household/{householdId}")
    public List<Product> getProductsByHousehold(@PathVariable("householdId") long householdId) {
        return productService.getProductsByHousehold(householdId);
    }

    @GetMapping("/productType/{barcode}")
    public ProductType getProductType(@PathVariable("barcode") String barcode) throws IOException {
        return productService.getProductType(barcode);
    }

    @PostMapping("/{locationId}")
    public ResponseEntity<ApiResponse> addProduct(@PathVariable("locationId") long locationId, @RequestBody Product product) throws Exception {
        productService.addProduct(locationId, product);
        return ResponseEntity.ok(new ApiResponse(true, "Save product successful"));
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<ApiResponse> deleteProducts(@PathVariable("productId") long productId, @RequestBody DeleteProductsRequest request) throws Exception {
        productService.deleteProducts(productId, request.amount);
        return ResponseEntity.ok(new ApiResponse(true, "Products deleted successful"));
    }

    @GetMapping(value = "/", params = "households")
    @ResponseBody
    public List<Product> getAllProducts(@RequestParam List<Long> households) {
        return productService.getAllProducts(households);
    }

}

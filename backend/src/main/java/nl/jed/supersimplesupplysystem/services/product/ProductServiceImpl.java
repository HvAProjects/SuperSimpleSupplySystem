package nl.jed.supersimplesupplysystem.services.product;

import lombok.val;
import nl.jed.supersimplesupplysystem.dto.openfoodfacts.GetProductResponse;
import nl.jed.supersimplesupplysystem.models.location.Location;
import nl.jed.supersimplesupplysystem.models.product.Product;
import nl.jed.supersimplesupplysystem.models.product.ProductType;
import nl.jed.supersimplesupplysystem.repository.LocationRepository;
import nl.jed.supersimplesupplysystem.repository.OpenFoodFactsRepository;
import nl.jed.supersimplesupplysystem.repository.ProductRepository;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;

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
            val existingProduct = productRepository.findByBarcodeAndExpirationDate(product.getBarcode(), product.getExpirationDate());
            if (existingProduct.isPresent()) {
                existingProduct.get().setAmount(existingProduct.get().getAmount() + product.getAmount());
                existingProduct.get().setAddedDateTime(new Date());
                productRepository.save(existingProduct.get());
            } else {
                product.setLocation(location.get());
                product.setAddedDateTime(new Date());
                productRepository.save(product);
            }
        } else {
            throw new Exception("Location not found");
        }
    }

    @Override
    public void deleteProducts(long productId, int amount) throws Exception {
        val product = productRepository.findById(productId);
        if (product.isPresent()) {
            if (product.get().getAmount() <= amount) {
                productRepository.delete(product.get());
            } else {
                product.get().setAmount(product.get().getAmount() - amount);
                productRepository.save(product.get());
            }
        } else {
            throw new Exception("Product not found");
        }
    }

    @Override
    public List<Product> getProductsWithBarcode(String barcode, long householdId) {
        List<Product> products = new ArrayList<>();
        List<Location> locations = locationRepository.findByHouseholdId(householdId);
        for (Location location : locations) {
            for (Product product : productRepository.findByLocationId(location.getId())) {
                if (product.getBarcode().equals(barcode))
                {
                    products.add(product);
                }
            }
        }
        return products;
    }

    @Override
    public List<Product> getProductsByHousehold(long householdId) {
        return productRepository.findByLocation_Household_Id(householdId);
    }

    @Override
    public List<Product> getAllProducts(List<Long> households) {
        List<Product> allProducts = new ArrayList<>();
        for (val household: households) {
            val products = getProductsByHousehold(household);
            allProducts.addAll(products);
        }
        return allProducts;
    }

}

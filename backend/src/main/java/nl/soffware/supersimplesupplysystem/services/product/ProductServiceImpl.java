package nl.soffware.supersimplesupplysystem.services.product;

import lombok.RequiredArgsConstructor;
import lombok.val;
import nl.soffware.supersimplesupplysystem.dto.openfoodfacts.GetProductResponse;
import nl.soffware.supersimplesupplysystem.models.location.Location;
import nl.soffware.supersimplesupplysystem.models.product.Product;
import nl.soffware.supersimplesupplysystem.models.product.ProductType;
import nl.soffware.supersimplesupplysystem.repositories.HouseholdRepository;
import nl.soffware.supersimplesupplysystem.repositories.LocationRepository;
import nl.soffware.supersimplesupplysystem.repositories.OpenFoodFactsRepository;
import nl.soffware.supersimplesupplysystem.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final OpenFoodFactsRepository openFoodFactsRepository;

    private final ProductRepository productRepository;

    private final LocationRepository locationRepository;

    private final HouseholdRepository householdRepository;

    public ProductType getProductType(String barcode) throws IOException {
        GetProductResponse response = openFoodFactsRepository.getProduct(barcode).execute().body();
        return response.toProductType();
    }

    public List<Product> getProducts(Long locationId) {
        return productRepository.findByLocationId(locationId);
    }

    @Override
    public void addProduct(Long locationId, Product product) throws Exception {
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
    public void deleteProducts(Long productId, int amount) throws Exception {
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
    public List<Product> getProductsWithBarcode(String barcode, Long householdId) {
        List<Product> products = new ArrayList<>();
        List<Location> locations = locationRepository.findByTenantId(householdId.toString());
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
    public List<Product> getProductsByHousehold(Long householdId) {
        var household = householdRepository.getOne(householdId);
        return household.getLocations().stream().flatMap(loc -> productRepository.findByLocation(loc.getId()).stream()).collect(Collectors.toList());
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

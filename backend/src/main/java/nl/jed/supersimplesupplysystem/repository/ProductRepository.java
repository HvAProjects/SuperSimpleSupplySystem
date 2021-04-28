package nl.jed.supersimplesupplysystem.repository;

import nl.jed.supersimplesupplysystem.models.location.Location;
import nl.jed.supersimplesupplysystem.models.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByLocationId(long locationId);

    Optional<Product> findByBarcodeAndExpirationDate(String barcode, Date expirationDate);

    List<Product> findByBarcode(String barcode);
}

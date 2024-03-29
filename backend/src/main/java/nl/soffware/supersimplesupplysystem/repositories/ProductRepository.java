package nl.soffware.supersimplesupplysystem.repositories;

import nl.soffware.supersimplesupplysystem.models.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByLocationId(long locationId);

    List<Product> findByLocation(Long locationId);

    Optional<Product> findByBarcodeAndExpirationDate(String barcode, Date expirationDate);

    List<Product> findByExpirationDateBefore(Date date);
    List<Product> findByExpirationDateBetween(Date date1, Date date2);
}

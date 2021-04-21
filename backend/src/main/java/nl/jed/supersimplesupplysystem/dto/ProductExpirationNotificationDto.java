package nl.jed.supersimplesupplysystem.dto;

import lombok.Data;
import nl.jed.supersimplesupplysystem.models.product.Product;

@Data
public class ProductExpirationNotificationDto extends NotificationDto {
    private ProductDto product;
}

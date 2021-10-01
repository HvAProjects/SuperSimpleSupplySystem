package nl.soffware.supersimplesupplysystem.dto;

import lombok.Data;

@Data
public class ProductExpirationNotificationDto extends NotificationDto {
    private ProductDto product;
}

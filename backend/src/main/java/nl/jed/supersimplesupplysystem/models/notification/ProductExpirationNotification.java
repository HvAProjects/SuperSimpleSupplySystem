package nl.jed.supersimplesupplysystem.models.notification;

import lombok.Data;
import nl.jed.supersimplesupplysystem.dto.NotificationDto;
import nl.jed.supersimplesupplysystem.dto.ProductExpirationNotificationDto;
import nl.jed.supersimplesupplysystem.models.product.Product;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity
public class ProductExpirationNotification extends Notification {

    @OneToOne
    private Product product;

    public ProductExpirationNotificationDto getNotificationDto() {
        ProductExpirationNotificationDto productExpirationNotificationDto = new ProductExpirationNotificationDto();
        productExpirationNotificationDto.setId(super.getId());
        productExpirationNotificationDto.setProduct(this.product.getProductDto());
        productExpirationNotificationDto.setDate(super.getDate());
        productExpirationNotificationDto.setState(super.getState());
        productExpirationNotificationDto.setNotificationType(super.getNotificationType());
        return  productExpirationNotificationDto;
    }
}

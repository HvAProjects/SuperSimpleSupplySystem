package nl.soffware.supersimplesupplysystem.models.notification;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.soffware.supersimplesupplysystem.dto.ProductExpirationNotificationDto;
import nl.soffware.supersimplesupplysystem.models.product.Product;

import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
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

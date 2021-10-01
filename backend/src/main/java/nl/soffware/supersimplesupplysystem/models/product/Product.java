package nl.soffware.supersimplesupplysystem.models.product;

import lombok.Data;
import lombok.EqualsAndHashCode;
import nl.soffware.supersimplesupplysystem.dto.ProductDto;
import nl.soffware.supersimplesupplysystem.models.location.Location;

import javax.persistence.*;
import java.util.Date;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
public class Product extends ProductType {
    @Column
    private long amount;

    @Column
    private Date expirationDate;

    @Column
    private Date addedDateTime;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Location location;

    public ProductDto getProductDto() {
        return new ProductDto(super.getId(), super.getName(), super.getQuantity(), this.amount, this.expirationDate);
    }
}

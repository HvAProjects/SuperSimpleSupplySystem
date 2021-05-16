package nl.jed.supersimplesupplysystem.models.product;

import lombok.Data;
import nl.jed.supersimplesupplysystem.dto.ProductDto;
import nl.jed.supersimplesupplysystem.dto.UserDto;
import nl.jed.supersimplesupplysystem.models.location.Location;

import javax.persistence.*;
import java.util.Date;

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

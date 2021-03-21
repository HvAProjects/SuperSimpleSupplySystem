package nl.jed.supersimplesupplysystem.models.product;

import lombok.Data;
import nl.jed.supersimplesupplysystem.models.location.Location;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Date;

@Data
@Entity
public class Product extends ProductType {
    @Column
    private long amount;

    @Column
    private Date expirationDate;

    @ManyToOne
    private Location location;
}

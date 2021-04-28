package nl.jed.supersimplesupplysystem.models.location;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.jed.supersimplesupplysystem.models.household.Household;
import nl.jed.supersimplesupplysystem.models.product.ProductType;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String name;

    @Transient
    private long numberOfProducts = this.products == null ? 0 : this.products.size();

    @Column
    @OneToMany(cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
    private List<ProductType> products;

    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.EAGER)
    private Household household;
}

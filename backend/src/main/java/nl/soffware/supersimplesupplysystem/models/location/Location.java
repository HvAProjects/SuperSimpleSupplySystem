package nl.soffware.supersimplesupplysystem.models.location;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.soffware.supersimplesupplysystem.models.household.Household;
import nl.soffware.supersimplesupplysystem.models.household.TenantSupport;
import nl.soffware.supersimplesupplysystem.models.product.ProductType;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@FilterDef(name = "tenantFilter", parameters = {@ParamDef(name = "tenantId", type = "string")})
@Filter(name = "tenantFilter", condition = "tenant_id = :tenantId")
public class Location implements TenantSupport {
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

    @Transient
    @NotNull(message = "Household not set")
    @JsonIgnore
    private Household household;

    @Column(name = "household")
    private String tenantId;
}

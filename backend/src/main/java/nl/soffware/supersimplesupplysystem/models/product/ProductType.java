package nl.soffware.supersimplesupplysystem.models.product;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class ProductType {

    @Id
    @Column
    private Long id;

    @Column(name = "BARCODE")
    @ToString.Include
    private String barcode;

    @Column(name = "NAME")
    private String name;

    @Column(name = "QUANTITY")
    private String quantity;

    @Column(name = "BRANDS")
    private String brands;

    @Column(name = "IMAGE_URL")
    private String imageUrl;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        ProductType that = (ProductType) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return 537430631;
    }
}

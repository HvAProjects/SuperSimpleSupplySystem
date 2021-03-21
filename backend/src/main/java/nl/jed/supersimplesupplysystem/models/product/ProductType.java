package nl.jed.supersimplesupplysystem.models.product;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
public class ProductType {
    @Id
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
}

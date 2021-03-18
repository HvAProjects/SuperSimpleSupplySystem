package nl.jed.supersimplesupplysystem.models;

import lombok.Data;
import lombok.ToString;

import javax.persistence.*;

@Data
@Entity
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "BARCODE")
    @ToString.Include
    private Long barcode;

    @Column(name = "NAME")
    private String name;

    @Column(name = "UNIT")
    private String unit;

    @Column(name = "UNIT_AMOUNT")
    private int unit_amount;
}

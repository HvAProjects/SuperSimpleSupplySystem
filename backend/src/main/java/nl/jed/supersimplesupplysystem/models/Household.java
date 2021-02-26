package nl.jed.supersimplesupplysystem.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author joe.vrolijk
 * @since 26/02/2021
 */
@Entity
@Data
@NoArgsConstructor
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    private Long id;

    @Column
    private String name;

    @Column
    private String address;

    @Column
    private String postalCode;

    @Column
    private String country;

}

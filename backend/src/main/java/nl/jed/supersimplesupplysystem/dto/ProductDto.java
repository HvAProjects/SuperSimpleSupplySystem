package nl.jed.supersimplesupplysystem.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String name;
    private String quantity;
    private long amount;
    private Date expirationDate;
}

package nl.soffware.supersimplesupplysystem.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

/**
 * The persistent class for the user database table.
 *
 */
@Entity
@NoArgsConstructor
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USER_ID")
    private Long id;

    @Column(name = "PROVIDER_USER_ID")
    private String providerUserId;

    private String email;

    private Set<Role> roles;

    public String getDisplayName() {
        return email;
    }
}

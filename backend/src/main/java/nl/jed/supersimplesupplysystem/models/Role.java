package nl.jed.supersimplesupplysystem.models;
import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.*;

/**
 * The persistent class for the role database table.
 *
 */
@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString(onlyExplicitlyIncluded = true)
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    public static final String USER = "USER";
    public static final String ROLE_USER = "ROLE_USER";
    public static final String ROLE_ADMIN = "ROLE_ADMIN";
    public static final String ROLE_MODERATOR = "ROLE_MODERATOR";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ROLE_ID")
    @ToString.Include
    private Long roleId;

    @EqualsAndHashCode.Include
    @ToString.Include
    @NonNull
    private String name;

    // bi-directional many-to-many association to User
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;


}

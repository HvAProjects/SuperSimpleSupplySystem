package nl.soffware.supersimplesupplysystem.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

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
public class Role implements Serializable, GrantedAuthority {
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
    @JsonBackReference
    @ManyToMany(mappedBy = "roles")
    private Set<User> users;


    @Override
    public String getAuthority() {
        return name;
    }
}

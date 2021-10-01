package nl.soffware.supersimplesupplysystem.models.household;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import nl.soffware.supersimplesupplysystem.models.User;
import nl.soffware.supersimplesupplysystem.models.location.Location;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Household {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "HOUSEHOLD_ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ADDRESS")
    private String address;

    @Column(name = "POSTAL_CODE")
    private String postalCode;

    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;


    @JsonBackReference
    @ManyToMany
    @ToString.Exclude
    private Set<User> users;

    @OneToMany
    @ToString.Exclude
    private List<Location> locations;

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(Long userID) {
        users = users.stream().filter(u -> u.getId() != userID).collect(Collectors.toSet());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Household household = (Household) o;

        return Objects.equals(id, household.id);
    }

    @Override
    public int hashCode() {
        return 335815920;
    }
}

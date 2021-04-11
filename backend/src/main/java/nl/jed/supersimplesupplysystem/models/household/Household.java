package nl.jed.supersimplesupplysystem.models.household;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.val;
import nl.jed.supersimplesupplysystem.models.User;

import javax.naming.Name;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SecondaryTable;
import java.security.Principal;
import java.util.List;
import java.util.Set;

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


    @ManyToMany
    @JsonBackReference
//    @JoinTable(name = "household_users", joinColumns = {@JoinColumn(name = "HOUSEHOLD_ID")}, inverseJoinColumns = {@JoinColumn(name = "USER_ID")})
    private Set<User> users;


    public boolean hasAccess(String username){
        for (User user: this.users) {
            if (user.getEmail().equals(username)){
                return true;
            }
        }
        return false;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

}

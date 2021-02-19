package nl.jed.supersimplesupplysystem.models;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.beans.ConstructorProperties;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

@Entity
@NoArgsConstructor
public class PasswordResetToken implements Serializable {

    private static final int EXPIRATION = 60 * 24;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Setter @Getter
    private String token;

    @Setter @Getter
    @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "USER_ID")
    private User user;

    @Getter
    private Date expiryDate = getExpiryDate();

    private Date getExpiryDate() {
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.HOUR_OF_DAY, EXPIRATION);
        return calendar.getTime();
    }
}

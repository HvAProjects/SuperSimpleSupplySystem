package nl.jed.supersimplesupplysystem.models.notification;

import lombok.Data;
import nl.jed.supersimplesupplysystem.models.household.Household;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

@Data
@Entity
public class HouseholdInvitationNotification extends Notification {

    @OneToOne
    private Household household;
}

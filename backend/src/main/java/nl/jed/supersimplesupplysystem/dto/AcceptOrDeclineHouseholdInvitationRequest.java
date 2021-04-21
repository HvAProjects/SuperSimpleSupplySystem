package nl.jed.supersimplesupplysystem.dto;

import lombok.Data;

@Data
public class AcceptOrDeclineHouseholdInvitationRequest {
    private long notificationId;
    private boolean accept;
}

package nl.soffware.supersimplesupplysystem.dto;

import lombok.Data;

@Data
public class InviteUserToHouseholdRequest {
    public String emailAddress;
    public int householdId;
}

package nl.soffware.supersimplesupplysystem.controllers.notification;


import lombok.extern.slf4j.Slf4j;
import nl.soffware.supersimplesupplysystem.models.notification.Notification;
import nl.soffware.supersimplesupplysystem.services.notification.NotificationService;
import nl.soffware.supersimplesupplysystem.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/notifications")
public class NotificationController {

    @Autowired
    private NotificationService notificationService;

    @GetMapping("/householdInvitations")
    public List<NotificationDto> getNotifications(Principal principal) {
        return notificationService.getNotifications(principal.getName()).stream().map(Notification::getNotificationDto).collect(Collectors.toList());
    }

    @PostMapping("/inviteToHousehold")
    public ResponseEntity<ApiResponse> inviteToHousehold(@RequestBody InviteUserToHouseholdRequest request, Principal principal)  {
        notificationService.inviteUserToHousehold(request.getEmailAddress(), request.getHouseholdId(), principal.getName());
        return ResponseEntity.ok(new ApiResponse(true, "An invitation was sent to the user"));
    }

    @PostMapping("/setNotificationsSeen")
    public List<NotificationDto> setNotificationsSeen(Principal principal) {
        return notificationService.setNotificationsSeen(principal.getName()).stream().map(Notification::getNotificationDto).collect(Collectors.toList());
    }

    @PostMapping("/acceptOrDeclineHouseholdInvitation")
    public List<NotificationDto> acceptOrDeclineHouseholdInvitation(@RequestBody AcceptOrDeclineHouseholdInvitationRequest request, Principal principal) {
        return notificationService.acceptOrDeclineHouseholdInvitation(request.getNotificationId(), principal.getName(), request.isAccept()).stream().map(Notification::getNotificationDto).collect(Collectors.toList());
    }

}

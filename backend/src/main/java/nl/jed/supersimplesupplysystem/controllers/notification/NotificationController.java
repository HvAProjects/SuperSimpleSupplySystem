package nl.jed.supersimplesupplysystem.controllers.notification;


import lombok.extern.slf4j.Slf4j;
import nl.jed.supersimplesupplysystem.dto.*;
import nl.jed.supersimplesupplysystem.models.notification.Notification;
import nl.jed.supersimplesupplysystem.services.notification.NotificationService;
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
        LocalUser localUser = (LocalUser) ((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        return notificationService.getNotifications(localUser.getUser().getEmail()).stream().map(Notification::getNotificationDto).collect(Collectors.toList());
    }

    @PostMapping("/inviteToHousehold")
    public ResponseEntity<ApiResponse> inviteToHousehold(@RequestBody InviteUserToHouseholdRequest request, Principal principal) throws Exception {
        LocalUser localUser = (LocalUser) ((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        notificationService.inviteUserToHousehold(request.getEmailAddress(), request.getHouseholdId(), localUser.getUser());
        return ResponseEntity.ok(new ApiResponse(true, "An invitation was sent to the user"));
    }

    @PostMapping("/setNotificationsSeen")
    public List<NotificationDto> setNotificationsSeen(Principal principal) {
        LocalUser localUser = (LocalUser) ((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        return notificationService.setNotificationsSeen(localUser.getUser()).stream().map(Notification::getNotificationDto).collect(Collectors.toList());
    }

    @PostMapping("/acceptOrDeclineHouseholdInvitation")
    public List<NotificationDto> acceptOrDeclineHouseholdInvitation(@RequestBody AcceptOrDeclineHouseholdInvitationRequest request, Principal principal) {
        LocalUser localUser = (LocalUser) ((UsernamePasswordAuthenticationToken)principal).getPrincipal();
        return notificationService.acceptOrDeclineHouseholdInvitation(request.getNotificationId(), localUser.getUser(), request.isAccept()).stream().map(Notification::getNotificationDto).collect(Collectors.toList());
    }

}

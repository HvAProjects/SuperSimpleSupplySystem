package nl.jed.supersimplesupplysystem.util;

import nl.jed.supersimplesupplysystem.models.User;
import nl.jed.supersimplesupplysystem.models.notification.NotificationState;
import nl.jed.supersimplesupplysystem.models.notification.NotificationType;
import nl.jed.supersimplesupplysystem.models.notification.ProductExpirationNotification;
import nl.jed.supersimplesupplysystem.models.product.Product;
import nl.jed.supersimplesupplysystem.repository.NotificationRepository;
import nl.jed.supersimplesupplysystem.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Component
public class ScheduledTasks implements ApplicationListener<ContextRefreshedEvent> {

    private final int ALMOST_EXPIRED_DAYS = 3;

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        // Initiele aanroepen, zodat ze ook bij het opstarten uitgevoerd worden

        sendProductExpirationNotifications();
    }

    // Elke dag om 00:01
    @Scheduled(cron = "1 0 * * * *")
    @Transactional
    public void sendProductExpirationNotifications() {
        LocalDate now = LocalDate.now();
        LocalDate almostExpiredDate = LocalDate.now().plusDays(ALMOST_EXPIRED_DAYS);
        List<Product> expiredProducts = productRepository.findByExpirationDateBefore(java.sql.Date.valueOf(now));
        List<Product> almostExpiredProducts = productRepository.findByExpirationDateBetween(java.sql.Date.valueOf(now), java.sql.Date.valueOf(almostExpiredDate));

        for (Product product : expiredProducts) {
            Set<User> users = product.getLocation().getHousehold().getUsers();
            for (User user : users) {
                ProductExpirationNotification notification = new ProductExpirationNotification();
                notification.setNotificationType(NotificationType.productExpired);
                notification.setDate(new Date());
                notification.setUserEmail(user.getEmail());
                notification.setProduct(product);
                notification.setState(NotificationState.unseen);
                notificationRepository.save(notification);
            }
        }

        for (Product product : almostExpiredProducts) {
            Set<User> users = product.getLocation().getHousehold().getUsers();
            for (User user : users) {
                ProductExpirationNotification notification = new ProductExpirationNotification();
                notification.setNotificationType(NotificationType.productAboutToExpire);
                notification.setDate(new Date());
                notification.setUserEmail(user.getEmail());
                notification.setProduct(product);
                notification.setState(NotificationState.unseen);
                notificationRepository.save(notification);
            }
        }
    }
}

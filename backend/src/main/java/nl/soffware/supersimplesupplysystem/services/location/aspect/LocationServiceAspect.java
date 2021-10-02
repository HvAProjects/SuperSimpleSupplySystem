package nl.soffware.supersimplesupplysystem.services.location.aspect;

import lombok.extern.slf4j.Slf4j;
import nl.soffware.supersimplesupplysystem.context.TenantContext;
import nl.soffware.supersimplesupplysystem.services.location.LocationService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class LocationServiceAspect {

    @Before("target(locationService)")
    public void aroundExecution(JoinPoint pjp, LocationService locationService) throws Throwable {
        org.hibernate.Filter filter = locationService.entityManager.unwrap(Session.class).enableFilter("tenantFilter");
        filter.setParameter("tenantId", TenantContext.getCurrentTenant());
        filter.validate();
    }
}

package nl.soffware.supersimplesupplysystem.configuration;

import lombok.extern.slf4j.Slf4j;
import nl.soffware.supersimplesupplysystem.context.TenantContext;
import nl.soffware.supersimplesupplysystem.models.household.TenantSupport;
import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

@Slf4j
@Configuration
public class TenantInterceptorConfiguration {
    @Bean
    public EmptyInterceptor hibernateInterceptor() {
        return new EmptyInterceptor() {

            @Override
            public boolean onSave(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
                if (entity instanceof TenantSupport) {
                    log.debug("[save] Updating the entity " + id + " with util information: " + TenantContext.getCurrentTenant());
                    ((TenantSupport) entity).setTenantId(TenantContext.getCurrentTenant());
                }
                return false;
            }

            @Override
            public void onDelete(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
                if (entity instanceof TenantSupport) {
                    log.debug("[delete] Updating the entity " + id + " with util information: " + TenantContext.getCurrentTenant());
                    ((TenantSupport) entity).setTenantId(TenantContext.getCurrentTenant());
                }
            }

            @Override
            public boolean onFlushDirty(Object entity, Serializable id, Object[] currentState, Object[] previousState, String[] propertyNames, Type[] types) {
                if (entity instanceof TenantSupport) {
                    log.debug("[flush-dirty] Updating the entity " + id + " with util information: " + TenantContext.getCurrentTenant());
                    ((TenantSupport) entity).setTenantId(TenantContext.getCurrentTenant());
                }
                return false;
            }

        };
    }

}

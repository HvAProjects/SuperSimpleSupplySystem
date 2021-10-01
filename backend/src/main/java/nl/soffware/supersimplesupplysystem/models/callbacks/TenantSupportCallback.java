package nl.soffware.supersimplesupplysystem.models.callbacks;

import nl.soffware.supersimplesupplysystem.context.TenantContext;
import nl.soffware.supersimplesupplysystem.models.household.TenantSupport;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.event.BeforeConvertCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class TenantSupportCallback implements BeforeConvertCallback<TenantSupport>{
    @Override
    public Publisher<TenantSupport> onBeforeConvert(TenantSupport entity, SqlIdentifier table) {
        entity.setTenantId(TenantContext.getCurrentTenant());
        return Mono.just(entity);
    }


}

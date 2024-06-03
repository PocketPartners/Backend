package fairfinance.pocketpartners.backend.groups.interfaces.rest.transform;

import fairfinance.pocketpartners.backend.groups.domain.model.aggregates.GroupOperation;
import fairfinance.pocketpartners.backend.groups.interfaces.rest.resources.GroupOperationResource;

public class GroupOperationResourceFromEntityAssembler {
    public static GroupOperationResource toResourceFromEntity(GroupOperation groupOperation) {
        return new GroupOperationResource(groupOperation.getId(), groupOperation.getGroup().getId(), groupOperation.getExpense().getId(), groupOperation.getPayment().getId());
    }
}

package fairfinance.pocketpartners.backend.groups.application.internal.queryservices;

import fairfinance.pocketpartners.backend.groups.domain.model.aggregates.GroupOperation;
import fairfinance.pocketpartners.backend.groups.domain.model.queries.GetAllGroupOperationsByGroupIdQuery;
import fairfinance.pocketpartners.backend.groups.domain.model.queries.GetAllGroupOperationsQuery;
import fairfinance.pocketpartners.backend.groups.domain.model.queries.GetGroupOperationByGroupIdAndExpenseIdAndPaymentId;
import fairfinance.pocketpartners.backend.groups.domain.model.queries.GetGroupOperationByIdQuery;
import fairfinance.pocketpartners.backend.groups.domain.services.GroupOperationQueryService;
import fairfinance.pocketpartners.backend.groups.infrastructure.persistence.jpa.repositories.GroupOperationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GroupOperationQueryServiceImpl implements GroupOperationQueryService {
    private final GroupOperationRepository groupOperationRepository;

    public GroupOperationQueryServiceImpl(GroupOperationRepository groupOperationRepository) {
        this.groupOperationRepository = groupOperationRepository;
    }

    @Override
    public List<GroupOperation> handle(GetAllGroupOperationsQuery query) {return groupOperationRepository.findAll();}

    @Override
    public Optional<GroupOperation> handle(GetGroupOperationByIdQuery query) {
        return groupOperationRepository.findById(query.groupOperationId());
    }

    @Override
    public List<GroupOperation> handle(GetAllGroupOperationsByGroupIdQuery query) {
        return groupOperationRepository.findAllByGroupId(query.groupId());
    }

    @Override
    public Optional<GroupOperation> handle(GetGroupOperationByGroupIdAndExpenseIdAndPaymentId query){
        return groupOperationRepository.findByGroupIdAndExpenseIdAndPaymentId(query.groupId(), query.expenseId(), query.paymentId());
    }
}

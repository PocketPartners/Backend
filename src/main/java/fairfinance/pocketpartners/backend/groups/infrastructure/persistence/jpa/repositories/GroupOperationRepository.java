package fairfinance.pocketpartners.backend.groups.infrastructure.persistence.jpa.repositories;

import fairfinance.pocketpartners.backend.groups.domain.model.aggregates.GroupOperation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupOperationRepository extends JpaRepository<GroupOperation, Long> {
    List<GroupOperation> findAllByGroupId(Long groupId);
    Optional<GroupOperation> findByGroupIdAndExpenseIdAndPaymentId(Long groupId, Long expenseId, Long paymentId);
}

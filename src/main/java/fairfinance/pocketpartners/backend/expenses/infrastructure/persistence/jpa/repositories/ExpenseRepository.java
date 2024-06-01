package fairfinance.pocketpartners.backend.expenses.infrastructure.persistence.jpa.repositories;

import fairfinance.pocketpartners.backend.expenses.domain.model.aggregates.Expense;
import fairfinance.pocketpartners.backend.expenses.domain.model.valueobjects.ExpenseName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> findByName(ExpenseName name);
}

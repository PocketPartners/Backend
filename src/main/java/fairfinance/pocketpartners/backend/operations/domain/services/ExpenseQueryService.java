package fairfinance.pocketpartners.backend.operations.domain.services;

import fairfinance.pocketpartners.backend.operations.domain.model.aggregates.Expense;
import fairfinance.pocketpartners.backend.operations.domain.model.queries.GetAllExpensesQuery;
import fairfinance.pocketpartners.backend.operations.domain.model.queries.GetExpenseByIdQuery;
import fairfinance.pocketpartners.backend.operations.domain.model.queries.GetExpenseByNameAndUserId;

import java.util.List;
import java.util.Optional;

public interface ExpenseQueryService {
    List<Expense> handle(GetAllExpensesQuery query);
    Optional<Expense> handle(GetExpenseByIdQuery query);
    Optional<Expense> handle(GetExpenseByNameAndUserId query);
}

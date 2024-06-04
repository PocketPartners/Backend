package fairfinance.pocketpartners.backend.operations.domain.services;

import fairfinance.pocketpartners.backend.operations.domain.model.aggregates.Expense;
import fairfinance.pocketpartners.backend.operations.domain.model.queries.GetAllExpensesQuery;
import fairfinance.pocketpartners.backend.operations.domain.model.queries.GetExpenseByIdQuery;
import fairfinance.pocketpartners.backend.operations.domain.model.queries.GetExpenseByNameAndUserIdQuery;
import fairfinance.pocketpartners.backend.operations.domain.model.queries.GetAllExpensesByUserIdQuery;

import java.util.List;
import java.util.Optional;

public interface ExpenseQueryService {
    List<Expense> handle(GetAllExpensesQuery query);
    Optional<Expense> handle(GetExpenseByIdQuery query);
    List<Expense> handle(GetAllExpensesByUserIdQuery query);
    Optional<Expense> handle(GetExpenseByNameAndUserIdQuery query);
}

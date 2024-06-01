package fairfinance.pocketpartners.backend.expenses.domain.services;

import fairfinance.pocketpartners.backend.expenses.domain.model.aggregates.Expense;
import fairfinance.pocketpartners.backend.expenses.domain.model.queries.GetAllExpensesQuery;
import fairfinance.pocketpartners.backend.expenses.domain.model.queries.GetExpenseByIdQuery;

import java.util.List;
import java.util.Optional;

public interface ExpenseQueryService {
    List<Expense> handle(GetAllExpensesQuery query);
    Optional<Expense> handle(GetExpenseByIdQuery query);
}

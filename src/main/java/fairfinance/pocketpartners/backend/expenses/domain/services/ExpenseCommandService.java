package fairfinance.pocketpartners.backend.expenses.domain.services;

import fairfinance.pocketpartners.backend.expenses.domain.model.aggregates.Expense;
import fairfinance.pocketpartners.backend.expenses.domain.model.commands.CreateExpenseCommand;
import java.util.Optional;

public interface ExpenseCommandService {
    Optional<Expense> handle(CreateExpenseCommand command);
}

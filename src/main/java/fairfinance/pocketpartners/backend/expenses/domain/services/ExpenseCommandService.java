package fairfinance.pocketpartners.backend.expenses.domain.services;

import fairfinance.pocketpartners.backend.expenses.domain.model.aggregates.Expense;
import fairfinance.pocketpartners.backend.expenses.domain.model.commands.CreateExpenseCommand;
import fairfinance.pocketpartners.backend.expenses.domain.model.commands.UpdateExpenseCommand;

import java.util.Optional;

public interface ExpenseCommandService {
    Long handle(CreateExpenseCommand command);
    Optional<Expense> handle(UpdateExpenseCommand command);
}

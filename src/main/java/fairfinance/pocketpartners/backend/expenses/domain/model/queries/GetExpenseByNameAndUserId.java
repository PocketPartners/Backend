package fairfinance.pocketpartners.backend.expenses.domain.model.queries;

import fairfinance.pocketpartners.backend.expenses.domain.model.valueobjects.ExpenseName;

public record GetExpenseByNameAndUserId(ExpenseName expenseName, Long userId) {
}

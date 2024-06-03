package fairfinance.pocketpartners.backend.operations.domain.model.queries;

import fairfinance.pocketpartners.backend.operations.domain.model.valueobjects.ExpenseName;

public record GetExpenseByNameAndUserId(ExpenseName expenseName, Long userId) {
}

package fairfinance.pocketpartners.backend.operations.domain.model.queries;

import fairfinance.pocketpartners.backend.operations.domain.model.valueobjects.ExpenseName;

public record GetExpenseByNameAndUserIdQuery(ExpenseName expenseName, Long userId) {
}

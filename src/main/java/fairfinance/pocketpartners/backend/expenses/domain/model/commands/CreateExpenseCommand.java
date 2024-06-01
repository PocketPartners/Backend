package fairfinance.pocketpartners.backend.expenses.domain.model.commands;

import java.math.BigDecimal;

public record CreateExpenseCommand(String name, BigDecimal amount) {
}

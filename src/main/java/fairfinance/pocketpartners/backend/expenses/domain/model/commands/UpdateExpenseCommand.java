package fairfinance.pocketpartners.backend.expenses.domain.model.commands;

import java.math.BigDecimal;

public record UpdateExpenseCommand(Long id, String name, BigDecimal amount) {
}
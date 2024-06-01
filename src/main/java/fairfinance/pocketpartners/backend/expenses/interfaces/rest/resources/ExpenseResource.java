package fairfinance.pocketpartners.backend.expenses.interfaces.rest.resources;

import java.math.BigDecimal;

public record ExpenseResource(Long id,
                              String name,
                              BigDecimal amount) {
}

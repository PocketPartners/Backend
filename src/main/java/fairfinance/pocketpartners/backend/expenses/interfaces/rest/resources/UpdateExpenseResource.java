package fairfinance.pocketpartners.backend.expenses.interfaces.rest.resources;

import java.math.BigDecimal;

public record UpdateExpenseResource(String name, BigDecimal amount) {
}

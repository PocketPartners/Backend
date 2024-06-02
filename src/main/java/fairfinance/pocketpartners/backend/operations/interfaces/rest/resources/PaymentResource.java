package fairfinance.pocketpartners.backend.operations.interfaces.rest.resources;

import java.math.BigDecimal;

public record PaymentResource(Long id,
                              String description,
                              BigDecimal amount,
                              Long userId,
                              Long expenseId) {
}

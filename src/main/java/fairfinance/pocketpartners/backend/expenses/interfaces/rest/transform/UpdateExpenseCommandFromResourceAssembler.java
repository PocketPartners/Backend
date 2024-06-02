package fairfinance.pocketpartners.backend.expenses.interfaces.rest.transform;

import fairfinance.pocketpartners.backend.expenses.domain.model.commands.UpdateExpenseCommand;
import fairfinance.pocketpartners.backend.expenses.interfaces.rest.resources.UpdateExpenseResource;

public class UpdateExpenseCommandFromResourceAssembler {
    public static UpdateExpenseCommand toCommandFromResource(Long expenseId, UpdateExpenseResource resource) {
        return new UpdateExpenseCommand(expenseId, resource.name(), resource.amount());
    }
}

package fairfinance.pocketpartners.backend.expenses.interfaces.rest.transform;

import fairfinance.pocketpartners.backend.expenses.domain.model.aggregates.Expense;
import fairfinance.pocketpartners.backend.expenses.interfaces.rest.resources.ExpenseResource;

public class ExpenseResourceFromEntityAssembler {
    public static ExpenseResource toResourceFromEntity(Expense expense){
        return new ExpenseResource(expense.getId(), expense.getName(), expense.getAmount());
    }
}

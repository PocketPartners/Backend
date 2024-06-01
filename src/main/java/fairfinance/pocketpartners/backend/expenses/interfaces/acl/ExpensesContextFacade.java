package fairfinance.pocketpartners.backend.expenses.interfaces.acl;

import fairfinance.pocketpartners.backend.expenses.domain.model.commands.CreateExpenseCommand;
import fairfinance.pocketpartners.backend.expenses.domain.services.ExpenseCommandService;
import fairfinance.pocketpartners.backend.expenses.domain.services.ExpenseQueryService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ExpensesContextFacade {

    private final ExpenseQueryService expenseQueryService;
    private final ExpenseCommandService expenseCommandService;

    public ExpensesContextFacade(ExpenseQueryService expenseQueryService, ExpenseCommandService expenseCommandService) {
        this.expenseQueryService = expenseQueryService;
        this.expenseCommandService = expenseCommandService;
    }

    public Long createExpense(String name, BigDecimal amount) {
        var createExpenseCommand = new CreateExpenseCommand(name, amount);
        var expense = expenseCommandService.handle(createExpenseCommand);
        if(expense.isEmpty()) return 0L;
        return expense.get().getId();
    }
}

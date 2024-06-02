package fairfinance.pocketpartners.backend.expenses.application.internal.commandservices;

import fairfinance.pocketpartners.backend.expenses.domain.model.aggregates.Expense;
import fairfinance.pocketpartners.backend.expenses.domain.model.commands.CreateExpenseCommand;
import fairfinance.pocketpartners.backend.expenses.domain.model.valueobjects.ExpenseName;
import fairfinance.pocketpartners.backend.expenses.domain.services.ExpenseCommandService;
import fairfinance.pocketpartners.backend.expenses.infrastructure.persistence.jpa.repositories.ExpenseRepository;
import fairfinance.pocketpartners.backend.users.domain.model.aggregates.User;
import fairfinance.pocketpartners.backend.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseCommandServiceImpl implements ExpenseCommandService {

    private final ExpenseRepository expenseRepository;
    private final UserRepository userRepository;

    public ExpenseCommandServiceImpl(ExpenseRepository expenseRepository, UserRepository userRepository) {
        this.expenseRepository = expenseRepository;
        this.userRepository = userRepository;
    }


    public Long handle(CreateExpenseCommand command) {
        Optional<User> user = userRepository.findById(command.userId());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        Expense expense = new Expense(command.name(), command.amount(), user.get());
        expenseRepository.save(expense);
        return expense.getId();
    }
}

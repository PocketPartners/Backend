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
        userRepository.findById(command.userId()).map(user -> {
            user = userRepository.findById(command.userId()).orElseThrow();
            Expense expenses = new Expense(command.name(), command.amount(), user);
            expenses = expenseRepository.save(expenses);
            return expenses.getId();
        }).orElseThrow(() -> new RuntimeException("Course not found"));
        return 0L;
    }
}

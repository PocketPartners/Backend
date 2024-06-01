package fairfinance.pocketpartners.backend.expenses.application.internal.commandservices;

import fairfinance.pocketpartners.backend.expenses.domain.model.aggregates.Expense;
import fairfinance.pocketpartners.backend.expenses.domain.model.commands.CreateExpenseCommand;
import fairfinance.pocketpartners.backend.expenses.domain.model.valueobjects.ExpenseName;
import fairfinance.pocketpartners.backend.expenses.domain.services.ExpenseCommandService;
import fairfinance.pocketpartners.backend.expenses.infrastructure.persistence.jpa.repositories.ExpenseRepository;
import fairfinance.pocketpartners.backend.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExpenseCommandServiceImpl implements ExpenseCommandService {

    private final ExpenseRepository expenseRepository;

    public ExpenseCommandServiceImpl(ExpenseRepository expenseRepository, UserRepository userRepository) {this.expenseRepository = expenseRepository;}

    @Override
    public Optional<Expense> handle(CreateExpenseCommand command) {
        var name = new ExpenseName(command.name());
        expenseRepository.findByName(name).map(expense -> {
            throw new IllegalArgumentException("Expense with name " + command.name() + " already exists");
        });
        var expense = new Expense(command);
        expenseRepository.save(expense);
        return Optional.of(expense);
    }
}

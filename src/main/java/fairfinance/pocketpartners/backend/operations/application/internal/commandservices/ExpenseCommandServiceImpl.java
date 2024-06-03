package fairfinance.pocketpartners.backend.operations.application.internal.commandservices;

import fairfinance.pocketpartners.backend.operations.domain.model.aggregates.Expense;
import fairfinance.pocketpartners.backend.operations.domain.model.commands.CreateExpenseCommand;
import fairfinance.pocketpartners.backend.operations.domain.model.commands.UpdateExpenseCommand;
import fairfinance.pocketpartners.backend.operations.domain.services.ExpenseCommandService;
import fairfinance.pocketpartners.backend.operations.infrastructure.persistence.jpa.repositories.ExpenseRepository;
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

    @Override
    public Long handle(CreateExpenseCommand command) {
        Optional<User> user = userRepository.findById(command.userId());
        if (user.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }
        Expense expense = new Expense(command.name(), command.amount(), user.get());
        expenseRepository.save(expense);
        return expense.getId();
    }

    @Override
    public Optional<Expense> handle(UpdateExpenseCommand command) {
        var result = expenseRepository.findById(command.id());
        if (result.isEmpty()) {throw new IllegalArgumentException("Expense not found");}
        var expenseToUpdate = result.get();
        try {
            var updateExpense = expenseRepository.save(expenseToUpdate.UpdateInformation(command.name(), command.amount()));
            return Optional.of(updateExpense);
        }catch (Exception e) {
            throw new IllegalArgumentException("Error while updating expense: " + e.getMessage());
        }
    }

}

package fairfinance.pocketpartners.backend.expenses.application.internal.queryservices;

import fairfinance.pocketpartners.backend.expenses.domain.model.aggregates.Expense;
import fairfinance.pocketpartners.backend.expenses.domain.model.queries.GetAllExpensesQuery;
import fairfinance.pocketpartners.backend.expenses.domain.model.queries.GetExpenseByIdQuery;
import fairfinance.pocketpartners.backend.expenses.domain.model.queries.GetExpenseByNameAndUserId;
import fairfinance.pocketpartners.backend.expenses.domain.services.ExpenseQueryService;
import fairfinance.pocketpartners.backend.expenses.infrastructure.persistence.jpa.repositories.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExpenseQueryServiceImpl implements ExpenseQueryService {

    private final ExpenseRepository expenseRepository;

    public ExpenseQueryServiceImpl(ExpenseRepository expenseRepository) {this.expenseRepository = expenseRepository;}

    @Override
    public List<Expense> handle(GetAllExpensesQuery query) {
        return expenseRepository.findAll();
    }

    @Override
    public Optional<Expense> handle(GetExpenseByIdQuery query){
        return expenseRepository.findById(query.expenseId());
    }

    @Override
    public Optional<Expense> handle(GetExpenseByNameAndUserId query){
        return expenseRepository.findByName(query.expenseName());
    }
}

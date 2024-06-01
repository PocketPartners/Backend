package fairfinance.pocketpartners.backend.expenses.domain.model.aggregates;

import fairfinance.pocketpartners.backend.expenses.domain.model.commands.CreateExpenseCommand;
import fairfinance.pocketpartners.backend.expenses.domain.model.valueobjects.Amount;
import fairfinance.pocketpartners.backend.expenses.domain.model.valueobjects.ExpenseName;
import fairfinance.pocketpartners.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

import java.math.BigDecimal;

@Entity
public class Expense extends AuditableAbstractAggregateRoot<Expense> {

    @Embedded
    private ExpenseName name;

    @Embedded
    private Amount amount;

    public Expense(String name, BigDecimal amount){
        this.name = new ExpenseName(name);
        this.amount = new Amount(amount);
    }

    public Expense(CreateExpenseCommand command){
        this.name = new ExpenseName(command.name());
        this.amount = new Amount(command.amount());
    }

    public Expense() {}

    public void UpdateExpenseName(String newName){this.name = new ExpenseName(newName);}

    public void UpdateAmount(BigDecimal newAmount){this.amount = new Amount(newAmount);}

    public String getName(){return name.getName();}

    public BigDecimal getAmount(){return amount.getAmount();}
}

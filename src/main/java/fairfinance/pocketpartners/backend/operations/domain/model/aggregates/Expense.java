package fairfinance.pocketpartners.backend.operations.domain.model.aggregates;

import fairfinance.pocketpartners.backend.groups.domain.model.aggregates.Group;
import fairfinance.pocketpartners.backend.operations.domain.model.valueobjects.Amount;
import fairfinance.pocketpartners.backend.operations.domain.model.valueobjects.ExpenseName;
import fairfinance.pocketpartners.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import fairfinance.pocketpartners.backend.users.domain.model.aggregates.User;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
public class Expense extends AuditableAbstractAggregateRoot<Expense> {

    @Embedded
    private ExpenseName name;

    @Embedded
    private Amount amount;

    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Getter
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    public Expense(String name, BigDecimal amount, User user, Group group){
        this.name = new ExpenseName(name);
        this.amount = new Amount(amount);
        this.user = user;
        this.group = group;
    }

    //public Expense(ExpenseName name, Amount amount, User user){
    //    this.name = name;
    //    this.amount = amount;
    //    this.userId = user;
    //}

    public Expense() {}

    public void UpdateExpenseName(String newName){this.name = new ExpenseName(newName);}

    public void UpdateAmount(BigDecimal newAmount){this.amount = new Amount(newAmount);}

    public Expense UpdateInformation(String newName, BigDecimal newAmount){
        this.name = new ExpenseName(newName);
        this.amount = new Amount(newAmount);
        return this;
    }

    public String getName(){return name.getName();}

    public BigDecimal getAmount(){return amount.getAmount();}

}

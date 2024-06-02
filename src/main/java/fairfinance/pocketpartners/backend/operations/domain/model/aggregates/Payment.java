package fairfinance.pocketpartners.backend.operations.domain.model.aggregates;

import fairfinance.pocketpartners.backend.operations.domain.model.valueobjects.Amount;
import fairfinance.pocketpartners.backend.operations.domain.model.valueobjects.Description;
import fairfinance.pocketpartners.backend.operations.domain.model.valueobjects.ExpenseName;
import fairfinance.pocketpartners.backend.operations.domain.model.valueobjects.PaymentStatus;
import fairfinance.pocketpartners.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import fairfinance.pocketpartners.backend.users.domain.model.aggregates.User;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

import java.math.BigDecimal;

@Entity
public class Payment extends AuditableAbstractAggregateRoot<Payment> {

    @Getter
    @Embedded
    private Description description;

    @Getter
    @Embedded
    private Amount amount;

    private PaymentStatus status;

    @Getter
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Getter
    @ManyToOne
    @JoinColumn(name = "expense_id")
    private Expense expense;

    public Payment() {}

    public Payment(String description, BigDecimal amount, User user, Expense expense) {
        this.description = new Description(description);
        this.amount = new Amount(amount);
        this.status = PaymentStatus.PENDING;
        this.user = user;
        this.expense = expense;
    }

    public Payment UpdateInformation(String newDescription, BigDecimal newAmount){
        this.description = new Description(newDescription);
        this.amount = new Amount(newAmount);
        return this;
    }

    public void completePayment(){
        this.status = PaymentStatus.COMPLETED;
    }

    public String getDescription() {return description.getDescription();}

    public BigDecimal getAmount(){return amount.getAmount();}

}

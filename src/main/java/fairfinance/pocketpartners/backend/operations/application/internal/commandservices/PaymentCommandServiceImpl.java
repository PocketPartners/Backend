package fairfinance.pocketpartners.backend.operations.application.internal.commandservices;

import fairfinance.pocketpartners.backend.operations.domain.exceptions.UserNotFoundException;
import fairfinance.pocketpartners.backend.operations.domain.model.aggregates.Payment;
import fairfinance.pocketpartners.backend.operations.domain.model.commands.CompletePaymentCommand;
import fairfinance.pocketpartners.backend.operations.domain.model.commands.CreatePaymentCommand;
import fairfinance.pocketpartners.backend.operations.domain.services.PaymentCommandService;
import fairfinance.pocketpartners.backend.operations.infrastructure.persistence.jpa.repositories.ExpenseRepository;
import fairfinance.pocketpartners.backend.operations.infrastructure.persistence.jpa.repositories.PaymentRepository;
import fairfinance.pocketpartners.backend.users.domain.model.aggregates.UserInformation;
import fairfinance.pocketpartners.backend.users.infrastructure.persistence.jpa.repositories.UserInformationRepository;
import org.springframework.stereotype.Service;

@Service
public class PaymentCommandServiceImpl implements PaymentCommandService {
    private final PaymentRepository paymentRepository;
    private final UserInformationRepository userInformationRepository;
    private final ExpenseRepository expenseRepository;

    public PaymentCommandServiceImpl(PaymentRepository paymentRepository, UserInformationRepository userInformationRepository, ExpenseRepository expenseRepository) {
        this.paymentRepository = paymentRepository;
        this.userInformationRepository = userInformationRepository;
        this.expenseRepository = expenseRepository;
    }

    public Long handle(CreatePaymentCommand command) {
        expenseRepository.findById(command.expenseId()).map(expense -> {
            UserInformation userInformation = userInformationRepository.findById(command.userId()).orElseThrow(() -> new UserNotFoundException(command.userId()));
            Payment payment = new Payment(command.description(), command.amount(), userInformation, expense);
            payment = paymentRepository.save(payment);
            return payment.getId();
        }).orElseThrow(() -> new RuntimeException("User not found"));
        return 0L;
    }

    @Override
    public Long handle(CompletePaymentCommand command){
        paymentRepository.findById(command.paymentId()).map(payment -> {
            payment.completePayment();
            paymentRepository.save(payment);
            return command.paymentId();
        }).orElseThrow(() -> new RuntimeException("Payment not found"));
        return null;
    }
}

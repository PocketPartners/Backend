package fairfinance.pocketpartners.backend.users.domain.model.aggregates;

import fairfinance.pocketpartners.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import fairfinance.pocketpartners.backend.users.domain.model.commands.CreateUserCommand;
import fairfinance.pocketpartners.backend.users.domain.model.valueobjects.EmailAddress;
import fairfinance.pocketpartners.backend.users.domain.model.valueobjects.Password;
import fairfinance.pocketpartners.backend.users.domain.model.valueobjects.PersonName;
import fairfinance.pocketpartners.backend.users.domain.model.valueobjects.PhoneNumber;
import jakarta.persistence.Embedded;

public class User extends AuditableAbstractAggregateRoot<User> {

    @Embedded
    private PersonName name;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    EmailAddress email;

    @Embedded
    private Password password;

    public User(String firstName, String lastName, String phoneNumber, String email, String password) {
        this.name = new PersonName(firstName, lastName);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.email = new EmailAddress(email);
        this.password = new Password(password);
    }

    public User(CreateUserCommand command) {
        this.name = new PersonName(command.firstName(), command.lastName());
        this.phoneNumber = new PhoneNumber(command.phoneNumber());
        this.email = new EmailAddress(command.email());
        this.password = new Password(command.password());
    }

    public User() {
    }

    public void updateName(String firstName, String lastName) {
        this.name = new PersonName(firstName, lastName);
    }

    public void updatePhoneNumber(String phoneNumber) {
        this.phoneNumber = new PhoneNumber(phoneNumber);
    }

    public void updateEmail(String email) {
        this.email = new EmailAddress(email);
    }

    public void updatePassword(String password) {
        this.password = new Password(password);
    }

    public String getFullName() {
        return name.getFullName();
    }

    public String getEmailAddress() {
        return email.email();
    }

    public String getPhoneNumber() {
        return phoneNumber.getPhoneNumber();
    }

    public String getPassword() {
        return password.getPassword();
    }

}

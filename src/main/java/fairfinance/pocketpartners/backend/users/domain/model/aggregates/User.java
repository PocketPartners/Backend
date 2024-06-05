package fairfinance.pocketpartners.backend.users.domain.model.aggregates;

import fairfinance.pocketpartners.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import fairfinance.pocketpartners.backend.users.domain.model.commands.CreateUserCommand;
import fairfinance.pocketpartners.backend.users.domain.model.valueobjects.*;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;

@Entity
public class User extends AuditableAbstractAggregateRoot<User> {

    @Embedded
    private PersonName name;

    @Embedded
    private PhoneNumber phoneNumber;

    @Embedded
    private Photo photo;

    @Embedded
    EmailAddress email;

    @Embedded
    private Password password;

    public User(String firstName, String lastName, String phoneNumber,String photo, String email, String password) {
        this.name = new PersonName(firstName, lastName);
        this.phoneNumber = new PhoneNumber(phoneNumber);
        this.photo = new Photo(photo);
        this.email = new EmailAddress(email);
        this.password = new Password(password);
    }

    public User(CreateUserCommand command) {
        this.name = new PersonName(command.firstName(), command.lastName());
        this.phoneNumber = new PhoneNumber(command.phoneNumber());
        this.photo = new Photo(command.photo());
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

    public void updatePhoto(String photo) {
        this.photo = new Photo(photo);
    }

    public void updateEmail(String email) {
        this.email = new EmailAddress(email);
    }

    public void updatePassword(String password) {
        this.password = new Password(password);
    }

    //Getters
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

    public String getPhoto() {
        return photo.getPhoto();
    }


    // Setter methods
    public void setName(PersonName name) {
        this.name = name;
    }

    public void setEmail(EmailAddress email) {
        this.email = email;
    }
}

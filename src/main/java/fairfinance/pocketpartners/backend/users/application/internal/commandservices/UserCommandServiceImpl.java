package fairfinance.pocketpartners.backend.users.application.internal.commandservices;

import fairfinance.pocketpartners.backend.users.domain.model.aggregates.User;
import fairfinance.pocketpartners.backend.users.domain.model.commands.CreateUserCommand;
import fairfinance.pocketpartners.backend.users.domain.model.commands.DeleteUserCommand;
import fairfinance.pocketpartners.backend.users.domain.model.commands.UpdateUserCommand;
import fairfinance.pocketpartners.backend.users.domain.model.valueobjects.EmailAddress;
import fairfinance.pocketpartners.backend.users.domain.services.UserCommandService;
import fairfinance.pocketpartners.backend.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;

    public UserCommandServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> handle(CreateUserCommand command) {
        var emailAddress = new EmailAddress(command.email());
        userRepository.findByEmail(emailAddress).map(user -> {
            throw new IllegalArgumentException("User with email " + command.email() + " already exists");
        });
        var user = new User(command);
        userRepository.save(user);
        return Optional.of(user);
    }

    @Override
    public Optional<User> handle(DeleteUserCommand command) {
        var user = userRepository.findById(command.userId());
        user.ifPresent(userRepository::delete);
        return user;
    }

    @Override
    public Optional<User> handle(UpdateUserCommand command) {
        return userRepository.findById(command.userId()).map(user -> {
            user.updateName(command.firstName(), command.lastName());
            user.updatePhoneNumber(command.phoneNumber());
            user.updateEmail(command.email());
            userRepository.save(user);
            return user;
        });
    }


}

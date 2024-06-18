package fairfinance.pocketpartners.backend.users.application.internal.commandservices;

import fairfinance.pocketpartners.backend.users.domain.model.aggregates.UserInformation;
import fairfinance.pocketpartners.backend.users.domain.model.commands.CreateUserInformationCommand;
import fairfinance.pocketpartners.backend.users.domain.model.commands.DeleteUserInformationCommand;
import fairfinance.pocketpartners.backend.users.domain.model.commands.UpdateUserInformationCommand;
import fairfinance.pocketpartners.backend.users.domain.model.valueobjects.EmailAddress;
import fairfinance.pocketpartners.backend.users.domain.services.UserInformationCommandService;
import fairfinance.pocketpartners.backend.users.infrastructure.persistence.jpa.repositories.UserInformationRepository;
import org.apache.catalina.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserInformationCommandServiceImpl implements UserInformationCommandService {

    private final UserInformationRepository userInformationRepository;

    public UserInformationCommandServiceImpl(UserInformationRepository userInformationRepository) {
        this.userInformationRepository = userInformationRepository;
    }

    @Override
    public Optional<UserInformation> handle(CreateUserInformationCommand command) {
        var emailAddress = new EmailAddress(command.email());
        userInformationRepository.findByEmail(emailAddress).map(user -> {
            throw new IllegalArgumentException("User with email " + command.email() + " already exists");
        });
        var userInformation = new UserInformation(command);
        userInformationRepository.save(userInformation);
        return Optional.of(userInformation);
    }

    @Override
    public Optional<UserInformation> handle(DeleteUserInformationCommand command) {
        var user = userInformationRepository.findById(command.userId());
        user.ifPresent(userInformationRepository::delete);
        return user;
    }

    @Override
    public Optional<UserInformation> handle(UpdateUserInformationCommand command) {
        return userInformationRepository.findById(command.userId()).map(user -> {
            user.updateName(command.firstName(), command.lastName());
            user.updatePhoneNumber(command.phoneNumber());
            user.updatePhoto(command.photo());
            user.updateEmail(command.email());
            userInformationRepository.save(user);
            return user;
        });
    }


}

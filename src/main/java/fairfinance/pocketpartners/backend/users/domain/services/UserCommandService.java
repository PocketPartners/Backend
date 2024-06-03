package fairfinance.pocketpartners.backend.users.domain.services;

import fairfinance.pocketpartners.backend.users.domain.model.aggregates.User;
import fairfinance.pocketpartners.backend.users.domain.model.commands.CreateUserCommand;
import fairfinance.pocketpartners.backend.users.domain.model.commands.DeleteUserCommand;
import fairfinance.pocketpartners.backend.users.domain.model.commands.UpdateUserCommand;

import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(CreateUserCommand command);
    Optional<User> handle(DeleteUserCommand command);
    Optional<User> handle(UpdateUserCommand command);
}

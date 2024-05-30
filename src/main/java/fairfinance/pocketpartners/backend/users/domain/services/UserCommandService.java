package fairfinance.pocketpartners.backend.users.domain.services;

import fairfinance.pocketpartners.backend.users.domain.model.aggregates.User;
import fairfinance.pocketpartners.backend.users.domain.model.commands.CreateUserCommand;
import java.util.Optional;

public interface UserCommandService {
    Optional<User> handle(CreateUserCommand command);
}

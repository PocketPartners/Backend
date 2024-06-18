package fairfinance.pocketpartners.backend.users.application.internal.commandservices;

import fairfinance.pocketpartners.backend.users.application.internal.outboundservices.hashing.HashingService;
import fairfinance.pocketpartners.backend.users.application.internal.outboundservices.tokens.TokenService;
import fairfinance.pocketpartners.backend.users.domain.model.aggregates.User;
import fairfinance.pocketpartners.backend.users.domain.model.commands.SignInCommand;
import fairfinance.pocketpartners.backend.users.domain.model.commands.SignUpCommand;
import fairfinance.pocketpartners.backend.users.domain.services.UserCommandService;
import fairfinance.pocketpartners.backend.users.infrastructure.persistence.jpa.repositories.RoleRepository;
import fairfinance.pocketpartners.backend.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final HashingService hashingService;
    private final TokenService tokenService;

    private final RoleRepository roleRepository;

    public UserCommandServiceImpl(UserRepository userRepository, HashingService hashingService, TokenService tokenService, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.hashingService = hashingService;
        this.tokenService = tokenService;
        this.roleRepository = roleRepository;
    }
    
    /**
     * Handles the creation of a new user.
     * @param command CreateUserCommand object containing the user's details.
     * @return Optional<User> Newly created user.
     * @throws IllegalArgumentException if a user with the same email already exists.
     */
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
  
    /**
     * Handles the deletion of a user.
     * @param command DeleteUserCommand object containing the user's ID.
     * @return Optional<User> Deleted user.
     */
    @Override
    public Optional<User> handle(DeleteUserCommand command) {
        var user = userRepository.findById(command.userId());
        user.ifPresent(userRepository::delete);
        return user;
    }
  
    /**
     * Handle the sign-in command
     * <p>
     *     This method handles the {@link SignInCommand} command and returns the user and the token.
     * </p>
     * @param command the sign-in command containing the username and password
     * @return and optional containing the user matching the username and the generated token
     * @throws RuntimeException if the user is not found or the password is invalid
     */
    @Override
    public Optional<ImmutablePair<User, String>> handle(SignInCommand command) {
        var user = userRepository.findByUsername(command.username());
        if (user.isEmpty())
            throw new RuntimeException("User not found");
        if (!hashingService.matches(command.password(), user.get().getPassword()))
            throw new RuntimeException("Invalid password");
        var token = tokenService.generateToken(user.get().getUsername());
        return Optional.of(ImmutablePair.of(user.get(), token));
    }

    /**
     * Handles the updating of a user's details.
     * @param command UpdateUserCommand object containing the user's updated details.
     * @return Optional<User> Updated user.
     */
    @Override
    public Optional<User> handle(SignUpCommand command) {
        if (userRepository.existsByUsername(command.username()))
            throw new RuntimeException("Username already exists");
        var roles = command.roles().stream().map(role -> roleRepository.findByName(role.getName()).orElseThrow(() -> new RuntimeException("Role name not found"))).toList();
        var user = new User(command.username(), hashingService.encode(command.password()), roles);
        userRepository.save(user);
        return userRepository.findByUsername(command.username());
    }

}

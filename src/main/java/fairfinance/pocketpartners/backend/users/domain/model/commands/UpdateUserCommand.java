package fairfinance.pocketpartners.backend.users.domain.model.commands;

public record UpdateUserCommand(Long userId, String firstName, String lastName, String phoneNumber, String email) {
}
package fairfinance.pocketpartners.backend.users.domain.model.commands;

public record CreateUserCommand(String firstName, String lastName,String phoneNumber,String photo, String email, String password) {
}
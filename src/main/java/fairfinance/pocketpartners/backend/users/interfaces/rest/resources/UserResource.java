package fairfinance.pocketpartners.backend.users.interfaces.rest.resources;

public record UserResource(Long id,
                           String fullName,
                           String phoneNumber,
                           String email,
                           String password) {
}

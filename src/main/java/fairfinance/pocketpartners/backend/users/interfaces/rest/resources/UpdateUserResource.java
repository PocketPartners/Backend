package fairfinance.pocketpartners.backend.users.interfaces.rest.resources;

public record UpdateUserResource(String firstName, String lastName, String phoneNumber, String email) {
}
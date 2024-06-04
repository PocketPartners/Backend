package fairfinance.pocketpartners.backend.users.interfaces.rest.resources;

public record CreateUserResource(String firstName,
                                 String lastName,
                                 String phoneNumber,
                                 String photo,
                                 String email,
                                 String password) {
}

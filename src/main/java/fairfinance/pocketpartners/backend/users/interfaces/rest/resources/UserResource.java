package fairfinance.pocketpartners.backend.users.interfaces.rest.resources;

import java.util.List;

public record UserResource(Long id,
                           String fullName,
                           String phoneNumber,
                           String photo,
                           String email,
                           String password,
                           List<String> roles) {
}

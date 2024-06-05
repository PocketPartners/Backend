package fairfinance.pocketpartners.backend.users.interfaces.rest.transform;

import fairfinance.pocketpartners.backend.users.domain.model.aggregates.UserInformation;
import fairfinance.pocketpartners.backend.users.interfaces.rest.resources.AuthenticatedUserResource;

public class AuthenticatedUserResourceFromEntityAssembler {
    public static AuthenticatedUserResource toResourceFromEntity(UserInformation userInformation, String token) {
        return new AuthenticatedUserResource(userInformation.getId(), userInformation.getUsername(), token);
    }
}
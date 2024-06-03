package fairfinance.pocketpartners.backend.users.interfaces.rest.transform;

import fairfinance.pocketpartners.backend.users.domain.model.commands.UpdateUserCommand;
import fairfinance.pocketpartners.backend.users.interfaces.rest.resources.UpdateUserResource;

public class UpdateUserCommandFromResourceAssembler {
    public static UpdateUserCommand toCommandfromResource(Long userId, UpdateUserResource resource) {
        return new UpdateUserCommand(userId, resource.firstName(), resource.lastName(), resource.phoneNumber(), resource.email());
    }
}
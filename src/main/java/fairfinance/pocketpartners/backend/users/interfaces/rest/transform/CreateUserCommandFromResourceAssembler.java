package fairfinance.pocketpartners.backend.users.interfaces.rest.transform;

import fairfinance.pocketpartners.backend.users.domain.model.commands.CreateUserCommand;
import fairfinance.pocketpartners.backend.users.interfaces.rest.resources.CreateUserResource;

public class CreateUserCommandFromResourceAssembler {
    public static CreateUserCommand toCommandfromResource(CreateUserResource resource) {
        return new CreateUserCommand(resource.firstName(), resource.lastName(), resource.phoneNumber(), resource.email(), resource.password());
    }
}

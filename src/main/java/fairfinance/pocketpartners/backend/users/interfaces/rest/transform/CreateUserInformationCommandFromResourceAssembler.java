package fairfinance.pocketpartners.backend.users.interfaces.rest.transform;

import fairfinance.pocketpartners.backend.users.domain.model.commands.CreateUserInformationCommand;
import fairfinance.pocketpartners.backend.users.interfaces.rest.resources.CreateUserInformationResource;

public class CreateUserInformationCommandFromResourceAssembler {
    public static CreateUserInformationCommand toCommandfromResource(CreateUserInformationResource resource) {
        return new CreateUserInformationCommand(resource.firstName(), resource.lastName(), resource.phoneNumber(), resource.photo(), resource.email(), resource.userId());
    }
}

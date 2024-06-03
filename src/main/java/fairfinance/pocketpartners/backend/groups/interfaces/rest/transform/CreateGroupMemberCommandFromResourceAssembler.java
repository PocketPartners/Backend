package fairfinance.pocketpartners.backend.groups.interfaces.rest.transform;

import fairfinance.pocketpartners.backend.groups.domain.model.commands.CreateGroupMemberCommand;
import fairfinance.pocketpartners.backend.groups.interfaces.rest.resources.CreateGroupMemberResource;

public class CreateGroupMemberCommandFromResourceAssembler {
    public static CreateGroupMemberCommand fromCommandToResource(CreateGroupMemberResource resource) {
        return new CreateGroupMemberCommand(resource.groupId(), resource.userId());
    }
}

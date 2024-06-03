package fairfinance.pocketpartners.backend.groups.interfaces.rest;

import fairfinance.pocketpartners.backend.groups.domain.model.commands.CreateGroupMemberCommand;
import fairfinance.pocketpartners.backend.groups.domain.model.entities.GroupMember;
import fairfinance.pocketpartners.backend.groups.domain.services.GroupMemberCommandService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "api/v1/groups/{groupId}/members")
@Tag(name = "Member Group", description = "Member Group Management Endpoints")
public class GroupMemberController {
    private final GroupMemberCommandService groupMemberCommand;

    public GroupMemberController(GroupMemberCommandService groupMemberCommandService) {
        this.groupMemberCommand = groupMemberCommandService;
    }

    @PostMapping("{userId}")
    public ResponseEntity<GroupMember> joinGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        var createGroupMemberCommand = new CreateGroupMemberCommand(groupId, userId);
        var groupMember = groupMemberCommand.handle(createGroupMemberCommand);
        return groupMember.map(value -> new ResponseEntity<>(value, HttpStatus.CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

}

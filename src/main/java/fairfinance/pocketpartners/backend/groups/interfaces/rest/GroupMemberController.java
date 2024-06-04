package fairfinance.pocketpartners.backend.groups.interfaces.rest;

import fairfinance.pocketpartners.backend.groups.domain.model.commands.CreateGroupMemberCommand;
import fairfinance.pocketpartners.backend.groups.domain.model.entities.GroupMember;
import fairfinance.pocketpartners.backend.groups.domain.model.queries.GetAllMembersInGroupQuery;
import fairfinance.pocketpartners.backend.groups.domain.services.GroupMemberCommandService;
import fairfinance.pocketpartners.backend.groups.domain.services.GroupMemberQueryService;
import fairfinance.pocketpartners.backend.groups.interfaces.rest.resources.GroupMemberResource;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "api/v1/groups/{groupId}/members")
@Tag(name = "Member Group", description = "Member Group Management Endpoints")
public class GroupMemberController {
    private final GroupMemberCommandService groupMemberCommand;
    private final GroupMemberQueryService groupMemberQuery;

    public GroupMemberController(GroupMemberCommandService groupMemberCommandService, GroupMemberQueryService groupMemberQuery) {
        this.groupMemberCommand = groupMemberCommandService;
        this.groupMemberQuery = groupMemberQuery;
    }

    @PostMapping("{userId}")
    public ResponseEntity<GroupMember> joinGroup(@PathVariable Long groupId, @PathVariable Long userId) {
        var createGroupMemberCommand = new CreateGroupMemberCommand(groupId, userId);
        var groupMember = groupMemberCommand.handle(createGroupMemberCommand);
        return groupMember.map(value -> new ResponseEntity<>(value, HttpStatus.CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping
    public ResponseEntity<List<Object>> getGroupMembers(@PathVariable Long groupId) {
        var groupMembers = groupMemberQuery.handle(new GetAllMembersInGroupQuery(groupId));
        if (groupMembers.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(groupMembers.stream().map(GroupMemberResource::fromCommandToResource).collect(Collectors.toList()));
    }

}

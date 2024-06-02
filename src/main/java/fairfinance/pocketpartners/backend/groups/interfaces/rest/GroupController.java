package fairfinance.pocketpartners.backend.groups.interfaces.rest;

import fairfinance.pocketpartners.backend.groups.domain.model.aggregates.Group;
import fairfinance.pocketpartners.backend.groups.domain.model.queries.GetGroupByIdQuery;
import fairfinance.pocketpartners.backend.groups.domain.services.GroupQueryService;
import fairfinance.pocketpartners.backend.groups.interfaces.rest.resources.CreateGroupResource;
import fairfinance.pocketpartners.backend.groups.interfaces.rest.resources.GroupResource;
import fairfinance.pocketpartners.backend.groups.interfaces.rest.transform.CreateGroupCommandFromResourceAssembler;
import fairfinance.pocketpartners.backend.groups.interfaces.rest.transform.GroupResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import fairfinance.pocketpartners.backend.groups.domain.services.GroupCommandService;

@RestController
@RequestMapping(value = "api/v1/groups", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Group", description = "Group Management Endpoints")
public class GroupController {

    private final GroupCommandService groupCommandService;
    private final GroupQueryService groupQueryService;

    public GroupController(GroupCommandService groupCommandService, GroupQueryService groupQueryService) {
        this.groupCommandService = groupCommandService;
        this.groupQueryService = groupQueryService;
    }

    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody CreateGroupResource createGroupResource) {
        var createGroupCommand = CreateGroupCommandFromResourceAssembler.toCommandFromResource(createGroupResource);
        var group = groupCommandService.handle(createGroupCommand);
        return group.map(value -> new ResponseEntity<>(value, HttpStatus.CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<GroupResource> getGroup(@PathVariable Long groupId) {
        var getGroupByIdQuery = new GetGroupByIdQuery(groupId);
        var group = groupQueryService.handle(getGroupByIdQuery);
        if (group.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var courseResource = GroupResourceFromEntityAssembler.toResourceFromEntity(group.get());
        return ResponseEntity.ok(courseResource);
    }
}

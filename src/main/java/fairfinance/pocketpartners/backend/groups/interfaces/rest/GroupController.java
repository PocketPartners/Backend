package fairfinance.pocketpartners.backend.groups.interfaces.rest;

import fairfinance.pocketpartners.backend.groups.domain.model.aggregates.Group;
import fairfinance.pocketpartners.backend.groups.interfaces.rest.resources.CreateGroupResource;
import fairfinance.pocketpartners.backend.groups.interfaces.rest.transform.CreateGroupCommandFromResourceAssembler;
import fairfinance.pocketpartners.backend.groups.interfaces.rest.transform.GroupResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fairfinance.pocketpartners.backend.groups.domain.services.GroupCommandService;

@RestController
@RequestMapping(value = "api/v1/groups", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Group", description = "Group Management Endpoints")
public class GroupController {

    private final GroupCommandService groupCommandService;

    public GroupController(GroupCommandService groupCommandService) {
        this.groupCommandService = groupCommandService;
    }

    // TODO Implement the POST endpoint to create a new group and assing a user id
    @PostMapping
    public ResponseEntity<Group> createGroup(@RequestBody CreateGroupResource createGroupResource) {
        var createGroupCommand = CreateGroupCommandFromResourceAssembler.toCommandFromResource(createGroupResource);
        var group = groupCommandService.handle(createGroupCommand);
        return group.map(value -> new ResponseEntity<>(value, HttpStatus.CREATED)).orElseGet(() -> ResponseEntity.badRequest().build());
    }
}

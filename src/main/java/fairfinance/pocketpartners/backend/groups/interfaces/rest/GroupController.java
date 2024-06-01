package fairfinance.pocketpartners.backend.groups.interfaces.rest;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fairfinance.pocketpartners.backend.groups.domain.services.GroupCommandService;

@RestController
@RequestMapping(value = "api/v1/groups", produces = MediaType.APPLICATION_JSON_VALUE)
public class GroupController {

    private final GroupCommandService groupCommandService;

    public GroupController(GroupCommandService groupCommandService) {
        this.groupCommandService = groupCommandService;
    }

    // TODO Implement the POST endpoint to create a new group
}

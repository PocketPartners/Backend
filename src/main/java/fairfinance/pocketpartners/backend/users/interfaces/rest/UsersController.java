package fairfinance.pocketpartners.backend.users.interfaces.rest;

import fairfinance.pocketpartners.backend.users.domain.model.commands.DeleteUserCommand;
import fairfinance.pocketpartners.backend.users.domain.model.queries.GetAllUsersQuery;
import fairfinance.pocketpartners.backend.users.domain.model.queries.GetUserByIdQuery;
import fairfinance.pocketpartners.backend.users.domain.services.UserCommandService;
import fairfinance.pocketpartners.backend.users.domain.services.UserQueryService;
import fairfinance.pocketpartners.backend.users.interfaces.rest.resources.CreateUserResource;
import fairfinance.pocketpartners.backend.users.interfaces.rest.resources.UpdateUserResource;
import fairfinance.pocketpartners.backend.users.interfaces.rest.resources.UserResource;
import fairfinance.pocketpartners.backend.users.interfaces.rest.transform.CreateUserCommandFromResourceAssembler;
import fairfinance.pocketpartners.backend.users.interfaces.rest.transform.UserResourceFromEntityAssembler;
import fairfinance.pocketpartners.backend.users.interfaces.rest.transform.UpdateUserCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/v1/users", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "User Management Endpoints")
public class UsersController {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    public UsersController(UserQueryService userQueryService, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    //CREATE
    @Operation(summary = "Create a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    public ResponseEntity<UserResource> createUser(@RequestBody CreateUserResource resource) {
        var createUserCommand = CreateUserCommandFromResourceAssembler.toCommandfromResource(resource);
        var user = userCommandService.handle(createUserCommand);
        if (user.isEmpty()) return ResponseEntity.badRequest().build();
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return new ResponseEntity<>(userResource, HttpStatus.CREATED);
    }

    //GET BY ID
    @Operation(summary = "Get user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<UserResource> getProfileById(@PathVariable Long userId) {
        var getUserByIdQuery = new GetUserByIdQuery(userId);
        var user = userQueryService.handle(getUserByIdQuery);
        if (user.isEmpty()) return ResponseEntity.badRequest().build();
        var profileResource = UserResourceFromEntityAssembler.toResourceFromEntity(user.get());
        return ResponseEntity.ok(profileResource);
    }

    //GET ALL
    @Operation(summary = "Get all users")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List of users")
    })
    @GetMapping
    public ResponseEntity<List<UserResource>> getAllProfiles() {
        var getAllUsersQuery = new GetAllUsersQuery();
        var profiles = userQueryService.handle(getAllUsersQuery);
        var profileResources = profiles.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).collect(Collectors.toList());
        return ResponseEntity.ok(profileResources);
    }


    //UPDATE
    @Operation(summary = "Update user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @PutMapping("/{userId}")
    public ResponseEntity<UserResource> updateUserById(@PathVariable Long userId, @RequestBody UpdateUserResource resource) {
        var updateUserCommand = UpdateUserCommandFromResourceAssembler.toCommandfromResource(userId, resource);
        var updatedUser = userCommandService.handle(updateUserCommand);
        if (updatedUser.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        var userResource = UserResourceFromEntityAssembler.toResourceFromEntity(updatedUser.get());
        return ResponseEntity.ok(userResource);
    }


    //DELETE
    @Operation(summary = "Delete user by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully"),
            @ApiResponse(responseCode = "400", description = "Bad request"),
            @ApiResponse(responseCode = "404", description = "User not found")
    })
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUserById(@PathVariable Long userId) {
        var deleteUserCommand = new DeleteUserCommand(userId);
        var userDeleted = userCommandService.handle(deleteUserCommand);
        if (userDeleted.isEmpty()) return ResponseEntity.badRequest().build();
        return ResponseEntity.noContent().build();
    }

}

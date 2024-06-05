package fairfinance.pocketpartners.backend.users.interfaces.acl;

import fairfinance.pocketpartners.backend.users.domain.model.commands.CreateUserCommand;
import fairfinance.pocketpartners.backend.users.domain.services.UserCommandService;
import fairfinance.pocketpartners.backend.users.domain.services.UserQueryService;
import org.springframework.stereotype.Service;

@Service
public class UsersContextFacade {

    private final UserQueryService userQueryService;
    private final UserCommandService userCommandService;

    public UsersContextFacade(UserQueryService userQueryService, UserCommandService userCommandService) {
        this.userQueryService = userQueryService;
        this.userCommandService = userCommandService;
    }

    public Long createUser(String firstName, String lastName,String phoneNumber,String photo, String email, String password) {
        var createProfileCommand = new CreateUserCommand(firstName, lastName, phoneNumber,photo, email, password);
        var user = userCommandService.handle(createProfileCommand);
        if (user.isEmpty()) return 0L;
        return user.get().getId();
    }

}

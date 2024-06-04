package fairfinance.pocketpartners.backend.users.interfaces.rest.transform;

import fairfinance.pocketpartners.backend.users.domain.model.aggregates.User;
import fairfinance.pocketpartners.backend.users.interfaces.rest.resources.UserResource;

public class UserResourceFromEntityAssembler {
    public static UserResource toResourceFromEntity(User user) {
        return new UserResource(user.getId(), user.getFullName(), user.getPhoneNumber(), user.getPhoto(), user.getEmailAddress(), user.getPassword());
    }
}

package fairfinance.pocketpartners.backend.users.domain.services;

import fairfinance.pocketpartners.backend.users.domain.model.aggregates.User;
import fairfinance.pocketpartners.backend.users.domain.model.queries.GetAllUsersQuery;
import fairfinance.pocketpartners.backend.users.domain.model.queries.GetUserByIdQuery;

import java.util.List;
import java.util.Optional;

public interface UserQueryService {

    List<User> handle(GetAllUsersQuery query);
    Optional<User> handle(GetUserByIdQuery query);
}

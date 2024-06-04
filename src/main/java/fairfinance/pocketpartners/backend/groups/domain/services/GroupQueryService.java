package fairfinance.pocketpartners.backend.groups.domain.services;

import fairfinance.pocketpartners.backend.groups.domain.model.aggregates.Group;
import fairfinance.pocketpartners.backend.groups.domain.model.queries.GetAllGroupsQuery;
import fairfinance.pocketpartners.backend.groups.domain.model.queries.GetGroupByIdQuery;
import fairfinance.pocketpartners.backend.users.domain.model.aggregates.User;
import fairfinance.pocketpartners.backend.users.domain.model.queries.GetAllUsersQuery;

import java.util.List;
import java.util.Optional;

public interface GroupQueryService {
    Optional<Group> handle(GetGroupByIdQuery query);
    List<Group> handle(GetAllGroupsQuery query);
}

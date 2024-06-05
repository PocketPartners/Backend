package fairfinance.pocketpartners.backend.groups.application.internal.commandservices;

import fairfinance.pocketpartners.backend.groups.domain.model.commands.CreateGroupMemberCommand;
import fairfinance.pocketpartners.backend.groups.domain.model.entities.GroupMember;
import fairfinance.pocketpartners.backend.groups.domain.services.GroupMemberCommandService;
import fairfinance.pocketpartners.backend.groups.infrastructure.persistence.jpa.repositories.GroupMemberRepository;
import fairfinance.pocketpartners.backend.groups.infrastructure.persistence.jpa.repositories.GroupRepository;
import fairfinance.pocketpartners.backend.users.infrastructure.persistence.jpa.repositories.UserInformationRepository;
import fairfinance.pocketpartners.backend.users.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GroupMemberCommandServiceImpl implements GroupMemberCommandService {

    private final GroupMemberRepository groupMemberRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public GroupMemberCommandServiceImpl(GroupMemberRepository groupMemberRepository, GroupRepository groupRepository, UserRepository userRepository) {
        this.groupMemberRepository = groupMemberRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Optional<GroupMember> handle(CreateGroupMemberCommand command) {
        if (command == null || groupMemberRepository.findByGroupIdAndUserId(command.groupId(), command.userId()).isPresent()) {
            return Optional.empty();
        }
        return Optional.ofNullable(groupRepository.findById(command.groupId()).orElse(null))
                .flatMap(group -> Optional.ofNullable(userRepository.findById(command.userId()).orElse(null))
                        .map(user -> {
                            GroupMember groupMember = new GroupMember(group, user);
                            groupMemberRepository.save(groupMember);
                            return groupMember;
                        }));
    }
}

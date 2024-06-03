package fairfinance.pocketpartners.backend.groups.infrastructure.persistence.jpa.repositories;

import fairfinance.pocketpartners.backend.groups.domain.model.entities.GroupMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupMemberRepository extends JpaRepository<GroupMember, Long> {
    Optional<GroupMember> findByGroupIdAndUserId(Long groupId, Long userId);
}

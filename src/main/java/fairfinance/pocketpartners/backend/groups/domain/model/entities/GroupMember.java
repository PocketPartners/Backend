package fairfinance.pocketpartners.backend.groups.domain.model.entities;

import fairfinance.pocketpartners.backend.groups.domain.model.aggregates.Group;
import fairfinance.pocketpartners.backend.groups.domain.model.valueobjects.GroupMemberId;
import fairfinance.pocketpartners.backend.users.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

/**
 * The GroupMember class represents a member of a group in the system.
 * It is an entity class that is linked to both the Group and User aggregate roots.
 */
@Entity
@Getter
@IdClass(GroupMemberId.class)
@EntityListeners(AuditingEntityListener.class)
public class GroupMember {

    /**
     * The group that the user is a member of.
     * It is a many-to-one relationship, as a group can have many members.
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    /**
     * The user who is a member of the group.
     * It is a many-to-one relationship, as a user can be a member of many groups.
     */
    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    /**
     * The date when the user joined the group.
     * It is automatically updated whenever the entity is persisted.
     */
    @LastModifiedDate
    @Column(nullable =  false)
    private Date joinedAt;

    /**
     * Constructs a new GroupMember with the provided group and user.
     *
     * @param group The group that the user is joining.
     * @param user The user who is joining the group.
     */
    public GroupMember(Group group, User user) {
        this.group = group;
        this.user = user;
        this.joinedAt = new Date();
    }

    /**
     * Default constructor. Required for JPA.
     */
    protected GroupMember() {
    }
}

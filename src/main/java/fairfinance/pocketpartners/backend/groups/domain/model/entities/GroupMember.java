package fairfinance.pocketpartners.backend.groups.domain.model.entities;

import fairfinance.pocketpartners.backend.groups.domain.model.aggregates.Group;
import fairfinance.pocketpartners.backend.users.domain.model.aggregates.User;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@Getter
@EntityListeners(AuditingEntityListener.class)
public class GroupMember {

    @Id
    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @LastModifiedDate
    @Column(nullable =  false)
    private Date joinedAt;

    public GroupMember(Group group, User user) {
        this.group = group;
        this.user = user;
    }

    protected GroupMember() {
    }


}

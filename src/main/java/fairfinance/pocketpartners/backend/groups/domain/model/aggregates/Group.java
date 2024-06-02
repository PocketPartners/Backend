package fairfinance.pocketpartners.backend.groups.domain.model.aggregates;

import fairfinance.pocketpartners.backend.groups.domain.model.commands.CreateGroupCommand;
import fairfinance.pocketpartners.backend.groups.domain.model.valueobjects.GroupName;
import fairfinance.pocketpartners.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "pocket_groups")
public class Group extends AuditableAbstractAggregateRoot<Group> {

    @Embedded
    private GroupName name;

    public Group(GroupName name) {
        this.name = name;
    }

    public Group(CreateGroupCommand command) {
        this.name = new GroupName(command.name());
    }

    public Group() {
        this.name = new GroupName();
    }

    public void changeName(GroupName name) {
        this.name = name;
    }
}

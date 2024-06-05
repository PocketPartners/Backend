package fairfinance.pocketpartners.backend.groups.domain.model.aggregates;

import fairfinance.pocketpartners.backend.groups.domain.model.commands.CreateGroupCommand;
import fairfinance.pocketpartners.backend.groups.domain.model.valueobjects.GroupName;
import fairfinance.pocketpartners.backend.groups.domain.model.valueobjects.GroupPhoto;
import fairfinance.pocketpartners.backend.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

/**
 * The Group class represents a group entity in the system.
 * It extends the AuditableAbstractAggregateRoot class, which provides audit fields for the entity.
 */
@Getter
@Entity
@Table(name = "pocket_groups")
public class Group extends AuditableAbstractAggregateRoot<Group> {

    /**
     * The name of the group, represented as an embedded value object.
     */
    @Embedded
    private GroupName name;

    @Embedded
    private GroupPhoto groupPhoto;

    /**
     * Constructs a new Group with the provided name.
     *
     * @param name The name of the group.
     */
    public Group(GroupName name, GroupPhoto groupPhoto) {
        this.name = name;
        this.groupPhoto = groupPhoto;
    }

    /**
     * Constructs a new Group from a CreateGroupCommand.
     *
     * @param command The command containing the details of the group to be created.
     */
    public Group(CreateGroupCommand command) {
        this.name = new GroupName(command.name());
        this.groupPhoto = new GroupPhoto(command.groupPhoto());
    }

    /**
     * Default constructor. Constructs a new Group with a default name.
     */
    public Group() {
        this.name = new GroupName();
        this.groupPhoto = new GroupPhoto();
    }

    /**
     * Changes the name of the group.
     *
     * @param name The new name of the group.
     */
    public void changeName(GroupName name) {
        this.name = name;
    }

    public void changeGroupPhoto(GroupPhoto groupPhoto) {
        this.groupPhoto = groupPhoto;
    }

    /**
     * Changes the name of the group.
     *
     * @param name The new name of the group, as a string.
     */
    public void changeName(String name) {
        this.name = new GroupName(name);
    }

    public void changeGroupPhoto(String groupPhoto) {
        this.groupPhoto = new GroupPhoto(groupPhoto);
    }

    /**
     * Returns the name of the group.
     *
     * @return The name of the group.
     */
    public String getName() {
        return name.GetName();
    }

    public String getGroupPhoto() {
        return groupPhoto.getPhotoLink();
    }
}

package fairfinance.pocketpartners.backend.groups.domain.model.valueobjects;

import java.io.Serializable;
import java.util.Objects;

public class GroupMemberId implements Serializable {
    private Long group;
    private Long user;

    // Default constructor
    public GroupMemberId() {}

    // Getters and setters
    public Long getGroup() {
        return group;
    }

    public void setGroup(Long group) {
        this.group = group;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
    }

    // hashCode and equals implementation
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupMemberId that = (GroupMemberId) o;
        return Objects.equals(group, that.group) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(group, user);
    }
}
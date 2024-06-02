package fairfinance.pocketpartners.backend.groups.domain.model.valueobjects;

import jakarta.persistence.Embeddable;

@Embeddable
public record GroupName(String name) {
    public GroupName {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Group name must not be null or blank");
        }
    }

    public GroupName () {
        this("No name");
    }
}

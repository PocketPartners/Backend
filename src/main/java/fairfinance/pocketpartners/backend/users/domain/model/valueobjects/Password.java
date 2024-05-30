package fairfinance.pocketpartners.backend.users.domain.model.valueobjects;

import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Embeddable
public record Password(
        @NotBlank
        @Size(min = 5, message = "Password must be at least 5 characters long")
        String value
) {
    public Password {
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }
        if (value.length() < 5) {
            throw new IllegalArgumentException("Password must be at least 5 characters long");
        }
    }

    // Factory method for creating an instance with a default value if needed
    public static Password defaultPassword() {
        return new Password("defaultPassword");
    }

    public String getPassword() {
        return value;
    }
}

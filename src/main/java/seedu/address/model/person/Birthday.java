package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
// this class is almost directly borrowed from seedu.address.model.appointment.AppointmentDateTime
// should try to make the code reusable

/**
 * Represents the Birthday of a patient.
 * Guarantees: immutable and must represent a time in the past.
 */
public class Birthday {

    public static final String MESSAGE_CONSTRAINTS = "Birthday mush be a date in the past.";

    private final LocalDateTime value;

    /**
     * Constructs an {@code Birthday}.
     *
     * @param value The {@code LocalDateTime} of the Birthday.
     * @throws IllegalArgumentException if {@code value} is after the current time.
     */
    public Birthday(LocalDateTime value) {
        requireNonNull(value);
        if (value.isAfter(LocalDateTime.now())) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.value = value;
    }

    public LocalDateTime getBirthday() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof seedu.address.model.person.Birthday)) {
            return false;
        }
        return value.equals(((seedu.address.model.person.Birthday) other).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;

/**
 * Represents the date and time of an {@code Appointment}.
 * Guarantees: immutable and must represent a time in the future.
 */
public class AppointmentDateTime {

    public static final String MESSAGE_CONSTRAINTS = "Appointment date/time must be in the future.";

    private final LocalDateTime value;

    /**
     * Constructs an {@code AppointmentDateTime}.
     *
     * @param value The {@code LocalDateTime} of the appointment.
     * @throws IllegalArgumentException if {@code value} is before the current time.
     */
    public AppointmentDateTime(LocalDateTime value) {
        requireNonNull(value);
        if (value.isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.value = value;
    }

    public LocalDateTime getValue() {
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
        if (!(other instanceof AppointmentDateTime)) {
            return false;
        }
        return value.equals(((AppointmentDateTime) other).value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}

package seedu.hospital.model.appointment;

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
     * @param dateTime The {@code LocalDateTime} of the appointment.
     * @throws IllegalArgumentException if {@code dateTime} is before the current time.
     */
    public AppointmentDateTime(LocalDateTime dateTime) {
        requireNonNull(dateTime);
        if (isPast(dateTime)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.value = dateTime;
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

    /**
     * Helper to check if a date is before the current time.
     */
    private boolean isPast(LocalDateTime dateTime) {
        return dateTime.isBefore(LocalDateTime.now());
    }
}

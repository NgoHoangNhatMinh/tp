package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an Appointment in the hospital system.
 * Guarantees: details are present and not null.
 */
public class Appointment {

    private final String patientName;
    private final LocalDateTime dateTime;
    private final String doctor;
    private final String reason;

    /**
     * Constructs an {@code Appointment}.
     *
     * @param patientName The patient's unique ID.
     * @param dateTime The date and time of the appointment.
     * @param doctor The doctor's name.
     * @param reason The reason for the appointment.
     */
    public Appointment(String patientName, LocalDateTime dateTime, String doctor, String reason) {
        requireNonNull(patientName);
        requireNonNull(dateTime);
        requireNonNull(doctor);
        requireNonNull(reason);
        this.patientName = patientName;
        this.dateTime = dateTime;
        this.doctor = doctor;
        this.reason = reason;
    }

    public String getPatientName() {
        return patientName;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDoctor() {
        return doctor;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Appointment)) {
            return false;
        }
        final Appointment otherAppointment = (Appointment) other;
        return patientName.equals(otherAppointment.patientName)
                && dateTime.equals(otherAppointment.dateTime)
                && doctor.equals(otherAppointment.doctor)
                && reason.equals(otherAppointment.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientName, dateTime, doctor, reason);
    }

    @Override
    public String toString() {
        return String.format("Appointment[patient=%s, dateTime=%s, doctor=%s, reason=%s]",
                patientName, dateTime, doctor, reason);
    }
}

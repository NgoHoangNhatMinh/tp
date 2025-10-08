package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents an Appointment in the hospital system.
 * Guarantees: details are present and not null.
 */
public class Appointment {

    private final String patientId;
    private final LocalDateTime dateTime;
    private final String doctor;
    private final String reason;

    /**
     * Constructs an {@code Appointment}.
     *
     * @param patientId The patient's unique ID.
     * @param dateTime The date and time of the appointment.
     * @param doctor The doctor's name.
     * @param reason The reason for the appointment.
     */
    public Appointment(String patientId, LocalDateTime dateTime, String doctor, String reason) {
        requireNonNull(patientId);
        requireNonNull(dateTime);
        requireNonNull(doctor);
        requireNonNull(reason);
        this.patientId = patientId;
        this.dateTime = dateTime;
        this.doctor = doctor;
        this.reason = reason;
    }

    public String getPatientId() {
        return patientId;
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
        return patientId.equals(otherAppointment.patientId)
                && dateTime.equals(otherAppointment.dateTime)
                && doctor.equals(otherAppointment.doctor)
                && reason.equals(otherAppointment.reason);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, dateTime, doctor, reason);
    }

    @Override
    public String toString() {
        return String.format("Appointment[patient=%s, dateTime=%s, doctor=%s, reason=%s]",
                patientId, dateTime, doctor, reason);
    }
}

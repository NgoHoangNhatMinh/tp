package seedu.address.model.prescription;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a Prescription in the hospital system.
 * Guarantees: patientId, medicationName, dosage, frequency, duration are present and not null
 */
public class Prescription {
    private final String patientId;
    private final String medicationName;
    private final Float dosage;
    private final Integer frequency;
    private final LocalDateTime startDate;
    private final Integer duration;
    private final String note;


    /**
     * Constructs an {@code Prescription}.
     *
     * @param patientId The patient's unique ID.
     * @param medicationName The medication name
     * @param dosage The dosage of medication
     * @param frequency The frequency (per day) to take medication
     * @param startDate The start day of taking medication
     * @param duration The number of days to take medication
     * @param note Additional note
     */
    public Prescription(String patientId, String medicationName, Float dosage,
                        Integer frequency, LocalDateTime startDate, Integer duration, String note) {
        requireNonNull(patientId);
        requireNonNull(medicationName);
        requireNonNull(dosage);
        requireNonNull(frequency);
        requireNonNull(duration);
        this.patientId = patientId;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.startDate = startDate;
        this.duration = duration;
        this.note = note;
    }

    public String getPatientId() {
        return patientId;
    }

    public String getMedicationName() {
        return medicationName;
    }

    public float getDosage() {
        return dosage;
    }

    public int getFrequency() {
        return frequency;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public int getDuration() {
        return duration;
    }

    public String getNote() {
        return note;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Prescription)) {
            return false;
        }

        final Prescription p = (Prescription) other;
        return patientId.equals(p.patientId)
                && medicationName.equals((p.medicationName))
                && dosage.equals(p.dosage)
                && frequency.equals(p.frequency)
                && startDate.equals(p.startDate)
                && duration.equals(p.duration)
                && note.equals(p.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(patientId, medicationName, dosage, frequency, startDate, duration, note);
    }

    @Override
    public String toString() {
        return String.format("Prescription[patient=%s, medication=%s, dosage=%f, "
                        + "frequency=%d, startDate=%s, duration=%d, note=%s]",
                patientId, medicationName, dosage, frequency, startDate, duration, note);
    }
}

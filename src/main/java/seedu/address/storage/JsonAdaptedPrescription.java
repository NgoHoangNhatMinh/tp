package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.prescription.Prescription;

/**
 * Jackson-friendly version of {@link Prescription}.
 */
public class JsonAdaptedPrescription {
    private final String patientId;
    private final String medicationName;
    private final Float dosage;
    private final Integer frequency;
    private final LocalDateTime startDate;
    private final Integer duration;
    private final String note;

    /**
     * Constructs a {@code JsonAdaptedPrescription} with the given prescription details.
     */
    @JsonCreator
    public JsonAdaptedPrescription(@JsonProperty("patientId") String patientId,
                                   @JsonProperty("medicationName") String medicationName,
                                   @JsonProperty("dosage") Float dosage,
                                   @JsonProperty("frequency") Integer frequency,
                                   @JsonProperty("startDate") LocalDateTime startDate,
                                   @JsonProperty("duration") Integer duration,
                                   @JsonProperty("note") String note
                                   ) {
        this.patientId = patientId;
        this.medicationName = medicationName;
        this.dosage = dosage;
        this.frequency = frequency;
        this.startDate = startDate;
        this.duration = duration;
        this.note = note;
    }

    /**
     * Converts a given {@code Prescription} into this class for Jackson use.
     */
    public JsonAdaptedPrescription(Prescription source) {
        this.patientId = source.getPatientId();
        this.medicationName = source.getMedicationName();
        this.dosage = source.getDosage();
        this.frequency = source.getFrequency();
        this.startDate = source.getStartDate();
        this.duration = source.getDuration();
        this.note = source.getNote();
    }

    /**
     * Converts this Jackson-friendly object back into the model's {@code Prescription} object.
     */
    public Prescription toModelType() throws IllegalValueException {
        if (patientId == null) {
            throw new IllegalValueException("Patient ID is missing");
        }
        if (medicationName == null) {
            throw new IllegalValueException("Medication name is missing");
        }
        if (dosage == null) {
            throw new IllegalValueException("Dosage is missing");
        }
        if (frequency == null) {
            throw new IllegalValueException("Frequency is missing");
        }
        if (duration == null) {
            throw new IllegalValueException("Duration is missing");
        }

        // Value range validation for numeric fields
        if (dosage <= 0) {
            throw new IllegalValueException("Dosage must be positive");
        }
        if (frequency <= 0) {
            throw new IllegalValueException("Frequency must be positive");
        }
        if (duration <= 0) {
            throw new IllegalValueException("Duration must be positive");
        }

        return new Prescription(patientId, medicationName, dosage, frequency,
            startDate, duration, note);
    }
}

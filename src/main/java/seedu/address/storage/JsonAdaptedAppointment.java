package seedu.address.storage;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.model.appointment.Appointment;

/**
 * Jackson-friendly version of {@link Appointment}.
 */
public class JsonAdaptedAppointment {

    private final String patientId;
    private final String dateTime;
    private final String doctor;
    private final String reason;

    /**
     * Constructs a {@code JsonAdaptedAppointment} with the given details.
     */
    @JsonCreator
    public JsonAdaptedAppointment(@JsonProperty("patientId") String patientId,
                                  @JsonProperty("dateTime") String dateTime,
                                  @JsonProperty("doctor") String doctor,
                                  @JsonProperty("reason") String reason) {
        this.patientId = patientId;
        this.dateTime = dateTime;
        this.doctor = doctor;
        this.reason = reason;
    }

    /**
     * Converts a given {@code Appointment} into this class for Jackson use.
     */
    public JsonAdaptedAppointment(Appointment source) {
        this.patientId = source.getPatientName();
        this.dateTime = source.getDateTime().toString();
        this.doctor = source.getDoctor();
        this.reason = source.getReason();
    }

    /**
     * Converts this Jackson-friendly object back into the model's {@code Appointment} object.
     */
    public Appointment toModelType() {
        return new Appointment(patientId, LocalDateTime.parse(dateTime), doctor, reason);
    }
}

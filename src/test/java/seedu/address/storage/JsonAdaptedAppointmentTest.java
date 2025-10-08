package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.Appointment;

/**
 * Tests for {@link JsonAdaptedAppointment}.
 */
public class JsonAdaptedAppointmentTest {

    @Test
    public void toModelType_validDetails_returnsAppointment() {
        Appointment original = new Appointment("P001", LocalDateTime.of(2025, 10, 10, 10, 0),
                "Dr Tan", "Review");
        JsonAdaptedAppointment jsonAdapted = new JsonAdaptedAppointment(original);
        Appointment converted = jsonAdapted.toModelType();
        assertEquals(original, converted);
    }

    @Test
    public void jsonCreatorConstructor_validInputs_createsObject() {
        JsonAdaptedAppointment json = new JsonAdaptedAppointment("P002",
                "2025-11-11T09:00", "Dr Lee", "Consultation");
        Appointment model = json.toModelType();
        assertEquals("P002", model.getPatientId());
        assertEquals("Dr Lee", model.getDoctor());
        assertEquals("Consultation", model.getReason());
    }
}

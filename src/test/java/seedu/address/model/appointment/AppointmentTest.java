package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Unit tests for {@link Appointment}.
 */
public class AppointmentTest {

    @Test
    public void constructor_validInputs_success() {
        Appointment appointment =
                new Appointment("P001", LocalDateTime.now().plusDays(1), "Dr Tan", "Check-up");
        assertEquals("P001", appointment.getPatientId());
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        LocalDateTime dateTime = LocalDateTime.now().plusDays(1);
        Appointment appointment1 =
                new Appointment("P001", dateTime, "Dr Tan", "Check-up");
        Appointment appointment2 =
                new Appointment("P001", dateTime, "Dr Tan", "Check-up");
        assertEquals(appointment1, appointment2);
    }

    @Test
    public void toString_containsFields() {
        Appointment appointment =
                new Appointment("P001", LocalDateTime.now().plusDays(1), "Dr Tan", "Review");
        String result = appointment.toString();
        assertTrue(result.contains("Dr Tan"));
        assertTrue(result.contains("Review"));
    }
}

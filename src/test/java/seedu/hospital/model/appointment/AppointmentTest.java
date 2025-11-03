package seedu.hospital.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link Appointment}.
 */
public class AppointmentTest {

    private static final LocalDateTime FUTURE_TIME = LocalDateTime.now().plusDays(1);

    @Test
    public void constructor_nullArguments_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () ->
                new Appointment(null, FUTURE_TIME, "Dr Tan", "Check-up"));
        assertThrows(NullPointerException.class, () ->
                new Appointment("P001", null, "Dr Tan", "Check-up"));
        assertThrows(NullPointerException.class, () ->
                new Appointment("P001", FUTURE_TIME, null, "Check-up"));
        assertThrows(NullPointerException.class, () ->
                new Appointment("P001", FUTURE_TIME, "Dr Tan", null));
    }

    @Test
    public void equals_sameValues_returnsTrue() {
        Appointment a1 = new Appointment("P001", FUTURE_TIME, "Dr Tan", "Check-up");
        Appointment a2 = new Appointment("P001", FUTURE_TIME, "Dr Tan", "Check-up");
        assertEquals(a1, a2);
        assertEquals(a1.hashCode(), a2.hashCode());
    }

    @Test
    public void equals_differentValues_returnsFalse() {
        Appointment base = new Appointment("P001", FUTURE_TIME, "Dr Tan", "Check-up");
        assertNotEquals(base, new Appointment("P002", FUTURE_TIME, "Dr Tan", "Check-up"));
        assertNotEquals(base, new Appointment("P001", FUTURE_TIME.plusHours(1), "Dr Tan", "Check-up"));
        assertNotEquals(base, new Appointment("P001", FUTURE_TIME, "Dr Lee", "Check-up"));
        assertNotEquals(base, new Appointment("P001", FUTURE_TIME, "Dr Tan", "Consultation"));
        assertNotEquals(base, null);
        assertNotEquals(base, 42);
    }

    @Test
    public void toString_containsAllFields() {
        Appointment a = new Appointment("P001", FUTURE_TIME, "Dr Tan", "Check-up");
        String str = a.toString();
        assertTrue(str.contains("P001"));
        assertTrue(str.contains("Dr Tan"));
        assertTrue(str.contains("Check-up"));
    }
}

package seedu.hospital.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

/**
 * Tests for {@link AppointmentDateTime}.
 */
public class AppointmentDateTimeTest {

    @Test
    public void constructor_pastDate_throwsIllegalArgumentException() {
        LocalDateTime past = LocalDateTime.now().minusDays(1);
        assertThrows(IllegalArgumentException.class, () -> new AppointmentDateTime(past));
    }

    @Test
    public void constructor_futureDate_success() {
        LocalDateTime future = LocalDateTime.now().plusDays(2);
        AppointmentDateTime adt = new AppointmentDateTime(future);
        assertEquals(future, adt.getValue());
    }

    @Test
    public void equals_and_hashCode() {
        LocalDateTime dt = LocalDateTime.now().plusDays(1);
        AppointmentDateTime a = new AppointmentDateTime(dt);
        AppointmentDateTime b = new AppointmentDateTime(dt);
        assertTrue(a.equals(b));
        assertEquals(a.hashCode(), b.hashCode());
    }

    @Test
    public void toString_returnsValueString() {
        LocalDateTime dt = LocalDateTime.now().plusDays(1);
        AppointmentDateTime a = new AppointmentDateTime(dt);
        assertEquals(dt.toString(), a.toString());
    }
}

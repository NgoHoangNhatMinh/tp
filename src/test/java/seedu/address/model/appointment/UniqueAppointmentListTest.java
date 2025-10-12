package seedu.address.model.appointment;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;

/**
 * Tests for {@link UniqueAppointmentList}.
 */
public class UniqueAppointmentListTest {

    private UniqueAppointmentList list;
    private Appointment a1;
    private Appointment a2;

    @BeforeEach
    public void setUp() {
        list = new UniqueAppointmentList();
        a1 = new Appointment("P001", LocalDateTime.of(2025, 10, 10, 10, 0),
                "Dr Tan", "Check-up");
        a2 = new Appointment("P002", LocalDateTime.of(2025, 10, 11, 10, 0),
                "Dr Lee", "Review");
    }

    @Test
    public void add_uniqueAppointments_success() {
        list.add(a1);
        list.add(a2);
        assertEquals(List.of(a1, a2), list.asUnmodifiableObservableList());
    }

    @Test
    public void add_duplicate_throwsException() {
        list.add(a1);
        assertThrows(DuplicateAppointmentException.class, () -> list.add(a1));
    }

    @Test
    public void setAppointments_duplicates_throwsException() {
        List<Appointment> withDupes = List.of(a1, a1);
        assertThrows(DuplicateAppointmentException.class, () -> list.setAppointments(withDupes));
    }

    @Test
    public void remove_existingAppointment_success() {
        list.add(a1);
        list.remove(a1);
        assertTrue(list.asUnmodifiableObservableList().isEmpty());
    }

    @Test
    public void toString_containsAppointments() {
        list.add(a1);
        String result = list.toString();
        assertTrue(result.contains("P001"));
        assertTrue(result.contains("Dr Tan"));
    }
}

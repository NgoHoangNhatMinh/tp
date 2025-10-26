package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Phone;

/**
 * Tests for {@link ViewAppointmentsCommand}.
 */
public class ViewAppointmentsCommandTest {

    private Model model;
    private Patient john;
    private Appointment a1;
    private Appointment a2;
    private Appointment a3;

    @BeforeEach
    public void setUp() {
        model = new ModelManager();
        john = new Patient(
                new Name("John Doe"),
                new Phone("12345678"),
                new Email("john@example.com"),
                new Address("123 Test Street")
                );

        model.addPatient(john);

        a1 = new Appointment("John Doe",
                LocalDateTime.of(2025, 1, 10, 9, 0),
                "Dr Tan", "Checkup");
        a2 = new Appointment("John Doe",
                LocalDateTime.of(2025, 3, 1, 14, 0),
                "Dr Lee", "Review");
        a3 = new Appointment("Alice",
                LocalDateTime.of(2025, 2, 1, 10, 0),
                "Dr Tan", "Other patient");

        model.addAppointment(a1);
        model.addAppointment(a2);
        model.addAppointment(a3);
    }

    @Test
    public void execute_validPatient_showsAppointmentsInOrder() throws Exception {
        ViewAppointmentsCommand cmd =
                new ViewAppointmentsCommand("John Doe", Optional.empty(), Optional.empty());
        CommandResult result = cmd.execute(model);

        assertEquals(
                String.format(ViewAppointmentsCommand.MESSAGE_SUCCESS_NO_RANGE, 2, "John Doe"),
                result.getFeedbackToUser());
        assertEquals(a1, model.getFilteredAppointmentList().get(0));
        assertEquals(a2, model.getFilteredAppointmentList().get(1));
    }

    @Test
    public void execute_withDateRange_filtersCorrectly() throws Exception {
        ViewAppointmentsCommand cmd =
                new ViewAppointmentsCommand("John Doe",
                        Optional.of(LocalDate.of(2025, 2, 1)),
                        Optional.of(LocalDate.of(2025, 12, 31)));
        CommandResult result = cmd.execute(model);

        assertEquals(
                String.format(ViewAppointmentsCommand.MESSAGE_SUCCESS_WITH_RANGE,
                        1, "John Doe", "2025-02-01", "2025-12-31"),
                result.getFeedbackToUser());
        assertEquals(a2, model.getFilteredAppointmentList().get(0));
    }

    @Test
    public void execute_patientNotFound_throwsCommandException() {
        ViewAppointmentsCommand cmd =
                new ViewAppointmentsCommand("Nonexistent", Optional.empty(), Optional.empty());
        assertThrows(CommandException.class, () -> cmd.execute(model));
    }
}

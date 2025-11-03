package seedu.hospital.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.hospital.logic.commands.exceptions.CommandException;
import seedu.hospital.model.HospitalContacts;
import seedu.hospital.model.Model;
import seedu.hospital.model.ModelManager;
import seedu.hospital.model.UserPrefs;
import seedu.hospital.model.appointment.Appointment;
import seedu.hospital.model.person.Address;
import seedu.hospital.model.person.Email;
import seedu.hospital.model.person.Name;
import seedu.hospital.model.person.Patient;
import seedu.hospital.model.person.Phone;

/**
 * Tests for {@link AddAppointmentCommand}.
 */
public class AddAppointmentCommandTest {

    private Model model;
    private Patient john;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new HospitalContacts(), new UserPrefs());
        john = new Patient(
                new Name("John Doe"),
                new Phone("12345678"),
                new Email("john@example.com"),
                new Address("123, Jurong West Ave 6, #08-111"));
        model.addPatient(john);
    }

    @Test
    public void execute_validAppointment_success() throws Exception {
        Appointment appt = new Appointment("John Doe",
                LocalDateTime.of(2025, 11, 11, 14, 0),
                "Dr Wee", "Check-up");

        AddAppointmentCommand cmd = new AddAppointmentCommand("John Doe", appt);
        CommandResult result = cmd.execute(model);

        assertEquals(String.format(AddAppointmentCommand.MESSAGE_SUCCESS, appt),
                result.getFeedbackToUser());
    }

    @Test
    public void execute_patientNotFound_throwsCommandException() {
        Appointment appt = new Appointment("Ghost",
                LocalDateTime.of(2025, 12, 1, 14, 0),
                "Dr Tan", "Test");

        AddAppointmentCommand cmd = new AddAppointmentCommand("Ghost", appt);
        assertThrows(CommandException.class, () -> cmd.execute(model));
    }

    @Test
    public void execute_duplicateAppointment_throwsCommandException() throws Exception {
        Appointment appt = new Appointment("John Doe",
                LocalDateTime.of(2025, 11, 11, 14, 0),
                "Dr Wee", "Check-up");

        model.addAppointment(appt);
        AddAppointmentCommand cmd = new AddAppointmentCommand("John Doe", appt);
        assertThrows(CommandException.class, () -> cmd.execute(model));
    }
}

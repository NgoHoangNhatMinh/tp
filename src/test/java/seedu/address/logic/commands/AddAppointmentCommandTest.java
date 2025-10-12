package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;

/**
 * Tests for {@link AddAppointmentCommand}.
 */
public class AddAppointmentCommandTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    }

    @Test
    public void execute_validAppointment_success() throws Exception {
        Appointment appt = new Appointment("John Doe",
                LocalDateTime.of(2025, 11, 11, 14, 0),
                "Dr Wee", "Check-up");

        AddAppointmentCommand cmd = new AddAppointmentCommand(ALICE.getName().fullName, appt);
        CommandResult result = cmd.execute(model);

        assertEquals(String.format(AddAppointmentCommand.MESSAGE_SUCCESS, appt), result.getFeedbackToUser());
    }

    @Test
    public void execute_patientNotFound_throwsCommandException() {
        Appointment appt = new Appointment("Nonexistent", LocalDateTime.now().plusDays(1), "Dr Tan", "Test");
        AddAppointmentCommand cmd = new AddAppointmentCommand("Nonexistent", appt);
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

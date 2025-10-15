package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.Collections;

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
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.tag.Tag;

/**
 * Tests for {@link AddAppointmentCommand}.
 */
public class AddAppointmentCommandTest {

    private Model model;
    private Person john;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(new seedu.address.model.AddressBook(), new UserPrefs());
        john = new Person(
                new Name("John Doe"),
                new Phone("12345678"),
                new Email("john@example.com"),
                new Address("123, Jurong West Ave 6, #08-111"),
                Collections.<Tag>emptySet());
        model.addPerson(john);
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

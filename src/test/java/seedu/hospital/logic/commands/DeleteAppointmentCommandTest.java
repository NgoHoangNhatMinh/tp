package seedu.hospital.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.hospital.testutil.Assert.assertThrows;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.hospital.logic.commands.exceptions.CommandException;
import seedu.hospital.model.Model;
import seedu.hospital.model.ModelManager;
import seedu.hospital.model.appointment.Appointment;

public class DeleteAppointmentCommandTest {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    @Test
    public void execute_validAppointment_success() throws Exception {
        Model model = new ModelManager();
        LocalDateTime dateTime = LocalDateTime.parse("2025-01-01 10:00", DATE_TIME_FORMATTER);
        Appointment appointment = new Appointment("John Doe", dateTime, "Dr. Smith",
                "Checkup");
        model.addAppointment(appointment);

        DeleteAppointmentCommand command = new DeleteAppointmentCommand(
                "John Doe",
                "2025-01-01 10"
        );

        CommandResult result = command.execute(model);

        assertEquals(String.format(DeleteAppointmentCommand.MESSAGE_DELETE_APPOINTMENT_SUCCESS, appointment),
                result.getFeedbackToUser());
    }

    @Test
    public void execute_appointmentNotFound_throwsCommandException() {
        Model model = new ModelManager();
        LocalDateTime dateTime = LocalDateTime.parse("2025-01-01 10:00", DATE_TIME_FORMATTER);
        Appointment appointment = new Appointment("John Doe", dateTime, "Dr. Smith",
                "Checkup");
        model.addAppointment(appointment);

        DeleteAppointmentCommand command = new DeleteAppointmentCommand("Nonexistent Patient",
                "2025-01-01 10");

        assertThrows(CommandException.class,
                String.format(DeleteAppointmentCommand.MESSAGE_APPOINTMENT_NOT_FOUND,
                        "Nonexistent Patient", "2025-01-01 10"), () -> command.execute(model));
    }

    @Test
    public void execute_invalidDateFormat_throwsCommandException() {
        Model model = new ModelManager();

        DeleteAppointmentCommand command = new DeleteAppointmentCommand("John Doe",
                "invalid-date");

        assertThrows(CommandException.class,
                DeleteAppointmentCommand.MESSAGE_INVALID_DATE_FORMAT, () -> command.execute(model));
    }

    @Test
    public void execute_differentHourSameDay_notFound() throws Exception {
        Model model = new ModelManager();
        LocalDateTime dateTime = LocalDateTime.parse("2025-01-01 10:00", DATE_TIME_FORMATTER);
        Appointment appointment = new Appointment("John Doe", dateTime, "Dr. Smith",
                "Checkup");
        model.addAppointment(appointment);

        DeleteAppointmentCommand command = new DeleteAppointmentCommand("John Doe",
                "2025-01-01 11");

        assertThrows(CommandException.class,
                String.format(DeleteAppointmentCommand.MESSAGE_APPOINTMENT_NOT_FOUND, "John Doe", "2025-01-01 11"), ()
                        -> command.execute(model));
    }

    @Test
    public void execute_sameHourDifferentDay_notFound() throws Exception {
        Model model = new ModelManager();
        LocalDateTime dateTime = LocalDateTime.parse("2025-01-01 10:00", DATE_TIME_FORMATTER);
        Appointment appointment = new Appointment("John Doe", dateTime, "Dr. Smith",
                "Checkup");
        model.addAppointment(appointment);

        DeleteAppointmentCommand command = new DeleteAppointmentCommand("John Doe",
                "2025-01-02 10");

        assertThrows(CommandException.class,
                String.format(DeleteAppointmentCommand.MESSAGE_APPOINTMENT_NOT_FOUND, "John Doe", "2025-01-02 10"), ()
                        -> command.execute(model));
    }

    @Test
    public void equals() {
        DeleteAppointmentCommand firstCommand = new DeleteAppointmentCommand("John Doe",
                "2025-01-01 10");
        DeleteAppointmentCommand secondCommand = new DeleteAppointmentCommand("Mary Tan",
                "2025-01-01 10");
        DeleteAppointmentCommand thirdCommand = new DeleteAppointmentCommand("John Doe",
                "2025-01-02 11");

        assertEquals(firstCommand, firstCommand);

        DeleteAppointmentCommand firstCommandCopy = new DeleteAppointmentCommand("John Doe",
                "2025-01-01 10");
        assertEquals(firstCommand, firstCommandCopy);

        assertNotEquals(1, firstCommand);

        assertNotEquals(null, firstCommand);

        assertNotEquals(firstCommand, secondCommand);

        assertNotEquals(firstCommand, thirdCommand);
    }
}

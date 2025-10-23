package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;

/**
 * Deletes an appointment identified using the patient name and appointment time from the address book.
 */
public class DeleteAppointmentCommand extends Command {
    public static final String COMMAND_WORD = "a-delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the appointment identified by the patient name and time created used in the "
            + "appointment list.\n"
            + "Parameters: p/PATIENT_NAME (full name) t/DATE_TIME(yyyy-MM-dd HH)\n"
            + "Example: " + COMMAND_WORD + " p/John Tan t/2025-11-11 14";

    public static final String MESSAGE_DELETE_APPOINTMENT_SUCCESS = "Appointment deleted: %1$s";
    public static final String MESSAGE_APPOINTMENT_NOT_FOUND = "No appointment found for patient '%1$s' at time '%2$s'";
    public static final String MESSAGE_INVALID_DATE_FORMAT = "Invalid date format. Please use yyyy-MM-dd HH "
            + "(e.g., 2025-11-11 14)";

    private final String patientName;
    private final String dateTimeString;

    /**
     * Constructs a DeleteAppointmentCommand to delete a specific appointment.
     *
     * @param patientName The full name of the patient whose appointment is to be deleted
     * @param dateTimeString The date and time of the appointment in yyyy-MM-dd HH format
     */
    public DeleteAppointmentCommand(String patientName, String dateTimeString) {
        this.patientName = patientName;
        this.dateTimeString = dateTimeString;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Appointment> lastShownList = model.getFilteredAppointmentList(); // nned implement

        LocalDateTime targetDateTime;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
            targetDateTime = LocalDateTime.parse(dateTimeString, formatter);
        } catch (DateTimeParseException e) {
            throw new CommandException(MESSAGE_INVALID_DATE_FORMAT);
        }

        Appointment appointmentToDelete = null;
        for (Appointment appointment : lastShownList) {
            if (appointment.getPatientName().equals(patientName)
                    && isSameDateAndHour(appointment.getDateTime(), targetDateTime)) {
                appointmentToDelete = appointment;
                break;
            }
        }

        if (appointmentToDelete == null) {
            throw new CommandException(String.format(MESSAGE_APPOINTMENT_NOT_FOUND,
                    patientName, dateTimeString));
        }

        model.deleteAppointment(appointmentToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_APPOINTMENT_SUCCESS, appointmentToDelete));
    }

    /**
     * Checks if two LocalDateTime objects have the same date and hour (ignoring minutes and seconds).
     */
    private boolean isSameDateAndHour(LocalDateTime dateTime1, LocalDateTime dateTime2) {
        return dateTime1.getYear() == dateTime2.getYear()
                && dateTime1.getMonth() == dateTime2.getMonth()
                && dateTime1.getDayOfMonth() == dateTime2.getDayOfMonth()
                && dateTime1.getHour() == dateTime2.getHour();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DeleteAppointmentCommand
                && patientName.equals(((DeleteAppointmentCommand) other).patientName)
                && dateTimeString.equals(((DeleteAppointmentCommand) other).dateTimeString));
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("patientName", patientName)
                .add("dateTime", dateTimeString)
                .toString();
    }
}

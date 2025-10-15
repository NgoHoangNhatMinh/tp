package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;

/**
 * Parses input arguments and creates a new {@code AddAppointmentCommand} object.
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {

    private static final Prefix PREFIX_APPT_PATIENT = new Prefix("p/");
    private static final Prefix PREFIX_APPT_DOCTOR = new Prefix("d/");
    private static final Prefix PREFIX_APPT_TIME = new Prefix("t/");
    private static final Prefix PREFIX_APPT_NOTE = new Prefix("note/");

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd[ HH:mm]");

    @Override
    public AddAppointmentCommand parse(String raw) throws ParseException {
        // ✅ Key fix: prepend space for correct tokenization
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                " " + raw, PREFIX_APPT_PATIENT, PREFIX_APPT_DOCTOR, PREFIX_APPT_TIME, PREFIX_APPT_NOTE);

        if (!arePrefixesPresent(argMultimap, PREFIX_APPT_PATIENT, PREFIX_APPT_DOCTOR, PREFIX_APPT_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE));
        }

        String patientName = argMultimap.getValue(PREFIX_APPT_PATIENT).get();
        String doctor = argMultimap.getValue(PREFIX_APPT_DOCTOR).get();
        String timeRaw = argMultimap.getValue(PREFIX_APPT_TIME).get();
        String note = argMultimap.getValue(PREFIX_APPT_NOTE).orElse("");

        LocalDateTime dateTime;
        try {
            dateTime = LocalDateTime.parse(timeRaw, FORMATTER);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid datetime format. Use yyyy-MM-dd HH:mm");
        }

        Appointment appointment = new Appointment(patientName, dateTime, doctor, note);
        return new AddAppointmentCommand(patientName, appointment);
    }

    private static boolean arePrefixesPresent(ArgumentMultimap map, Prefix... prefixes) {
        for (Prefix prefix : prefixes) {
            if (map.getValue(prefix).isEmpty()) {
                return false;
            }
        }
        return true;
    }
}

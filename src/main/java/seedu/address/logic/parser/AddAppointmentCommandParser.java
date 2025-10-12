package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_DOCTOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_NOTE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_PATIENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_TIME;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.stream.Stream;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;

/**
 * Parses input arguments and creates a new {@code AddAppointmentCommand} object.
 */
public class AddAppointmentCommandParser implements Parser<AddAppointmentCommand> {

    @Override
    public AddAppointmentCommand parse(String args) throws ParseException {
        String raw = args.trim();
        if (raw.startsWith("add")) {  // tolerate if upstream forgot to strip
            raw = raw.substring(3).trim();
        }

        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                " " + raw, PREFIX_APPT_PATIENT, PREFIX_APPT_DOCTOR, PREFIX_APPT_TIME, PREFIX_APPT_NOTE);

        System.out.println("DEBUG -- PREAMBLE=[" + argMultimap.getPreamble() + "]");
        System.out.println("DEBUG -- p/ present=" + argMultimap.getValue(PREFIX_APPT_PATIENT).isPresent());
        System.out.println("DEBUG -- d/ present=" + argMultimap.getValue(PREFIX_APPT_DOCTOR).isPresent());
        System.out.println("DEBUG -- t/ present=" + argMultimap.getValue(PREFIX_APPT_TIME).isPresent());
        System.out.println("DEBUG -- note/ present=" + argMultimap.getValue(PREFIX_APPT_NOTE).isPresent());

        if (!arePrefixesPresent(argMultimap, PREFIX_APPT_PATIENT, PREFIX_APPT_DOCTOR, PREFIX_APPT_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddAppointmentCommand.MESSAGE_USAGE));
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_APPT_PATIENT, PREFIX_APPT_DOCTOR, PREFIX_APPT_TIME);

        String patientName = argMultimap.getValue(PREFIX_APPT_PATIENT).get();
        String doctor = argMultimap.getValue(PREFIX_APPT_DOCTOR).get();
        String timeRaw = argMultimap.getValue(PREFIX_APPT_TIME).get();
        String note = argMultimap.getValue(PREFIX_APPT_NOTE).orElse("");

        LocalDateTime dateTime;
        try {
            // accept "yyyy-MM-dd HH:mm" or just "yyyy-MM-dd"
            DateTimeFormatter FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd[' 'HH:mm]");
            dateTime = LocalDateTime.parse(timeRaw, FMT);
        } catch (DateTimeParseException e) {
            throw new ParseException("Invalid datetime format. Use yyyy-MM-dd HH:mm");
        }

        Appointment appt = new Appointment(patientName, dateTime, doctor, note);
        return new AddAppointmentCommand(patientName, appt);
    }

    /** AB3-style helper — note the use of .isPresent() */
    private static boolean arePrefixesPresent(ArgumentMultimap mm, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> mm.getValue(prefix).isPresent());
    }

}

package seedu.address.logic.parser;

import seedu.address.logic.commands.Command;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses appointment-related commands (root command: "appt").
 */
public class AppointmentCommandParser implements Parser<Command> {

    @Override
    public Command parse(String args) throws ParseException {
        final String trimmed = args.trim();
        if (trimmed.startsWith("add")) {
            final String remainder = trimmed.substring(3).trim();
            return new AddAppointmentCommandParser().parse(remainder);
        }
        throw new ParseException("Unknown appointment subcommand. Use: appt add ...");
    }
}

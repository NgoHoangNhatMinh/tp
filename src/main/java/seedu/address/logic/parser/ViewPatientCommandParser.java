package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import seedu.address.logic.commands.ViewPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameMatchesPredicate;

/**
 * Parses input arguments and creates a new ViewPatientCommand object
 */
public class ViewPatientCommandParser {
    /**
     * Parses the given {@code String} of arguments in the context of the ViewPatientCommand
     * and returns a ViewPatientCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewPatientCommand parse(String args) throws ParseException {
        String trimmedArgs = args.trim();
        if (trimmedArgs.isEmpty()) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, ViewPatientCommand.MESSAGE_USAGE));
        }

        String keyword = trimmedArgs;

        return new ViewPatientCommand(new NameMatchesPredicate(keyword));
    }

}

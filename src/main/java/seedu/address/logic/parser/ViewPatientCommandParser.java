package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.stream.Stream;

import seedu.address.logic.commands.ViewPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameMatchesPredicate;

/**
 * Parses input arguments and creates a new ViewPatientCommand object
 */
public class ViewPatientCommandParser {

    private static final Prefix PREFIX_PATIENT = new Prefix("p/");

    /**
     * Parses the given {@code String} of arguments in the context of the
     * ViewPatientCommand
     * and returns a ViewPatientCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public ViewPatientCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_PATIENT);
        if (!arePrefixesPresent(argMultimap, PREFIX_PATIENT) || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    ViewPatientCommand.MESSAGE_USAGE));
        }
        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_PATIENT);
        String keyword = argMultimap.getValue(PREFIX_PATIENT).get();

        return new ViewPatientCommand(new NameMatchesPredicate(keyword));
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values
     * in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

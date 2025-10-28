package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import java.util.stream.Stream;

import seedu.address.logic.commands.DeletePatientInfoCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeletePatientInfoCommand object
 */
public class DeletePatientInfoCommandParser implements Parser<DeletePatientInfoCommand> {

    @Override
    public DeletePatientInfoCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args, PREFIX_NAME);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME)) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                            DeletePatientInfoCommand.MESSAGE_USAGE)
            );
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_NAME);

        String patientName = argMultimap.getValue(PREFIX_NAME).get().trim();
        return new DeletePatientInfoCommand(patientName);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argMultimap.getValue(prefix).isPresent());
    }
}

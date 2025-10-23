package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeletePrescriptionCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses input arguments and creates a new DeletePrescriptionCommand object
 */
public class DeletePrescriptionCommandParser implements Parser<DeletePrescriptionCommand> {
    /**
     * Parses the given {@code String} of arguments in the context of the DeletePrescriptionCommand
     * and returns a DeletePrescriptionCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public DeletePrescriptionCommand parse(String args) throws ParseException {
        try {
            Index index = ParserUtil.parseIndex(args);
            return new DeletePrescriptionCommand(index);
        } catch (ParseException pe) {
            throw new ParseException(
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeletePrescriptionCommand.MESSAGE_USAGE), pe);
        }
    }
}

package seedu.hospital.logic.parser;

import static seedu.hospital.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hospital.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.hospital.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.hospital.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.hospital.logic.commands.DeletePrescriptionCommand;

public class DeletePrescriptionCommandParserTest {
    private DeletePrescriptionCommandParser parser = new DeletePrescriptionCommandParser();

    @Test
    public void parse_validArgs_returnsDeleteCommand() {
        assertParseSuccess(parser, "1", new DeletePrescriptionCommand(INDEX_FIRST_PERSON));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
            DeletePrescriptionCommand.MESSAGE_USAGE));
    }

}

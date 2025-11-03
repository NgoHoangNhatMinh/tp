package seedu.hospital.logic.parser;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.hospital.logic.commands.AddAppointmentCommand;
import seedu.hospital.logic.parser.exceptions.ParseException;

/**
 * Tests for {@link AppointmentCommandParser}.
 */
public class AppointmentCommandParserTest {

    private final AppointmentCommandParser parser = new AppointmentCommandParser();

    @Test
    public void parse_addSubcommand_success() throws Exception {
        String input = "add n/John Doe d/Dr Wee t/2025-11-11 14:00 note/Test";
        assertTrue(parser.parse(input) instanceof AddAppointmentCommand);
    }

    @Test
    public void parse_unknownSubcommand_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parse("delete n/John Doe"));
    }
}

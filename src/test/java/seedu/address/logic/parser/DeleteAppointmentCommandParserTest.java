package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_APPT_TIME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;

public class DeleteAppointmentCommandParserTest {

    private DeleteAppointmentCommandParser parser = new DeleteAppointmentCommandParser();



    @Test
    public void parse_missingPatientPrefix_throwsParseException() {
        String userInput = " " + PREFIX_APPT_TIME + "2025-01-01 10";

        ParseException thrown = assertThrows(ParseException.class, () -> parser.parse(userInput));
        assertEquals(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAppointmentCommand.MESSAGE_USAGE), thrown.getMessage());
    }

    @Test
    public void parse_missingTimePrefix_throwsParseException() {
        String userInput = " " + PREFIX_PATIENT + "John Doe";

        ParseException thrown = assertThrows(ParseException.class, () -> parser.parse(userInput));
        assertEquals(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAppointmentCommand.MESSAGE_USAGE), thrown.getMessage());
    }

    @Test
    public void parse_invalidDateFormat_throwsParseException() {
        String userInput = " " + PREFIX_PATIENT + "John Doe " + PREFIX_APPT_TIME + "invalid-date";

        ParseException thrown = assertThrows(ParseException.class, () -> parser.parse(userInput));
        assertEquals(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAppointmentCommand.MESSAGE_USAGE), thrown.getMessage());
    }

    @Test
    public void parse_emptyPatientName_throwsParseException() {
        String userInput = " " + PREFIX_APPT_TIME + "2025-01-01 10";

        ParseException thrown = assertThrows(ParseException.class, () -> parser.parse(userInput));
        assertEquals(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAppointmentCommand.MESSAGE_USAGE), thrown.getMessage());
    }

    @Test
    public void parse_preamblePresent_throwsParseException() {
        String userInput = "preamble " + PREFIX_PATIENT + "John Doe " + PREFIX_APPT_TIME + "2025-01-01 10";

        ParseException thrown = assertThrows(ParseException.class, () -> parser.parse(userInput));
        assertEquals(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteAppointmentCommand.MESSAGE_USAGE), thrown.getMessage());
    }

    @Test
    public void parse_validArgsWithWhitespace_returnsDeleteAppointmentCommand() throws Exception {
        String userInput = "   " + PREFIX_PATIENT + "  John Doe  " + PREFIX_APPT_TIME + "  2025-01-01 10  ";
        DeleteAppointmentCommand command = parser.parse(userInput);

        assertEquals("John Doe", command.getPatientName());
        assertEquals("2025-01-01 10", command.getDateTime());
    }
}

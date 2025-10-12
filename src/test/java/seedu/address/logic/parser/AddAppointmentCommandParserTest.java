package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AddAppointmentCommand.MESSAGE_USAGE;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.appointment.Appointment;

/**
 * Tests for {@link AddAppointmentCommandParser}.
 */
public class AddAppointmentCommandParserTest {

    private final AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws Exception {
        String userInput = " p/John Doe d/Dr Wee t/2025-11-11 14:00 note/Follow-up";

        Appointment expected = new Appointment("John Doe",
                LocalDateTime.of(2025, 11, 11, 14, 0),
                "Dr Wee", "Follow-up");

        AddAppointmentCommand command = parser.parse(userInput);
        AddAppointmentCommand expectedCommand = new AddAppointmentCommand("John Doe", expected);
        assertEquals(expectedCommand, command);
    }

    @Test
    public void parse_missingRequiredPrefix_throwsParseException() {
        String invalidInput = " d/Dr Wee t/2025-11-11 14:00";
        assertThrows(ParseException.class, () ->
                parser.parse(invalidInput),
                MESSAGE_INVALID_COMMAND_FORMAT + MESSAGE_USAGE);
    }

    @Test
    public void parse_invalidDateTimeFormat_throwsParseException() {
        String invalidDate = " p/John Doe d/Dr Wee t/11-11-2025 note/test";
        assertThrows(ParseException.class, () -> parser.parse(invalidDate));
    }
}

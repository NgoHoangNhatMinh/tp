package seedu.hospital.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static seedu.hospital.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hospital.logic.commands.AddAppointmentCommand.MESSAGE_USAGE;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.hospital.logic.commands.AddAppointmentCommand;
import seedu.hospital.logic.parser.exceptions.ParseException;
import seedu.hospital.model.appointment.Appointment;

/**
 * Tests for {@link AddAppointmentCommandParser}.
 */
public class AddAppointmentCommandParserTest {

    private final AddAppointmentCommandParser parser = new AddAppointmentCommandParser();

    @Test
    public void parse_allFieldsPresent_success() throws Exception {
        String userInput = " n/John Doe d/Dr Wee t/2025-11-11 14:00 note/Fp";

        Appointment expectedAppointment = new Appointment("John Doe",
                LocalDateTime.of(2025, 11, 11, 14, 0),
                "Dr Wee", "Fp");

        AddAppointmentCommand expectedCommand =
                new AddAppointmentCommand("John Doe", expectedAppointment);

        AddAppointmentCommand actualCommand = parser.parse(userInput);
        assertEquals(expectedCommand, actualCommand);
    }

    @Test
    public void parse_missingRequiredPrefix_throwsParseException() {
        String invalidInput = " d/Dr Wee t/2025-11-11 14:00 note/Fp";
        assertThrows(ParseException.class, () ->
                        parser.parse(invalidInput),
                MESSAGE_INVALID_COMMAND_FORMAT + MESSAGE_USAGE);
    }

    @Test
    public void parse_invalidDateTimeFormat_throwsParseException() {
        String invalidDate = " n/John Doe d/Dr Wee t/11-11-2025 note/test";
        assertThrows(ParseException.class, () -> parser.parse(invalidDate));
    }
}

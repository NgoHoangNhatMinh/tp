package seedu.hospital.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;

import seedu.hospital.logic.commands.ViewAppointmentsCommand;
import seedu.hospital.logic.parser.exceptions.ParseException;

/**
 * Tests for {@link ViewAppointmentsCommandParser}.
 */
public class ViewAppointmentsCommandParserTest {

    private final ViewAppointmentsCommandParser parser = new ViewAppointmentsCommandParser();

    @Test
    public void parse_minimumValidArgs_success() throws Exception {
        String input = " n/John Doe";
        ViewAppointmentsCommand expected =
                new ViewAppointmentsCommand("John Doe", Optional.empty(), Optional.empty());
        assertEquals(expected, parser.parse(input));
    }

    @Test
    public void parse_withFromAndTo_success() throws Exception {
        String input = " n/John Doe from/2025-01-01 to/2025-12-31";
        ViewAppointmentsCommand expected =
                new ViewAppointmentsCommand("John Doe",
                        Optional.of(LocalDate.of(2025, 1, 1)),
                        Optional.of(LocalDate.of(2025, 12, 31)));
        assertEquals(expected, parser.parse(input));
    }

    @Test
    public void parse_invalidDateFormat_throwsParseException() {
        String input = " n/John Doe from/01-01-2025";
        assertThrows(ParseException.class, () -> parser.parse(input));
    }

    @Test
    public void parse_fromAfterTo_throwsParseException() {
        String input = " n/John Doe from/2025-12-31 to/2025-01-01";
        assertThrows(ParseException.class, () -> parser.parse(input));
    }

    @Test
    public void parse_missingPatientPrefix_throwsParseException() {
        String input = " John Doe from/2025-01-01 to/2025-12-31";
        assertThrows(ParseException.class, () -> parser.parse(input));
    }
}

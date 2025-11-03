package seedu.hospital.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hospital.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hospital.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.hospital.testutil.Assert.assertThrows;
import static seedu.hospital.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.hospital.logic.commands.AddAppointmentCommand;
//import seedu.hospital.logic.commands.ClearCommand;
import seedu.hospital.logic.commands.ClearCommand;
import seedu.hospital.logic.commands.DeleteCommand;
import seedu.hospital.logic.commands.DeletePrescriptionCommand;
import seedu.hospital.logic.commands.EditCommand;
import seedu.hospital.logic.commands.EditCommand.EditPatientDescriptor;
import seedu.hospital.logic.commands.ExitCommand;
import seedu.hospital.logic.commands.FindCommand;
import seedu.hospital.logic.commands.HelpCommand;
import seedu.hospital.logic.commands.ListCommand;
import seedu.hospital.logic.commands.ListPrescriptionsCommand;
import seedu.hospital.logic.commands.ViewPrescriptionsCommand;
import seedu.hospital.logic.parser.exceptions.ParseException;
import seedu.hospital.model.person.NameContainsAnyKeywordsPredicate;
import seedu.hospital.model.person.Patient;
import seedu.hospital.testutil.EditPatientDescriptorBuilder;
import seedu.hospital.testutil.PatientBuilder;
import seedu.hospital.testutil.PatientUtil;

public class HospitalContactsParserTest {

    private final HospitalContactsParser parser = new HospitalContactsParser();

    //  Tests are commented out temporarily to be modified later.
    //    @Test
    //    public void parseCommand_add() throws Exception {
    //        Patient person = new PatientBuilder().build();
    //        AddCommand command = (AddCommand) parser.parseCommand(PatientUtil.getAddCommand(person));
    //        assertEquals(new AddCommand(person), command);
    //    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Patient person = new PatientBuilder().build();
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PatientUtil.getEditPatientDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsAnyKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_addAppointment_returnsAddAppointmentCommand() throws Exception {
        assertTrue(parser.parseCommand("a-add n/John Doe d/Dr Wee t/2025-11-11 14:00 note/Fp")
                instanceof AddAppointmentCommand);
    }

    @Test
    public void parseCommand_viewPrescriptions_returnsViewPrescriptionsCommand() throws Exception {
        assertTrue(parser.parseCommand("p-view n/Alex Yeoh") instanceof ViewPrescriptionsCommand);
    }

    @Test
    public void parseCommand_listPrescriptions_returnsListPrescriptionsCommand() throws Exception {
        assertTrue(parser.parseCommand("p-list") instanceof ListPrescriptionsCommand);
    }

    @Test
    public void parseCommand_deletePrescription_returnsDeletePrescriptionCommand() throws Exception {
        assertTrue(parser.parseCommand("p-delete 1") instanceof DeletePrescriptionCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}

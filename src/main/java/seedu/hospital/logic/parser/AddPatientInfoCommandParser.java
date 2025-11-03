package seedu.hospital.logic.parser;

import static seedu.hospital.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hospital.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.hospital.logic.parser.CliSyntax.PREFIX_DOB;
import static seedu.hospital.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.hospital.logic.parser.CliSyntax.PREFIX_EMERGENCY;
import static seedu.hospital.logic.parser.CliSyntax.PREFIX_GENDER;
import static seedu.hospital.logic.parser.CliSyntax.PREFIX_ID;
import static seedu.hospital.logic.parser.CliSyntax.PREFIX_LANG;
import static seedu.hospital.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.hospital.logic.parser.CliSyntax.PREFIX_PHONE;

import java.util.stream.Stream;

import seedu.hospital.logic.commands.AddPatientInfoCommand;
import seedu.hospital.logic.parser.exceptions.ParseException;
import seedu.hospital.model.person.Address;
import seedu.hospital.model.person.Birthday;
import seedu.hospital.model.person.Email;
import seedu.hospital.model.person.Name;
import seedu.hospital.model.person.Patient;
import seedu.hospital.model.person.Phone;


/**
 * Parses one i-add Command, including all input arguments and creates a new {@code AddPatientInfoCommand} object.
 */
public class AddPatientInfoCommandParser implements Parser<AddPatientInfoCommand> {

    @Override
    public AddPatientInfoCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(
                args, PREFIX_NAME, PREFIX_DOB, PREFIX_GENDER, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_EMERGENCY, PREFIX_ID,
                PREFIX_LANG);

        if (!arePrefixesPresent(argMultimap, PREFIX_NAME, PREFIX_DOB, PREFIX_GENDER, PREFIX_PHONE,
                PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_EMERGENCY, PREFIX_ID, PREFIX_LANG)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AddPatientInfoCommand.MESSAGE_USAGE));
        }

        // Parse all fields, tags ignored for now
        Name name = ParserUtil.parseName(argMultimap.getValue(PREFIX_NAME).get());
        Birthday birthday = ParserUtil.parseBirthday(argMultimap.getValue(PREFIX_DOB).get());
        String gender = argMultimap.getValue(PREFIX_GENDER).get();
        Phone phone = ParserUtil.parsePhone(argMultimap.getValue(PREFIX_PHONE).get());
        Email email = ParserUtil.parseEmail(argMultimap.getValue(PREFIX_EMAIL).get());
        Address address = ParserUtil.parseAddress(argMultimap.getValue(PREFIX_ADDRESS).get());
        String emergency = argMultimap.getValue(PREFIX_EMERGENCY).get();
        String id = argMultimap.getValue(PREFIX_ID).get();
        String lang = argMultimap.getValue(PREFIX_LANG).get();

        Patient patient = new Patient(name, birthday, gender, phone, email, address, emergency, id, lang);
        return new AddPatientInfoCommand(patient);
    }

    /**
     * Returns true if none of the prefixes contains empty {@code Optional} values in the given
     * {@code ArgumentMultimap}.
     */
    private static boolean arePrefixesPresent(ArgumentMultimap argumentMultimap, Prefix... prefixes) {
        return Stream.of(prefixes).allMatch(prefix -> argumentMultimap.getValue(prefix).isPresent());
    }
}

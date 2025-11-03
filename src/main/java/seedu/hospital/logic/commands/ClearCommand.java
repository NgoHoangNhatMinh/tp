package seedu.hospital.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.hospital.model.HospitalContacts;
import seedu.hospital.model.Model;

/**
 * Clears the hospital book.
 */
public class ClearCommand extends Command {

    public static final String COMMAND_WORD = "clear";
    public static final String MESSAGE_SUCCESS = "Address book has been cleared!";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.setHospitalContacts(new HospitalContacts());
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

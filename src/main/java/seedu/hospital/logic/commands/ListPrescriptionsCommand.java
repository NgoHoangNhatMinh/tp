package seedu.hospital.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.hospital.logic.commands.ViewType.PRESCRIPTION_LIST;
import static seedu.hospital.model.Model.PREDICATE_SHOW_ALL_PRESCRIPTIONS;

import seedu.hospital.model.Model;

/**
 * Lists all prescriptions in the hospital book to the user.
 */
public class ListPrescriptionsCommand extends Command {
    public static final String COMMAND_WORD = "p-list";

    public static final String MESSAGE_SUCCESS = "Listed all prescriptions";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPrescriptionList(PREDICATE_SHOW_ALL_PRESCRIPTIONS);
        return new CommandResult(MESSAGE_SUCCESS, PRESCRIPTION_LIST);
    }
}

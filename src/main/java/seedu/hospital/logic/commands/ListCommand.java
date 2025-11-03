package seedu.hospital.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.hospital.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.hospital.model.Model;

/**
 * Lists all persons in the hospital book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_SUCCESS = "Listed all persons";


    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

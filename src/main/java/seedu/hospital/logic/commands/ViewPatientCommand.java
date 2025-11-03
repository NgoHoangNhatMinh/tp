package seedu.hospital.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.hospital.commons.util.ToStringBuilder;
import seedu.hospital.logic.Messages;
import seedu.hospital.model.Model;
import seedu.hospital.model.person.NameMatchesPredicate;

/**
 * Finds and lists all patients in hospital book whose name matches the argument keyword.
 * Keyword matching is case insensitive.
 */
public class ViewPatientCommand extends Command {
    public static final String COMMAND_WORD = "i-view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose names contains "
            + "the entire keyword (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters:  n/[KEYWORD]\n"
            + "Example: " + COMMAND_WORD + " n/Alex Yeoh";

    private final NameMatchesPredicate predicate;

    public ViewPatientCommand(NameMatchesPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPatientList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPatientList().size()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewPatientCommand)) {
            return false;
        }

        ViewPatientCommand otherViewPatientCommand = (ViewPatientCommand) other;
        return predicate.equals(otherViewPatientCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("predicate", predicate)
                .toString();
    }
}

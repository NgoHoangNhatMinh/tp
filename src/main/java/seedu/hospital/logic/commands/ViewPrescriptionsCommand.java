package seedu.hospital.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.hospital.commons.util.ToStringBuilder;
import seedu.hospital.logic.Messages;
import seedu.hospital.model.Model;
import seedu.hospital.model.prescription.HavingPatientIdPredicate;

/**
 * Lists all prescriptions in the hospital book to the user.
 */
public class ViewPrescriptionsCommand extends Command {
    public static final String COMMAND_WORD = "p-view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all prescriptions tagged to "
        + "the specific patient name and displays them as a list.\n"
        + "Parameters: n/PATIENT_NAME \n"
        + "Example: " + COMMAND_WORD + " n/Alex Yeoh";

    private final HavingPatientIdPredicate predicate;

    public ViewPrescriptionsCommand(HavingPatientIdPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPrescriptionList(predicate);
        return new CommandResult(
            String.format(Messages.MESSAGE_PRESCRIPTION_LISTED_OVERVIEW, model.getFilteredPrescriptionList().size()),
            false, false, ViewType.PRESCRIPTION_LIST);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewPrescriptionsCommand)) {
            return false;
        }

        ViewPrescriptionsCommand otherViewPrescriptionCommand = (ViewPrescriptionsCommand) other;
        return predicate.equals(otherViewPrescriptionCommand.predicate);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("predicate", predicate)
            .toString();
    }
}

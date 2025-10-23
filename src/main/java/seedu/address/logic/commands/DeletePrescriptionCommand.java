package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.prescription.Prescription;


/**
 * Deletes a prescription identified using it's displayed index from the address book.
 */
public class DeletePrescriptionCommand extends Command {
    public static final String COMMAND_WORD = "p-delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Deletes the prescription identified by the index number used in the displayed prescription list.\n"
        + "Parameters: INDEX (must be a positive integer)\n"
        + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PRESCRIPTION_SUCCESS = "Deleted Prescription: %1$s";

    private final Index targetIndex;

    public DeletePrescriptionCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Prescription> lastShownList = model.getFilteredPrescriptionList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PRESCRIPTION_DISPLAYED_INDEX);
        }

        Prescription prescriptionToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deletePrescription(prescriptionToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PRESCRIPTION_SUCCESS,
            Messages.format(prescriptionToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletePrescriptionCommand)) {
            return false;
        }

        DeletePrescriptionCommand otherDeletePrescriptionCommand = (DeletePrescriptionCommand) other;
        return targetIndex.equals(otherDeletePrescriptionCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
            .add("targetIndex", targetIndex)
            .toString();
    }
}

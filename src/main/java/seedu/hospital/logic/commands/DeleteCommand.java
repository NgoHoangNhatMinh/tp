package seedu.hospital.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.ArrayList;
import java.util.List;

import seedu.hospital.commons.core.index.Index;
import seedu.hospital.commons.util.ToStringBuilder;
import seedu.hospital.logic.Messages;
import seedu.hospital.logic.commands.exceptions.CommandException;
import seedu.hospital.model.Model;
import seedu.hospital.model.person.Patient;
import seedu.hospital.model.prescription.HavingPatientIdPredicate;
import seedu.hospital.model.prescription.Prescription;

/**
 * Deletes a person identified using it's displayed index from the hospital book.
 */
public class DeleteCommand extends Command {

    public static final String COMMAND_WORD = "delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_PERSON_SUCCESS = "Deleted Patient: %1$s";

    private final Index targetIndex;

    public DeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.updateFilteredPrescriptionList(new HavingPatientIdPredicate(patientToDelete.getName().fullName));
        List<Prescription> prescriptionsToDelete = new ArrayList<>(model.getFilteredPrescriptionList());
        for (Prescription p : prescriptionsToDelete) {
            model.deletePrescription(p);
        }
        model.deletePatient(patientToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PERSON_SUCCESS, Messages.format(patientToDelete)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteCommand)) {
            return false;
        }

        DeleteCommand otherDeleteCommand = (DeleteCommand) other;
        return targetIndex.equals(otherDeleteCommand.targetIndex);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("targetIndex", targetIndex)
                .toString();
    }
}

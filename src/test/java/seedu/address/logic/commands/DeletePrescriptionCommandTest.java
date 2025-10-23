package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalPrescriptions.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.prescription.Prescription;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeletePrescriptionCommand}.
 */
public class DeletePrescriptionCommandTest {
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Prescription prescriptionToDelete = model.getFilteredPrescriptionList().get(INDEX_FIRST_PERSON.getZeroBased());
        DeletePrescriptionCommand deletePrescriptionCommand = new DeletePrescriptionCommand(INDEX_FIRST_PERSON);

        String expectedMessage = String.format(DeletePrescriptionCommand.MESSAGE_DELETE_PRESCRIPTION_SUCCESS,
            Messages.format(prescriptionToDelete));

        ModelManager expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.deletePrescription(prescriptionToDelete);

        assertCommandSuccess(deletePrescriptionCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPrescriptionList().size() + 1);
        DeletePrescriptionCommand deletePrescriptionCommand = new DeletePrescriptionCommand(outOfBoundIndex);
        assertCommandFailure(deletePrescriptionCommand, model,
            Messages.MESSAGE_INVALID_PRESCRIPTION_DISPLAYED_INDEX);
    }
}

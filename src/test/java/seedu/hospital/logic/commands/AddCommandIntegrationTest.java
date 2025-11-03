package seedu.hospital.logic.commands;

import static seedu.hospital.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.hospital.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hospital.testutil.TypicalPatients.getTypicalHospitalContacts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.hospital.logic.Messages;
import seedu.hospital.model.Model;
import seedu.hospital.model.ModelManager;
import seedu.hospital.model.UserPrefs;
import seedu.hospital.model.person.Patient;
import seedu.hospital.testutil.PatientBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHospitalContacts(), new UserPrefs());
    }

    @Test
    public void execute_newPatient_success() {
        Patient validPatient = new PatientBuilder().build();

        Model expectedModel = new ModelManager(model.getHospitalContacts(), new UserPrefs());
        expectedModel.addPatient(validPatient);

        assertCommandSuccess(new AddCommand(validPatient), model,
                String.format(AddCommand.MESSAGE_SUCCESS, Messages.format(validPatient)),
                expectedModel);
    }

    @Test
    public void execute_duplicatePatient_throwsCommandException() {
        Patient personInList = model.getHospitalContacts().getPatientList().get(0);
        assertCommandFailure(new AddCommand(personInList), model,
                AddCommand.MESSAGE_DUPLICATE_PERSON);
    }

}

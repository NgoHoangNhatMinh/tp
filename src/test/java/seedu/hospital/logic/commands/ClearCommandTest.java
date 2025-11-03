package seedu.hospital.logic.commands;

import static seedu.hospital.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hospital.testutil.TypicalPatients.getTypicalHospitalContacts;

import org.junit.jupiter.api.Test;

import seedu.hospital.model.HospitalContacts;
import seedu.hospital.model.Model;
import seedu.hospital.model.ModelManager;
import seedu.hospital.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyHospitalContacts_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyHospitalContacts_success() {
        Model model = new ModelManager(getTypicalHospitalContacts(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalHospitalContacts(), new UserPrefs());
        expectedModel.setHospitalContacts(new HospitalContacts());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}

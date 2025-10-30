package seedu.address.logic.commands;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalHospitalContactsXpm;

import org.junit.jupiter.api.Test;

import seedu.address.model.HospitalContactsXpm;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyHospitalContactsXpm_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyHospitalContactsXpm_success() {
        Model model = new ModelManager(getTypicalHospitalContactsXpm(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalHospitalContactsXpm(), new UserPrefs());
        expectedModel.setHospitalContactsXpm(new HospitalContactsXpm());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}

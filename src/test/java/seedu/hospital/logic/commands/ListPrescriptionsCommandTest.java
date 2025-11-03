package seedu.hospital.logic.commands;


import static seedu.hospital.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hospital.testutil.TypicalPatients.getTypicalHospitalContacts;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.hospital.model.Model;
import seedu.hospital.model.ModelManager;
import seedu.hospital.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListPrescriptionCommand.
 */
public class ListPrescriptionsCommandTest {
    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalHospitalContacts(), new UserPrefs());
        expectedModel = new ModelManager(model.getHospitalContacts(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListPrescriptionsCommand(), model, ListPrescriptionsCommand.MESSAGE_SUCCESS,
            expectedModel);
    }

}

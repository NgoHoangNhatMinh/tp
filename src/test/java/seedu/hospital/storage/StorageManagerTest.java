package seedu.hospital.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static seedu.hospital.testutil.TypicalPatients.getTypicalHospitalContacts;

import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.hospital.commons.core.GuiSettings;
//import seedu.hospital.model.HospitalContacts;
//import seedu.hospital.model.ReadOnlyHospitalContacts;
import seedu.hospital.model.UserPrefs;

public class StorageManagerTest {

    @TempDir
    public Path testFolder;

    private StorageManager storageManager;

    @BeforeEach
    public void setUp() {
        JsonHospitalContactsStorage hospitalContactsStorage = new JsonHospitalContactsStorage(getTempFilePath("ab"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(getTempFilePath("prefs"));
        storageManager = new StorageManager(hospitalContactsStorage, userPrefsStorage);
    }

    private Path getTempFilePath(String fileName) {
        return testFolder.resolve(fileName);
    }

    @Test
    public void prefsReadSave() throws Exception {
        /*
         * Note: This is an integration test that verifies the StorageManager is
         * properly wired to the
         * {@link JsonUserPrefsStorage} class.
         * More extensive testing of UserPref saving/reading is done in {@link
         * JsonUserPrefsStorageTest} class.
         */
        UserPrefs original = new UserPrefs();
        original.setGuiSettings(new GuiSettings(300, 600, 4, 6));
        storageManager.saveUserPrefs(original);
        UserPrefs retrieved = storageManager.readUserPrefs().get();
        assertEquals(original, retrieved);
    }

    // Tests are commented out temporarily to be modified later.
    // @Test
    // public void hospitalContactsReadSave() throws Exception {
    // /*
    // * Note: This is an integration test that verifies the StorageManager is
    // properly wired to the
    // * {@link JsonHospitalContactsStorage} class.
    // * More extensive testing of UserPref saving/reading is done in {@link
    // JsonHospitalContactsStorageTest} class.
    // */
    // HospitalContacts original = getTypicalHospitalContacts();
    // storageManager.saveHospitalContacts(original);
    // ReadOnlyHospitalContacts retrieved =
    // storageManager.readHospitalContactsBook().get();
    // assertEquals(original, new HospitalContacts(retrieved));
    // }

    @Test
    public void getHospitalContactsFilePath() {
        assertNotNull(storageManager.getHospitalContactsFilePath());
    }

}

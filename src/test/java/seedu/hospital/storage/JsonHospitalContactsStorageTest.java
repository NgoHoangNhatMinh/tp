package seedu.hospital.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.hospital.testutil.Assert.assertThrows;
//import static seedu.hospital.testutil.TypicalPatients.ALICE;
//import static seedu.hospital.testutil.TypicalPatients.HOON;
//import static seedu.hospital.testutil.TypicalPatients.IDA;
//import static seedu.hospital.testutil.TypicalPatients.getTypicalHospitalContacts;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

//import seedu.hospital.commons.exceptions.DataLoadingException;
import seedu.hospital.model.HospitalContacts;
import seedu.hospital.model.ReadOnlyHospitalContacts;

public class JsonHospitalContactsStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonHospitalContactsStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readHospitalContactsBook_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readHospitalContactsBook(null));
    }

    private java.util.Optional<ReadOnlyHospitalContacts> readHospitalContactsBook(String filePath) throws Exception {
        return new JsonHospitalContactsStorage(Paths.get(filePath))
                .readHospitalContactsBook(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readHospitalContactsBook("NonExistentFile.json").isPresent());
    }

    // @Test
    // public void read_notJsonFormat_exceptionThrown() {
    // assertThrows(DataLoadingException.class, () ->
    // readHospitalContactsBook("notJsonFormatHospitalContacts.json"));
    // }

    // Tests are commented out temporarily to be modified later.
    // @Test
    // public void
    // readHospitalContacts_invalidPatientHospitalContacts_throwDataLoadingException()
    // {
    // assertThrows(DataLoadingException.class, () ->
    // readHospitalContactsBook("invalidPatientHospitalContacts.json"));
    // }

    // @Test
    // public void
    // readHospitalContacts_invalidAndValidPatientHospitalContacts_throwDataLoadingException()
    // {
    // assertThrows(DataLoadingException.class, () ->
    // readHospitalContactsBook("invalidAndValidPatientHospitalContacts.json"));
    // }

    // @Test
    // public void readAndSaveHospitalContacts_allInOrder_success() throws Exception
    // {
    // Path filePath = testFolder.resolve("TempHospitalContacts.json");
    // HospitalContacts original = getTypicalHospitalContacts();
    // JsonHospitalContactsStorage jsonHospitalContactsStorage = new
    // JsonHospitalContactsStorage(filePath);
    //
    // // Save in new file and read back
    // jsonHospitalContactsStorage.saveHospitalContacts(original, filePath);
    // ReadOnlyHospitalContacts readBack =
    // jsonHospitalContactsStorage.readHospitalContactsBook(filePath).get();
    // assertEquals(original, new HospitalContacts(readBack));
    //
    // // Modify data, overwrite exiting file, and read back
    // original.addPatient(HOON);
    // original.removePatient(ALICE);
    // jsonHospitalContactsStorage.saveHospitalContacts(original, filePath);
    // readBack =
    // jsonHospitalContactsStorage.readHospitalContactsBook(filePath).get();
    // assertEquals(original, new HospitalContacts(readBack));
    //
    // // Save and read without specifying file path
    // original.addPatient(IDA);
    // jsonHospitalContactsStorage.saveHospitalContacts(original); // file path not
    // specified
    // readBack = jsonHospitalContactsStorage.readHospitalContactsBook().get(); //
    // file path not specified
    // assertEquals(original, new HospitalContacts(readBack));
    //
    // }

    @Test
    public void saveHospitalContacts_nullHospitalContacts_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHospitalContacts(null, "SomeFile.json"));
    }

    /**
     * Saves {@code hospitalContacts} at the specified {@code filePath}.
     */
    private void saveHospitalContacts(ReadOnlyHospitalContacts hospitalContacts, String filePath) {
        try {
            new JsonHospitalContactsStorage(Paths.get(filePath))
                    .saveHospitalContacts(hospitalContacts, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveHospitalContacts_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHospitalContacts(new HospitalContacts(), null));
    }
}

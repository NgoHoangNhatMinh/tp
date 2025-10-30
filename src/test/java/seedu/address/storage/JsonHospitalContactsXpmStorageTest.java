package seedu.address.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static seedu.address.testutil.Assert.assertThrows;
//import static seedu.address.testutil.TypicalPatients.ALICE;
//import static seedu.address.testutil.TypicalPatients.HOON;
//import static seedu.address.testutil.TypicalPatients.IDA;
//import static seedu.address.testutil.TypicalPatients.getTypicalHospitalContactsXpm;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.HospitalContactsXpm;
import seedu.address.model.ReadOnlyHospitalContactsXpm;

public class JsonHospitalContactsXpmStorageTest {
    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data", "JsonHospitalContactsXpmStorageTest");

    @TempDir
    public Path testFolder;

    @Test
    public void readHospitalContactsXpm_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> readHospitalContactsXpm(null));
    }

    private java.util.Optional<ReadOnlyHospitalContactsXpm> readHospitalContactsXpm(String filePath) throws Exception {
        return new JsonHospitalContactsXpmStorage(Paths.get(filePath))
                .readHospitalContactsXpm(addToTestDataPathIfNotNull(filePath));
    }

    private Path addToTestDataPathIfNotNull(String prefsFileInTestDataFolder) {
        return prefsFileInTestDataFolder != null
                ? TEST_DATA_FOLDER.resolve(prefsFileInTestDataFolder)
                : null;
    }

    @Test
    public void read_missingFile_emptyResult() throws Exception {
        assertFalse(readHospitalContactsXpm("NonExistentFile.json").isPresent());
    }

    @Test
    public void read_notJsonFormat_exceptionThrown() {
        assertThrows(DataLoadingException.class,
                () -> readHospitalContactsXpm("notJsonFormatHospitalContactsXpm.json"));
    }

    // Tests are commented out temporarily to be modified later.
    // @Test
    // public void
    // readHospitalContactsXpm_invalidPatientHospitalContactsXpm_throwDataLoadingException()
    // {
    // assertThrows(DataLoadingException.class, () ->
    // readHospitalContactsXpm("invalidPatientHospitalContactsXpm.json"));
    // }

    // @Test
    // public void
    // readHospitalContactsXpm_invalidAndValidPatientHospitalContactsXpm_throwDataLoadingException()
    // {
    // assertThrows(DataLoadingException.class, () ->
    // readHospitalContactsXpm("invalidAndValidPatientHospitalContactsXpm.json"));
    // }

    // @Test
    // public void readAndSaveHospitalContactsXpm_allInOrder_success() throws
    // Exception {
    // Path filePath = testFolder.resolve("TempHospitalContactsXpm.json");
    // HospitalContactsXpm original = getTypicalHospitalContactsXpm();
    // JsonHospitalContactsXpmStorage jsonHospitalContactsXpmStorage = new
    // JsonHospitalContactsXpmStorage(filePath);
    //
    // // Save in new file and read back
    // jsonHospitalContactsXpmStorage.saveHospitalContactsXpm(original, filePath);
    // ReadOnlyHospitalContactsXpm readBack =
    // jsonHospitalContactsXpmStorage.readHospitalContactsXpm(filePath).get();
    // assertEquals(original, new HospitalContactsXpm(readBack));
    //
    // // Modify data, overwrite exiting file, and read back
    // original.addPatient(HOON);
    // original.removePatient(ALICE);
    // jsonHospitalContactsXpmStorage.saveHospitalContactsXpm(original, filePath);
    // readBack =
    // jsonHospitalContactsXpmStorage.readHospitalContactsXpm(filePath).get();
    // assertEquals(original, new HospitalContactsXpm(readBack));
    //
    // // Save and read without specifying file path
    // original.addPatient(IDA);
    // jsonHospitalContactsXpmStorage.saveHospitalContactsXpm(original); // file
    // path not specified
    // readBack = jsonHospitalContactsXpmStorage.readHospitalContactsXpm().get(); //
    // file path not
    // specified
    // assertEquals(original, new HospitalContactsXpm(readBack));
    //
    // }

    @Test
    public void saveHospitalContactsXpm_nullHospitalContactsXpm_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHospitalContactsXpm(null, "SomeFile.json"));
    }

    /**
     * Saves {@code hospitalContactsXpm} at the specified {@code filePath}.
     */
    private void saveHospitalContactsXpm(ReadOnlyHospitalContactsXpm hospitalContactsXpm, String filePath) {
        try {
            new JsonHospitalContactsXpmStorage(Paths.get(filePath))
                    .saveHospitalContactsXpm(hospitalContactsXpm, addToTestDataPathIfNotNull(filePath));
        } catch (IOException ioe) {
            throw new AssertionError("There should not be an error writing to the file.", ioe);
        }
    }

    @Test
    public void saveHospitalContactsXpm_nullFilePath_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> saveHospitalContactsXpm(new HospitalContactsXpm(), null));
    }
}

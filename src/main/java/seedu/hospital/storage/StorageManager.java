package seedu.hospital.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.hospital.commons.core.LogsCenter;
import seedu.hospital.commons.exceptions.DataLoadingException;
import seedu.hospital.model.ReadOnlyHospitalContacts;
import seedu.hospital.model.ReadOnlyUserPrefs;
import seedu.hospital.model.UserPrefs;

/**
 * Manages storage of HospitalContacts data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private HospitalContactsStorage hospitalContactsStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given
     * {@code HospitalContactsStorage} and {@code UserPrefStorage}.
     */
    public StorageManager(HospitalContactsStorage hospitalContactsStorage, UserPrefsStorage userPrefsStorage) {
        this.hospitalContactsStorage = hospitalContactsStorage;
        this.userPrefsStorage = userPrefsStorage;
    }

    // ================ UserPrefs methods ==============================

    @Override
    public Path getUserPrefsFilePath() {
        return userPrefsStorage.getUserPrefsFilePath();
    }

    @Override
    public Optional<UserPrefs> readUserPrefs() throws DataLoadingException {
        return userPrefsStorage.readUserPrefs();
    }

    @Override
    public void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException {
        userPrefsStorage.saveUserPrefs(userPrefs);
    }

    // ================ HospitalContacts methods ==============================

    @Override
    public Path getHospitalContactsFilePath() {
        return hospitalContactsStorage.getHospitalContactsFilePath();
    }

    @Override
    public Optional<ReadOnlyHospitalContacts> readHospitalContactsBook() throws DataLoadingException {
        return readHospitalContactsBook(hospitalContactsStorage.getHospitalContactsFilePath());
    }

    @Override
    public Optional<ReadOnlyHospitalContacts> readHospitalContactsBook(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return hospitalContactsStorage.readHospitalContactsBook(filePath);
    }

    @Override
    public void saveHospitalContacts(ReadOnlyHospitalContacts hospitalContacts) throws IOException {
        saveHospitalContacts(hospitalContacts, hospitalContactsStorage.getHospitalContactsFilePath());
    }

    @Override
    public void saveHospitalContacts(ReadOnlyHospitalContacts hospitalContacts, Path filePath) throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        hospitalContactsStorage.saveHospitalContacts(hospitalContacts, filePath);
    }

    // ================ Prescription methods ==============================

}

package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyHospitalContactsXpm;
import seedu.address.model.ReadOnlyUserPrefs;
import seedu.address.model.UserPrefs;

/**
 * Manages storage of HospitalContactsXpm data in local storage.
 */
public class StorageManager implements Storage {

    private static final Logger logger = LogsCenter.getLogger(StorageManager.class);
    private HospitalContactsXpmStorage hospitalContactsXpmStorage;
    private UserPrefsStorage userPrefsStorage;

    /**
     * Creates a {@code StorageManager} with the given
     * {@code HospitalContactsXpmStorage}
     * and {@code UserPrefStorage}.
     */
    public StorageManager(HospitalContactsXpmStorage hospitalContactsXpmStorage, UserPrefsStorage userPrefsStorage) {
        this.hospitalContactsXpmStorage = hospitalContactsXpmStorage;
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

    // ================ HospitalContactsXpm methods ==============================

    @Override
    public Path getHospitalContactsXpmFilePath() {
        return hospitalContactsXpmStorage.getHospitalContactsXpmFilePath();
    }

    @Override
    public Optional<ReadOnlyHospitalContactsXpm> readHospitalContactsXpm() throws DataLoadingException {
        return readHospitalContactsXpm(hospitalContactsXpmStorage.getHospitalContactsXpmFilePath());
    }

    @Override
    public Optional<ReadOnlyHospitalContactsXpm> readHospitalContactsXpm(Path filePath) throws DataLoadingException {
        logger.fine("Attempting to read data from file: " + filePath);
        return hospitalContactsXpmStorage.readHospitalContactsXpm(filePath);
    }

    @Override
    public void saveHospitalContactsXpm(ReadOnlyHospitalContactsXpm hospitalContactsXpm) throws IOException {
        saveHospitalContactsXpm(hospitalContactsXpm, hospitalContactsXpmStorage.getHospitalContactsXpmFilePath());
    }

    @Override
    public void saveHospitalContactsXpm(ReadOnlyHospitalContactsXpm hospitalContactsXpm, Path filePath)
            throws IOException {
        logger.fine("Attempting to write to data file: " + filePath);
        hospitalContactsXpmStorage.saveHospitalContactsXpm(hospitalContactsXpm, filePath);
    }

    // ================ Prescription methods ==============================

}

package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.model.ReadOnlyHospitalContactsXpm;

/**
 * Represents a storage for {@link seedu.address.model.HospitalContactsXpm}.
 */
public interface HospitalContactsXpmStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getHospitalContactsXpmFilePath();

    /**
     * Returns HospitalContactsXpm data as a {@link ReadOnlyHospitalContactsXpm}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyHospitalContactsXpm> readHospitalContactsXpm() throws DataLoadingException;

    /**
     * @see #getHospitalContactsXpmFilePath()
     */
    Optional<ReadOnlyHospitalContactsXpm> readHospitalContactsXpm(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyHospitalContactsXpm} to the storage.
     * 
     * @param hospitalContactsXpm cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveHospitalContactsXpm(ReadOnlyHospitalContactsXpm hospitalContactsXpm) throws IOException;

    /**
     * @see #saveHospitalContactsXpm(ReadOnlyHospitalContactsXpm)
     */
    void saveHospitalContactsXpm(ReadOnlyHospitalContactsXpm hospitalContactsXpm, Path filePath) throws IOException;

}

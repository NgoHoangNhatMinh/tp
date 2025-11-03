package seedu.hospital.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.hospital.commons.exceptions.DataLoadingException;
import seedu.hospital.model.HospitalContacts;
import seedu.hospital.model.ReadOnlyHospitalContacts;

/**
 * Represents a storage for {@link HospitalContacts}.
 */
public interface HospitalContactsStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getHospitalContactsFilePath();

    /**
     * Returns HospitalContacts data as a {@link ReadOnlyHospitalContacts}.
     * Returns {@code Optional.empty()} if storage file is not found.
     *
     * @throws DataLoadingException if loading the data from storage failed.
     */
    Optional<ReadOnlyHospitalContacts> readHospitalContactsBook() throws DataLoadingException;

    /**
     * @see #getHospitalContactsFilePath()
     */
    Optional<ReadOnlyHospitalContacts> readHospitalContactsBook(Path filePath) throws DataLoadingException;

    /**
     * Saves the given {@link ReadOnlyHospitalContacts} to the storage.
     *
     * @param hospitalContacts cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveHospitalContacts(ReadOnlyHospitalContacts hospitalContacts) throws IOException;

    /**
     * @see #saveHospitalContacts(ReadOnlyHospitalContacts)
     */
    void saveHospitalContacts(ReadOnlyHospitalContacts hospitalContacts, Path filePath) throws IOException;

}

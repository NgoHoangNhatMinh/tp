package seedu.hospital.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.hospital.commons.exceptions.DataLoadingException;
import seedu.hospital.model.ReadOnlyHospitalContacts;
import seedu.hospital.model.ReadOnlyUserPrefs;
import seedu.hospital.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends HospitalContactsStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataLoadingException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getHospitalContactsFilePath();

    @Override
    Optional<ReadOnlyHospitalContacts> readHospitalContactsBook() throws DataLoadingException;

    @Override
    void saveHospitalContacts(ReadOnlyHospitalContacts hospitalContacts) throws IOException;

}

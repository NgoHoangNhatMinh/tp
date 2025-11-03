package seedu.hospital.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.hospital.commons.core.LogsCenter;
import seedu.hospital.commons.exceptions.DataLoadingException;
import seedu.hospital.commons.exceptions.IllegalValueException;
import seedu.hospital.commons.util.FileUtil;
import seedu.hospital.commons.util.JsonUtil;
import seedu.hospital.model.ReadOnlyHospitalContacts;

/**
 * A class to access HospitalContacts data stored as a json file on the hard
 * disk.
 */
public class JsonHospitalContactsStorage implements HospitalContactsStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonHospitalContactsStorage.class);

    private Path filePath;

    public JsonHospitalContactsStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getHospitalContactsFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyHospitalContacts> readHospitalContactsBook() throws DataLoadingException {
        return readHospitalContactsBook(filePath);
    }

    /**
     * Similar to {@link #readHospitalContactsBook()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyHospitalContacts> readHospitalContactsBook(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableHospitalContacts> jsonHospitalContacts = JsonUtil.readJsonFile(
                filePath, JsonSerializableHospitalContacts.class);
        if (!jsonHospitalContacts.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonHospitalContacts.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveHospitalContacts(ReadOnlyHospitalContacts hospitalContacts) throws IOException {
        saveHospitalContacts(hospitalContacts, filePath);
    }

    /**
     * Similar to {@link #saveHospitalContacts(ReadOnlyHospitalContacts)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveHospitalContacts(ReadOnlyHospitalContacts hospitalContacts, Path filePath) throws IOException {
        requireNonNull(hospitalContacts);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableHospitalContacts(hospitalContacts), filePath);
    }

}

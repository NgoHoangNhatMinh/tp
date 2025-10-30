package seedu.address.storage;

import static java.util.Objects.requireNonNull;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

import seedu.address.commons.core.LogsCenter;
import seedu.address.commons.exceptions.DataLoadingException;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.commons.util.FileUtil;
import seedu.address.commons.util.JsonUtil;
import seedu.address.model.ReadOnlyHospitalContactsXpm;

/**
 * A class to access HospitalContactsXpm data stored as a json file on the hard
 * disk.
 */
public class JsonHospitalContactsXpmStorage implements HospitalContactsXpmStorage {

    private static final Logger logger = LogsCenter.getLogger(JsonHospitalContactsXpmStorage.class);

    private Path filePath;

    public JsonHospitalContactsXpmStorage(Path filePath) {
        this.filePath = filePath;
    }

    public Path getHospitalContactsXpmFilePath() {
        return filePath;
    }

    @Override
    public Optional<ReadOnlyHospitalContactsXpm> readHospitalContactsXpm() throws DataLoadingException {
        return readHospitalContactsXpm(filePath);
    }

    /**
     * Similar to {@link #readHospitalContactsXpm()}.
     *
     * @param filePath location of the data. Cannot be null.
     * @throws DataLoadingException if loading the data from storage failed.
     */
    public Optional<ReadOnlyHospitalContactsXpm> readHospitalContactsXpm(Path filePath) throws DataLoadingException {
        requireNonNull(filePath);

        Optional<JsonSerializableHospitalContactsXpm> jsonHospitalContactsXpm = JsonUtil.readJsonFile(
                filePath, JsonSerializableHospitalContactsXpm.class);
        if (!jsonHospitalContactsXpm.isPresent()) {
            return Optional.empty();
        }

        try {
            return Optional.of(jsonHospitalContactsXpm.get().toModelType());
        } catch (IllegalValueException ive) {
            logger.info("Illegal values found in " + filePath + ": " + ive.getMessage());
            throw new DataLoadingException(ive);
        }
    }

    @Override
    public void saveHospitalContactsXpm(ReadOnlyHospitalContactsXpm hospitalContactsXpm) throws IOException {
        saveHospitalContactsXpm(hospitalContactsXpm, filePath);
    }

    /**
     * Similar to {@link #saveHospitalContactsXpm(ReadOnlyHospitalContactsXpm)}.
     *
     * @param filePath location of the data. Cannot be null.
     */
    public void saveHospitalContactsXpm(ReadOnlyHospitalContactsXpm hospitalContactsXpm, Path filePath)
            throws IOException {
        requireNonNull(hospitalContactsXpm);
        requireNonNull(filePath);

        FileUtil.createIfMissing(filePath);
        JsonUtil.saveJsonFile(new JsonSerializableHospitalContactsXpm(hospitalContactsXpm), filePath);
    }

}

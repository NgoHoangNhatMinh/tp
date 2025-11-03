package seedu.hospital.model;

import java.nio.file.Path;

import seedu.hospital.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getHospitalContactsFilePath();

}

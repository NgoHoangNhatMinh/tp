package seedu.hospital.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.hospital.commons.core.GuiSettings;
import seedu.hospital.logic.commands.CommandResult;
import seedu.hospital.logic.commands.exceptions.CommandException;
import seedu.hospital.logic.parser.exceptions.ParseException;
import seedu.hospital.model.ReadOnlyHospitalContacts;
import seedu.hospital.model.appointment.Appointment;
import seedu.hospital.model.person.Patient;
import seedu.hospital.model.prescription.Prescription;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the HospitalContacts.
     *
     * @see seedu.hospital.model.Model#getHospitalContacts()
     */
    ReadOnlyHospitalContacts getHospitalContacts();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Patient> getFilteredPatientList();

    /** Returns an unmodifiable view of the filtered list of persons */
    ObservableList<Prescription> getFilteredPrescriptionList();

    ObservableList<Appointment> getFilteredAppointmentList();

    /**
     * Returns the user prefs' hospital book file path.
     */
    Path getHospitalContactsFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}

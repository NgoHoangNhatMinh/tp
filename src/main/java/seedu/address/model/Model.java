package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Patient;
import seedu.address.model.prescription.Prescription;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Patient> PREDICATE_SHOW_ALL_PERSONS = unused -> true;

    /** {@code Predicate} that always evaluate to true (for Prescriptions) */
    Predicate<Prescription> PREDICATE_SHOW_ALL_PRESCRIPTIONS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' address book file path.
     */
    Path getHospitalContactsXpmFilePath();

    /**
     * Sets the user prefs' address book file path.
     */
    void setHospitalContactsXpmFilePath(Path hospitalContactsXpmFilePath);

    /**
     * Replaces address book data with the data in {@code hospitalContactsXpm}.
     */
    void setHospitalContactsXpm(ReadOnlyHospitalContactsXpm hospitalContactsXpm);

    /** Returns the HospitalContactsXpm */
    ReadOnlyHospitalContactsXpm getHospitalContactsXpm();

    /**
     * Returns true if a person with the same identity as {@code person} exists in
     * the address book.
     */
    boolean hasPatient(Patient person);

    /**
     * Deletes the given person.
     * The person must exist in the address book.
     */
    void deletePatient(Patient target);

    /**
     * Adds the given person.
     * {@code person} must not already exist in the address book.
     */
    void addPatient(Patient person);

    /**
     * Replaces the given person {@code target} with {@code editedPatient}.
     * {@code target} must exist in the address book.
     * The person identity of {@code editedPatient} must not be the same as another
     * existing person in the address book.
     */
    void setPatient(Patient target, Patient editedPatient);

    /** Returns an unmodifiable view of the filtered person list */
    ObservableList<Patient> getFilteredPatientList();

    /**
     * Updates the filter of the filtered person list to filter by the given
     * {@code predicate}.
     * 
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPatientList(Predicate<Patient> predicate);

    boolean hasAppointment(Appointment appointment);

    void addAppointment(Appointment appointment);

    void deleteAppointment(Appointment appointment);

    boolean hasPrescription(Prescription prescription);

    void addPrescription(Prescription prescription);

    void deletePrescription(Prescription prescription);

    /** Returns an unmodifiable view of the filtered prescriptions list */
    ObservableList<Prescription> getFilteredPrescriptionList();

    /**
     * Updates the filter of the filtered prescription list to filter by the given
     * {@code predicate}.
     * 
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredPrescriptionList(Predicate<Prescription> predicate);

    ObservableList<Appointment> getFilteredAppointmentList();

    void updateFilteredAppointmentList(Predicate<Appointment> predicate);

    void setAppointmentListComparator(Comparator<Appointment> comparator);

}

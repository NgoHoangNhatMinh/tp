package seedu.hospital.model;

import static java.util.Objects.requireNonNull;
import static seedu.hospital.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.hospital.commons.core.GuiSettings;
import seedu.hospital.commons.core.LogsCenter;
import seedu.hospital.model.appointment.Appointment;
import seedu.hospital.model.person.Patient;
import seedu.hospital.model.prescription.Prescription;

/**
 * Represents the in-memory model of the hospital book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final HospitalContacts hospitalContacts;
    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPatients;
    private final FilteredList<Prescription> filteredPrescriptions;
    private final FilteredList<Appointment> filteredAppointments;
    private final SortedList<Appointment> sortedAppointments;

    /**
     * Initializes a ModelManager with the given hospitalContacts and userPrefs.
     */
    public ModelManager(ReadOnlyHospitalContacts hospitalContacts, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(hospitalContacts, userPrefs);

        logger.fine("Initializing with hospital book: " + hospitalContacts + " and user prefs " + userPrefs);

        this.hospitalContacts = new HospitalContacts(hospitalContacts);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPatients = new FilteredList<>(this.hospitalContacts.getPatientList());
        filteredPrescriptions = new FilteredList<>(this.hospitalContacts.getPrescriptionList());
        filteredAppointments = new FilteredList<>(this.hospitalContacts.getAppointmentList());
        sortedAppointments = new SortedList<>(filteredAppointments);
    }

    public ModelManager() {
        this(new HospitalContacts(), new UserPrefs());
    }

    // =========== UserPrefs
    // ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getHospitalContactsFilePath() {
        return userPrefs.getHospitalContactsFilePath();
    }

    @Override
    public void setHospitalContactsFilePath(Path hospitalContactsFilePath) {
        requireNonNull(hospitalContactsFilePath);
        userPrefs.setHospitalContactsFilePath(hospitalContactsFilePath);
    }

    // =========== HospitalContacts
    // ================================================================================

    @Override
    public void setHospitalContacts(ReadOnlyHospitalContacts hospitalContacts) {
        this.hospitalContacts.resetData(hospitalContacts);
    }

    @Override
    public ReadOnlyHospitalContacts getHospitalContacts() {
        return hospitalContacts;
    }

    @Override
    public boolean hasPatient(Patient person) {
        requireNonNull(person);
        return hospitalContacts.hasPatient(person);
    }

    @Override
    public void deletePatient(Patient target) {
        hospitalContacts.removePatient(target);
    }

    @Override
    public void addPatient(Patient person) {
        hospitalContacts.addPatient(person);
        updateFilteredPatientList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        hospitalContacts.setPatient(target, editedPatient);
    }

    // =========== Filtered Patient List Accessors
    // =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Patient} backed by the
     * internal list of
     * {@code versionedHospitalContacts}
     */
    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return filteredPatients;
    }

    @Override
    public void updateFilteredPatientList(Predicate<Patient> predicate) {
        requireNonNull(predicate);
        filteredPatients.setPredicate(predicate);
    }

    // =========== Filtered Prescription List Accessors
    // =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Prescription} backed by
     * the internal list of
     * {@code versionedHospitalContacts}
     */
    @Override
    public ObservableList<Prescription> getFilteredPrescriptionList() {
        return filteredPrescriptions;
    }

    @Override
    public void updateFilteredPrescriptionList(Predicate<Prescription> predicate) {
        requireNonNull(predicate);
        filteredPrescriptions.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModelManager)) {
            return false;
        }

        ModelManager otherModelManager = (ModelManager) other;
        return hospitalContacts.equals(otherModelManager.hospitalContacts)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPatients.equals(otherModelManager.filteredPatients)
                && filteredPrescriptions.equals(otherModelManager.filteredPrescriptions);
    }

    // =========== Appointments
    // =============================================================

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return hospitalContacts.hasAppointment(appointment);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        requireNonNull(appointment);
        hospitalContacts.addAppointment(appointment);
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        requireNonNull(appointment);
        hospitalContacts.removeAppointment(appointment);
    }

    // =========== Filtered Appointments
    // =====================================================

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return sortedAppointments; // expose sorted view
    }

    @Override
    public void updateFilteredAppointmentList(Predicate<Appointment> predicate) {
        requireNonNull(predicate);
        filteredAppointments.setPredicate(predicate);
    }

    @Override
    public void setAppointmentListComparator(Comparator<Appointment> comparator) {
        sortedAppointments.setComparator(comparator);
    }

    // =========== Prescriptions
    // =============================================================

    @Override
    public boolean hasPrescription(Prescription prescription) {
        requireNonNull(prescription);
        return hospitalContacts.hasPrescription(prescription);
    }

    @Override
    public void addPrescription(Prescription prescription) {
        requireNonNull(prescription);
        hospitalContacts.addPrescription(prescription);
    }

    @Override
    public void deletePrescription(Prescription prescription) {
        requireNonNull(prescription);
        hospitalContacts.removePrescription(prescription);
    }

}

package seedu.address.model;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.commons.core.LogsCenter;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Patient;
import seedu.address.model.prescription.Prescription;

/**
 * Represents the in-memory model of the address book data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final HospitalContactsXpm hospitalContactsXpm;
    private final UserPrefs userPrefs;
    private final FilteredList<Patient> filteredPatients;
    private final FilteredList<Prescription> filteredPrescriptions;
    private final FilteredList<Appointment> filteredAppointments;
    private final SortedList<Appointment> sortedAppointments;

    /**
     * Initializes a ModelManager with the given hospitalContactsXpm and userPrefs.
     */
    public ModelManager(ReadOnlyHospitalContactsXpm hospitalContactsXpm, ReadOnlyUserPrefs userPrefs) {
        requireAllNonNull(hospitalContactsXpm, userPrefs);

        logger.fine("Initializing with address book: " + hospitalContactsXpm + " and user prefs " + userPrefs);

        this.hospitalContactsXpm = new HospitalContactsXpm(hospitalContactsXpm);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredPatients = new FilteredList<>(this.hospitalContactsXpm.getPatientList());
        filteredPrescriptions = new FilteredList<>(this.hospitalContactsXpm.getPrescriptionList());
        filteredAppointments = new FilteredList<>(this.hospitalContactsXpm.getAppointmentList());
        sortedAppointments = new SortedList<>(filteredAppointments);
    }

    public ModelManager() {
        this(new HospitalContactsXpm(), new UserPrefs());
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
    public Path getHospitalContactsXpmFilePath() {
        return userPrefs.getHospitalContactsXpmFilePath();
    }

    @Override
    public void setHospitalContactsXpmFilePath(Path hospitalContactsXpmFilePath) {
        requireNonNull(hospitalContactsXpmFilePath);
        userPrefs.setHospitalContactsXpmFilePath(hospitalContactsXpmFilePath);
    }

    // =========== HospitalContactsXpm
    // ================================================================================

    @Override
    public void setHospitalContactsXpm(ReadOnlyHospitalContactsXpm hospitalContactsXpm) {
        this.hospitalContactsXpm.resetData(hospitalContactsXpm);
    }

    @Override
    public ReadOnlyHospitalContactsXpm getHospitalContactsXpm() {
        return hospitalContactsXpm;
    }

    @Override
    public boolean hasPatient(Patient person) {
        requireNonNull(person);
        return hospitalContactsXpm.hasPatient(person);
    }

    @Override
    public void deletePatient(Patient target) {
        hospitalContactsXpm.removePatient(target);
    }

    @Override
    public void addPatient(Patient person) {
        hospitalContactsXpm.addPatient(person);
        updateFilteredPatientList(PREDICATE_SHOW_ALL_PERSONS);
    }

    @Override
    public void setPatient(Patient target, Patient editedPatient) {
        requireAllNonNull(target, editedPatient);

        hospitalContactsXpm.setPatient(target, editedPatient);
    }

    // =========== Filtered Patient List Accessors
    // =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Patient} backed by the
     * internal list of
     * {@code versionedHospitalContactsXpm}
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
     * {@code versionedHospitalContactsXpm}
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
        return hospitalContactsXpm.equals(otherModelManager.hospitalContactsXpm)
                && userPrefs.equals(otherModelManager.userPrefs)
                && filteredPatients.equals(otherModelManager.filteredPatients)
                && filteredPrescriptions.equals(otherModelManager.filteredPrescriptions);
    }

    // =========== Appointments
    // =============================================================

    @Override
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return hospitalContactsXpm.hasAppointment(appointment);
    }

    @Override
    public void addAppointment(Appointment appointment) {
        requireNonNull(appointment);
        hospitalContactsXpm.addAppointment(appointment);
    }

    @Override
    public void deleteAppointment(Appointment appointment) {
        requireNonNull(appointment);
        hospitalContactsXpm.removeAppointment(appointment);
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
        return hospitalContactsXpm.hasPrescription(prescription);
    }

    @Override
    public void addPrescription(Prescription prescription) {
        requireNonNull(prescription);
        hospitalContactsXpm.addPrescription(prescription);
    }

    @Override
    public void deletePrescription(Prescription prescription) {
        requireNonNull(prescription);
        hospitalContactsXpm.removePrescription(prescription);
    }

}

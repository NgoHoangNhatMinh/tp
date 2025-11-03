package seedu.hospital.model;

import javafx.collections.ObservableList;
import seedu.hospital.model.appointment.Appointment;
import seedu.hospital.model.person.Patient;
import seedu.hospital.model.prescription.Prescription;

/**
 * Unmodifiable view of an hospital book.
 */
public interface ReadOnlyHospitalContacts {
    ObservableList<Patient> getPatientList();

    /**
     * Returns an unmodifiable view of the appointment list.
     */
    ObservableList<Appointment> getAppointmentList();

    /**
     * Returns an unmodifiable view of the prescription list.
     */
    ObservableList<Prescription> getPrescriptionList();
}

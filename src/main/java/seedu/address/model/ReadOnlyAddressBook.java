package seedu.address.model;

import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Patient;

/**
 * Unmodifiable view of an address book.
 */
public interface ReadOnlyAddressBook {
    ObservableList<Patient> getPatientList();

    /**
     * Returns an unmodifiable view of the appointment list.
     */
    ObservableList<Appointment> getAppointmentList();
}

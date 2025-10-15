package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.UniqueAppointmentList;
import seedu.address.model.person.Patient;
import seedu.address.model.person.UniquePatientList;

/**
 * Wraps all data at the address-book level.
 * Duplicates are not allowed (by .isSamePatient comparison for persons, and equality for appointments).
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePatientList persons;
    private final UniqueAppointmentList appointments;

    {
        persons = new UniquePatientList();
        appointments = new UniqueAppointmentList();
    }

    /**
     * Constructs an empty {@code AddressBook}.
     */
    public AddressBook() {}

    /**
     * Creates an {@code AddressBook} using the data in {@code toBeCopied}.
     */
    public AddressBook(ReadOnlyAddressBook toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    public void setPatients(List<Patient> persons) {
        this.persons.setPatients(persons);
    }

    /**
     * Replaces the contents of the appointment list with {@code appointments}.
     * {@code appointments} must not contain duplicate appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
    }

    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setPatients(newData.getPatientList());
        setAppointments(newData.getAppointmentList());
    }

    //// person-level operations
    public boolean hasPatient(Patient person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    public void addPatient(Patient p) {
        persons.add(p);
    }

    public void setPatient(Patient target, Patient editedPatient) {
        requireNonNull(editedPatient);
        persons.setPatient(target, editedPatient);
    }

    public void removePatient(Patient key) {
        persons.remove(key);
    }

    //// appointment-level operations
    public boolean hasAppointment(Appointment appointment) {
        requireNonNull(appointment);
        return appointments.contains(appointment);
    }

    public void addAppointment(Appointment appointment) {
        appointments.add(appointment);
    }

    public void removeAppointment(Appointment appointment) {
        appointments.remove(appointment);
    }

    @Override
    public ObservableList<Patient> getPatientList() {
        return persons.asUnmodifiableObservableList();
    }

    @Override
    public ObservableList<Appointment> getAppointmentList() {
        return appointments.asUnmodifiableObservableList();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("persons", persons)
                .add("appointments", appointments)
                .toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AddressBook)) {
            return false;
        }
        AddressBook otherAddressBook = (AddressBook) other;
        return persons.equals(otherAddressBook.persons)
                && appointments.equals(otherAddressBook.appointments);
    }

    @Override
    public int hashCode() {
        return persons.hashCode() ^ appointments.hashCode();
    }
}

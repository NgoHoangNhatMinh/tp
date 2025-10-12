package seedu.address.model;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.appointment.UniqueAppointmentList;
import seedu.address.model.person.Person;
import seedu.address.model.person.UniquePersonList;

/**
 * Wraps all data at the address-book level.
 * Duplicates are not allowed (by .isSamePerson comparison for persons, and equality for appointments).
 */
public class AddressBook implements ReadOnlyAddressBook {

    private final UniquePersonList persons;
    private final UniqueAppointmentList appointments;

    {
        persons = new UniquePersonList();
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

    /**
     * Replaces the current list of persons with the provided list.
     *
     * @param persons the new list of {@link Person} objects to set
     */
    public void setPersons(List<Person> persons) {
        this.persons.setPersons(persons);
    }

    /**
     * Replaces the contents of the appointment list with {@code appointments}.
     * {@code appointments} must not contain duplicate appointments.
     */
    public void setAppointments(List<Appointment> appointments) {
        this.appointments.setAppointments(appointments);
    }

    /**
     * Replaces the current address book data with the data from the given {@code ReadOnlyAddressBook}.
     *
     * @param newData the new {@link ReadOnlyAddressBook} whose data will replace the current data
     */
    public void resetData(ReadOnlyAddressBook newData) {
        requireNonNull(newData);
        setPersons(newData.getPersonList());
        setAppointments(newData.getAppointmentList());
    }

    /**
     * Checks if the specified person exists in the current list of persons.
     *
     * @param person the {@link Person} object to check for
     * @return {@code true} if the list contains the specified person, {@code false} otherwise
     */
    public boolean hasPerson(Person person) {
        requireNonNull(person);
        return persons.contains(person);
    }

    public void addPerson(Person p) {
        persons.add(p);
    }

    public void setPerson(Person target, Person editedPerson) {
        requireNonNull(editedPerson);
        persons.setPerson(target, editedPerson);
    }

    public void removePerson(Person key) {
        persons.remove(key);
    }

    /**
     * Checks if the specified appointment exists in the current list of appointments.
     *
     * @param appointment the {@link Appointment} object to check for
     * @return {@code true} if the list contains the specified appointment, {@code false} otherwise
     */
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
    public ObservableList<Person> getPersonList() {
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

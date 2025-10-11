package seedu.address.model.appointment;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.exceptions.DuplicateAppointmentException;

/**
 * A list of appointments that enforces uniqueness.
 * Appointment identity is defined by equality.
 */
public class UniqueAppointmentList implements Iterable<Appointment> {

    private final ObservableList<Appointment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Appointment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    public boolean contains(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    public void add(Appointment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAppointmentException();
        }
        internalList.add(toAdd);
    }

    public void setAppointments(List<Appointment> appointments) {
        requireNonNull(appointments);
        if (!appointmentsAreUnique(appointments)) {
            throw new DuplicateAppointmentException();
        }
        internalList.setAll(appointments);
    }

    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    public void remove(Appointment toRemove) {
        requireNonNull(toRemove);
        internalList.remove(toRemove);
    }

    @Override
    public Iterator<Appointment> iterator() {
        return internalList.iterator();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof UniqueAppointmentList
                && internalList.equals(((UniqueAppointmentList) other).internalList));
    }

    @Override
    public int hashCode() {
        return internalList.hashCode();
    }

    private boolean appointmentsAreUnique(List<Appointment> appointments) {
        for (int i = 0; i < appointments.size() - 1; i++) {
            for (int j = i + 1; j < appointments.size(); j++) {
                if (appointments.get(i).equals(appointments.get(j))) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return internalUnmodifiableList.toString();
    }
}

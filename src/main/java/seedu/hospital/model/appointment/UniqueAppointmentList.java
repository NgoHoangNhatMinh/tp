package seedu.hospital.model.appointment;

import static java.util.Objects.requireNonNull;

import java.util.Iterator;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.hospital.model.appointment.exceptions.DuplicateAppointmentException;

/**
 * A list of appointments that enforces uniqueness.
 * Appointment identity is defined by equality.
 */
public class UniqueAppointmentList implements Iterable<Appointment> {

    private final ObservableList<Appointment> internalList = FXCollections.observableArrayList();
    private final ObservableList<Appointment> internalUnmodifiableList =
            FXCollections.unmodifiableObservableList(internalList);

    /**
     * Checks if the specified appointment exists in the internal list.
     *
     * @param toCheck the {@link Appointment} to check for
     * @return {@code true} if the internal list contains the specified appointment, {@code false} otherwise
     */
    public boolean contains(Appointment toCheck) {
        requireNonNull(toCheck);
        return internalList.stream().anyMatch(toCheck::equals);
    }

    /**
     * Adds the specified appointment to the internal list.
     *
     * @param toAdd the {@link Appointment} to add
     * @throws DuplicateAppointmentException if the appointment already exists
     */
    public void add(Appointment toAdd) {
        requireNonNull(toAdd);
        if (contains(toAdd)) {
            throw new DuplicateAppointmentException();
        }
        internalList.add(toAdd);
    }

    /**
     * Replaces the current list of appointments with the provided list.
     * The provided list must not contain duplicates.
     *
     * @param appointments list of appointments to replace the existing contents
     * @throws DuplicateAppointmentException if the provided list contains duplicates
     */
    public void setAppointments(List<Appointment> appointments) {
        requireNonNull(appointments);
        if (!appointmentsAreUnique(appointments)) {
            throw new DuplicateAppointmentException();
        }
        internalList.setAll(appointments);
    }

    /**
     * Returns an unmodifiable view of the internal appointment list.
     */
    public ObservableList<Appointment> asUnmodifiableObservableList() {
        return internalUnmodifiableList;
    }

    /**
     * Removes the specified appointment from the internal list.
     * If the appointment does not exist, no action is taken.
     *
     * @param toRemove the {@link Appointment} to remove
     */
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

    /**
     * Returns true if all appointments in the given list are unique.
     */
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

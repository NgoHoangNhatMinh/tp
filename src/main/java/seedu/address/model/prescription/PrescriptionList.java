package seedu.address.model.prescription;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Wraps all data at the prescription list level.
 * Duplicates are not allowed (by identity).
 */
public class PrescriptionList implements ReadOnlyPrescriptionList {

    private final ObservableList<Prescription> prescriptions = FXCollections.observableArrayList();
    private final ObservableList<Prescription> unmodifiablePrescriptions =
        FXCollections.unmodifiableObservableList(prescriptions);

    public PrescriptionList() {}

    /**
     * Creates a PrescriptionList using the Prescriptions in the {@code toBeCopied}.
     */
    public PrescriptionList(ReadOnlyPrescriptionList toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    //// list overwrite operations

    /**
     * Replaces the contents of the Prescription List with {@code prescriptions}.
     * {@code prescriptions} must not contain duplicate prescriptions.
     */

    public void setPrescriptions(List<Prescription> prescriptions) {
        requireNonNull(prescriptions);
        this.prescriptions.setAll(prescriptions);
    }

    /**
     * Resets the existing data of this {@code PrescriptionList} with {@code newData}.
     */

    public void resetData(ReadOnlyPrescriptionList newData) {
        requireNonNull(newData);
        setPrescriptions(newData.getPrescriptionList());
    }

    //// prescription-level operations

    //// person-level operations

    /**
     * Returns true if a prescription with the same identity as {@code prescription} exists in the Prescription List.
     */
    public boolean hasPrescription(Prescription prescription) {
        requireNonNull(prescription);
        return prescriptions.contains(prescription);
    }

    /**
     * Adds a prescription to the Prescription List.
     * The person must not already exist in Prescription List.
     */
    public void addPrescription(Prescription prescription) {
        requireNonNull(prescription);
        if (hasPrescription(prescription)) {
            throw new IllegalArgumentException("Duplicate prescription not allowed");
        }
        prescriptions.add(prescription);
    }

    /**
     * Removes a prescription from the Prescription List.
     * The {@code prescription} must already exist in Prescription List.
     */
    public void removePrescription(Prescription prescription) {
        prescriptions.remove(prescription);
    }

    @Override
    public ObservableList<Prescription> getPrescriptionList() {
        return unmodifiablePrescriptions;
    }

    @Override
    public String toString() {
        return prescriptions.size() + " prescriptions";
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof PrescriptionList
            && prescriptions.equals(((PrescriptionList) other).prescriptions));
    }

    @Override
    public int hashCode() {
        return prescriptions.hashCode();
    }
}

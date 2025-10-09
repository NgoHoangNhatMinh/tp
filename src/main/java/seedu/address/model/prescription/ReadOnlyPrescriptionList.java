package seedu.address.model.prescription;

import javafx.collections.ObservableList;

/**
 * Unmodifiable view of a prescription list.
 */
public interface ReadOnlyPrescriptionList {
    /**
     * Returns an unmodifiable view of the prescriptions list.
     * This list will not contain any duplicate prescriptions.
     */
    ObservableList<Prescription> getPrescriptionList();
}

package seedu.address.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPatients.ALICE;
import static seedu.address.testutil.TypicalPatients.getTypicalHospitalContactsXpm;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Patient;
import seedu.address.model.person.exceptions.DuplicatePatientException;
import seedu.address.model.prescription.Prescription;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.PrescriptionBuilder;

public class HospitalContactsXpmTest {

    private final HospitalContactsXpm hospitalContactsXpm = new HospitalContactsXpm();
    private Prescription prescription;

    @BeforeEach
    public void setUp() {
        // Sample prescription
        prescription = new PrescriptionBuilder()
                .withPatientId("P-10293")
                .withMedicationName("Paracetamol")
                .withDosage(500f)
                .withFrequency(3)
                .withDuration(7)
                .withNote("Take after meals")
                .build();
    }

    @Test
    public void constructor() {
        assertEquals(Collections.emptyList(), hospitalContactsXpm.getPatientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hospitalContactsXpm.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyHospitalContactsXpm_replacesData() {
        HospitalContactsXpm newData = getTypicalHospitalContactsXpm();
        hospitalContactsXpm.resetData(newData);
        assertEquals(newData, hospitalContactsXpm);
    }

    @Test
    public void resetData_withDuplicatePatients_throwsDuplicatePatientException() {
        // Two persons with the same identity fields
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .build();
        List<Patient> newPatients = Arrays.asList(ALICE, editedAlice);
        HospitalContactsXpmStub newData = new HospitalContactsXpmStub(newPatients);

        assertThrows(DuplicatePatientException.class, () -> hospitalContactsXpm.resetData(newData));
    }

    @Test
    public void hasPatient_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hospitalContactsXpm.hasPatient(null));
    }

    @Test
    public void hasPatient_personNotInHospitalContactsXpm_returnsFalse() {
        assertFalse(hospitalContactsXpm.hasPatient(ALICE));
    }

    @Test
    public void hasPatient_personInHospitalContactsXpm_returnsTrue() {
        hospitalContactsXpm.addPatient(ALICE);
        assertTrue(hospitalContactsXpm.hasPatient(ALICE));
    }

    @Test
    public void hasPatient_personWithSameIdentityFieldsInHospitalContactsXpm_returnsTrue() {
        hospitalContactsXpm.addPatient(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .build();
        assertTrue(hospitalContactsXpm.hasPatient(editedAlice));
    }

    @Test
    public void getPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> hospitalContactsXpm.getPatientList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = HospitalContactsXpm.class.getCanonicalName()
                + "{patients=" + hospitalContactsXpm.getPatientList()
                + ", appointments=" + hospitalContactsXpm.getAppointmentList()
                + ", prescriptions=" + hospitalContactsXpm.getPrescriptionList()
                + "}";
        assertEquals(expected, hospitalContactsXpm.toString());
    }

    /**
     * A stub ReadOnlyHospitalContactsXpm whose persons list can violate interface
     * constraints.
     */
    private static class HospitalContactsXpmStub implements ReadOnlyHospitalContactsXpm {
        private final ObservableList<Patient> persons = FXCollections.observableArrayList();
        private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        private final ObservableList<Prescription> prescriptions = FXCollections.observableArrayList();

        HospitalContactsXpmStub(Collection<Patient> persons) {
            this.persons.setAll(persons);
        }

        @Override
        public ObservableList<Patient> getPatientList() {
            return persons;
        }

        @Override
        public ObservableList<Appointment> getAppointmentList() {
            return appointments;
        }

        @Override
        public ObservableList<Prescription> getPrescriptionList() {
            return prescriptions;
        }
    }

    /// //////// Prescription tests ///////////

    @Test
    public void hasPrescription_prescriptionNotInHospitalContactsXpm_returnsFalse() {
        assertFalse(hospitalContactsXpm.hasPrescription(prescription));
    }

    @Test
    public void hasPrescription_prescriptionInHospitalContactsXpm_returnsTrue() {
        hospitalContactsXpm.addPrescription(prescription);
        assertTrue(hospitalContactsXpm.hasPrescription(prescription));
    }

    @Test
    public void addPrescription_prescriptionIsAdded_success() {
        hospitalContactsXpm.addPrescription(prescription);
        assertTrue(hospitalContactsXpm.hasPrescription(prescription));
    }

    @Test
    public void removePrescription_prescriptionIsRemoved_success() {
        hospitalContactsXpm.addPrescription(prescription);
        hospitalContactsXpm.removePrescription(prescription);
        assertFalse(hospitalContactsXpm.hasPrescription(prescription));
    }

    @Test
    public void setPrescription_nullEditedPrescription_throwsNullPointerException() {
        hospitalContactsXpm.addPrescription(prescription);
        assertThrows(NullPointerException.class, () -> hospitalContactsXpm.setPrescription(prescription, null));
    }

    @Test
    public void setPrescription_prescriptionNotInHospitalContactsXpm_throwsPrescriptionNotFoundException() {
        Prescription anotherPrescription = new PrescriptionBuilder().withMedicationName("Ibuprofen").build();
        assertThrows(seedu.address.model.prescription.exceptions.PrescriptionNotFoundException.class,
                () -> hospitalContactsXpm.setPrescription(anotherPrescription, prescription));
    }

    @Test
    public void setPrescription_success() {
        hospitalContactsXpm.addPrescription(prescription);

        Prescription editedPrescription = new PrescriptionBuilder(prescription)
                .withDosage(750f)
                .build();
        hospitalContactsXpm.setPrescription(prescription, editedPrescription);

        assertTrue(hospitalContactsXpm.hasPrescription(editedPrescription));
        assertFalse(hospitalContactsXpm.hasPrescription(prescription));
    }

}

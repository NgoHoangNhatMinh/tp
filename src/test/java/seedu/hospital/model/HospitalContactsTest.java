package seedu.hospital.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hospital.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
//import static seedu.hospital.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.hospital.testutil.Assert.assertThrows;
import static seedu.hospital.testutil.TypicalPatients.ALICE;
import static seedu.hospital.testutil.TypicalPatients.getTypicalHospitalContacts;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import seedu.hospital.model.appointment.Appointment;
import seedu.hospital.model.person.Patient;
import seedu.hospital.model.person.exceptions.DuplicatePatientException;
import seedu.hospital.model.prescription.Prescription;
import seedu.hospital.testutil.PatientBuilder;
import seedu.hospital.testutil.PrescriptionBuilder;

public class HospitalContactsTest {

    private final HospitalContacts hospitalContacts = new HospitalContacts();
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
        assertEquals(Collections.emptyList(), hospitalContacts.getPatientList());
    }

    @Test
    public void resetData_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hospitalContacts.resetData(null));
    }

    @Test
    public void resetData_withValidReadOnlyHospitalContacts_replacesData() {
        HospitalContacts newData = getTypicalHospitalContacts();
        hospitalContacts.resetData(newData);
        assertEquals(newData, hospitalContacts);
    }

    @Test
    public void resetData_withDuplicatePatients_throwsDuplicatePatientException() {
        // Two persons with the same identity fields
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .build();
        List<Patient> newPatients = Arrays.asList(ALICE, editedAlice);
        HospitalContactsStub newData = new HospitalContactsStub(newPatients);

        assertThrows(DuplicatePatientException.class, () -> hospitalContacts.resetData(newData));
    }

    @Test
    public void hasPatient_nullPatient_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> hospitalContacts.hasPatient(null));
    }

    @Test
    public void hasPatient_personNotInHospitalContacts_returnsFalse() {
        assertFalse(hospitalContacts.hasPatient(ALICE));
    }

    @Test
    public void hasPatient_personInHospitalContacts_returnsTrue() {
        hospitalContacts.addPatient(ALICE);
        assertTrue(hospitalContacts.hasPatient(ALICE));
    }

    @Test
    public void hasPatient_personWithSameIdentityFieldsInHospitalContacts_returnsTrue() {
        hospitalContacts.addPatient(ALICE);
        Patient editedAlice = new PatientBuilder(ALICE).withAddress(VALID_ADDRESS_BOB)
                .build();
        assertTrue(hospitalContacts.hasPatient(editedAlice));
    }

    @Test
    public void getPatientList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> hospitalContacts.getPatientList().remove(0));
    }

    @Test
    public void toStringMethod() {
        String expected = HospitalContacts.class.getCanonicalName()
                + "{patients=" + hospitalContacts.getPatientList()
                + ", appointments=" + hospitalContacts.getAppointmentList()
                + ", prescriptions=" + hospitalContacts.getPrescriptionList()
                + "}";
        assertEquals(expected, hospitalContacts.toString());
    }

    /**
     * A stub ReadOnlyHospitalContacts whose persons list can violate interface constraints.
     */
    private static class HospitalContactsStub implements ReadOnlyHospitalContacts {
        private final ObservableList<Patient> persons = FXCollections.observableArrayList();
        private final ObservableList<Appointment> appointments = FXCollections.observableArrayList();
        private final ObservableList<Prescription> prescriptions = FXCollections.observableArrayList();

        HospitalContactsStub(Collection<Patient> persons) {
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
    public void hasPrescription_prescriptionNotInHospitalContacts_returnsFalse() {
        assertFalse(hospitalContacts.hasPrescription(prescription));
    }

    @Test
    public void hasPrescription_prescriptionInHospitalContacts_returnsTrue() {
        hospitalContacts.addPrescription(prescription);
        assertTrue(hospitalContacts.hasPrescription(prescription));
    }

    @Test
    public void addPrescription_prescriptionIsAdded_success() {
        hospitalContacts.addPrescription(prescription);
        assertTrue(hospitalContacts.hasPrescription(prescription));
    }

    @Test
    public void removePrescription_prescriptionIsRemoved_success() {
        hospitalContacts.addPrescription(prescription);
        hospitalContacts.removePrescription(prescription);
        assertFalse(hospitalContacts.hasPrescription(prescription));
    }

    @Test
    public void setPrescription_nullEditedPrescription_throwsNullPointerException() {
        hospitalContacts.addPrescription(prescription);
        assertThrows(NullPointerException.class, () -> hospitalContacts.setPrescription(prescription, null));
    }

    @Test
    public void setPrescription_prescriptionNotInHospitalContacts_throwsPrescriptionNotFoundException() {
        Prescription anotherPrescription = new PrescriptionBuilder().withMedicationName("Ibuprofen").build();
        assertThrows(seedu.hospital.model.prescription.exceptions.PrescriptionNotFoundException.class, () ->
                hospitalContacts.setPrescription(anotherPrescription, prescription));
    }

    @Test
    public void setPrescription_success() {
        hospitalContacts.addPrescription(prescription);

        Prescription editedPrescription = new PrescriptionBuilder(prescription)
                .withDosage(750f)
                .build();
        hospitalContacts.setPrescription(prescription, editedPrescription);

        assertTrue(hospitalContacts.hasPrescription(editedPrescription));
        assertFalse(hospitalContacts.hasPrescription(prescription));
    }


}

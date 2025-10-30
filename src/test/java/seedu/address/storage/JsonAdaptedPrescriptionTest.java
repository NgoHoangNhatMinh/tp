package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.prescription.Prescription;
import seedu.address.testutil.PrescriptionBuilder;

public class JsonAdaptedPrescriptionTest {

    private static final String VALID_PATIENT_NAME = "Alex Yeoh";
    private static final String VALID_MEDICATION_NAME = "Paracetamol";
    private static final Float VALID_DOSAGE = 500.0f;
    private static final Integer VALID_FREQUENCY = 3;
    private static final LocalDateTime VALID_START_DATE = LocalDateTime.of(2024, 10, 10, 8, 0);
    private static final Integer VALID_DURATION = 7;
    private static final String VALID_NOTE = "Take after meals";

    @Test
    public void toModelType_validPrescriptionDetails_returnsPrescription() throws Exception {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_PATIENT_NAME, VALID_MEDICATION_NAME, VALID_DOSAGE, VALID_FREQUENCY,
            VALID_START_DATE, VALID_DURATION, VALID_NOTE);

        Prescription expectedPrescription = new Prescription(
            VALID_PATIENT_NAME, VALID_MEDICATION_NAME, VALID_DOSAGE, VALID_FREQUENCY,
            VALID_START_DATE, VALID_DURATION, VALID_NOTE);

        assertEquals(expectedPrescription, prescription.toModelType());
    }

    @Test
    public void toModelType_nullPatientName_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            null, VALID_MEDICATION_NAME, VALID_DOSAGE, VALID_FREQUENCY,
            VALID_START_DATE, VALID_DURATION, VALID_NOTE);

        assertThrows(IllegalValueException.class, prescription::toModelType);
    }

    @Test
    public void toModelType_nullMedicationName_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_PATIENT_NAME, null, VALID_DOSAGE, VALID_FREQUENCY,
            VALID_START_DATE, VALID_DURATION, VALID_NOTE);

        assertThrows(IllegalValueException.class, prescription::toModelType);
    }

    @Test
    public void toModelType_nullDosage_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_PATIENT_NAME, VALID_MEDICATION_NAME, null, VALID_FREQUENCY,
            VALID_START_DATE, VALID_DURATION, VALID_NOTE);

        assertThrows(IllegalValueException.class, prescription::toModelType);
    }

    @Test
    public void toModelType_nullFrequency_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_PATIENT_NAME, VALID_MEDICATION_NAME, VALID_DOSAGE, null,
            VALID_START_DATE, VALID_DURATION, VALID_NOTE);

        assertThrows(IllegalValueException.class, prescription::toModelType);
    }

    @Test
    public void toModelType_nullDuration_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_PATIENT_NAME, VALID_MEDICATION_NAME, VALID_DOSAGE, VALID_FREQUENCY,
            VALID_START_DATE, null, VALID_NOTE);

        assertThrows(IllegalValueException.class, prescription::toModelType);
    }

    @Test
    public void toModelType_invalidDosage_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_PATIENT_NAME, VALID_MEDICATION_NAME, -1.0f, VALID_FREQUENCY,
            VALID_START_DATE, VALID_DURATION, VALID_NOTE);

        assertThrows(IllegalValueException.class, prescription::toModelType);
    }

    @Test
    public void toModelType_invalidFrequency_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_PATIENT_NAME, VALID_MEDICATION_NAME, VALID_DOSAGE, 0,
            VALID_START_DATE, VALID_DURATION, VALID_NOTE);

        assertThrows(IllegalValueException.class, prescription::toModelType);
    }

    @Test
    public void toModelType_invalidDuration_throwsIllegalValueException() {
        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
            VALID_PATIENT_NAME, VALID_MEDICATION_NAME, VALID_DOSAGE, VALID_FREQUENCY,
            VALID_START_DATE, -5, VALID_NOTE);

        assertThrows(IllegalValueException.class, prescription::toModelType);
    }

    //    @Test
    //    public void toModelType_nullStartDate_returnsPrescription() throws Exception {
    //        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
    //            VALID_PATIENT_NAME, VALID_MEDICATION_NAME, VALID_DOSAGE, VALID_FREQUENCY,
    //            null, VALID_DURATION, VALID_NOTE);
    //
    //        Prescription expectedPrescription = new Prescription(
    //            VALID_PATIENT_NAME, VALID_MEDICATION_NAME, VALID_DOSAGE, VALID_FREQUENCY,
    //            null, VALID_DURATION, VALID_NOTE);
    //
    //        assertEquals(expectedPrescription, prescription.toModelType());
    //    }
    //
    //    @Test
    //    public void toModelType_nullNote_returnsPrescription() throws Exception {
    //        JsonAdaptedPrescription prescription = new JsonAdaptedPrescription(
    //            VALID_PATIENT_NAME, VALID_MEDICATION_NAME, VALID_DOSAGE, VALID_FREQUENCY,
    //            VALID_START_DATE, VALID_DURATION, null);
    //
    //        Prescription expectedPrescription = new Prescription(
    //            VALID_PATIENT_NAME, VALID_MEDICATION_NAME, VALID_DOSAGE, VALID_FREQUENCY,
    //            VALID_START_DATE, VALID_DURATION, null);
    //
    //        assertEquals(expectedPrescription, prescription.toModelType());
    //    }

    @Test
    public void constructor_fromPrescriptionSource_preservesAllData() throws IllegalValueException {
        Prescription originalPrescription = new PrescriptionBuilder().build();
        JsonAdaptedPrescription adaptedPrescription = new JsonAdaptedPrescription(originalPrescription);

        assertEquals(originalPrescription, adaptedPrescription.toModelType());
    }
}

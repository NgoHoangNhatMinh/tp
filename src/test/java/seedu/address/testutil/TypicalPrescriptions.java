package seedu.address.testutil;

import java.time.LocalDateTime;

import seedu.address.model.HospitalContactsXpm;
import seedu.address.model.prescription.Prescription;

/**
 * A utility class containing a list of {@code Prescription} objects to be used
 * in tests.
 */
public class TypicalPrescriptions {

        public static final Prescription PRESCRIPTION_A = new PrescriptionBuilder()
                        .withPatientId("P-10293")
                        .withMedicationName("Paracetamol")
                        .withDosage(500f)
                        .withFrequency(3)
                        .withStartDate(LocalDateTime.of(2025, 1, 5, 9, 0))
                        .withDuration(7)
                        .withNote("Take after meals")
                        .build();

        public static final Prescription PRESCRIPTION_B = new PrescriptionBuilder()
                        .withPatientId("P-10293")
                        .withMedicationName("Amoxicillin")
                        .withDosage(250f)
                        .withFrequency(2)
                        .withStartDate(LocalDateTime.of(2025, 2, 10, 8, 0))
                        .withDuration(5)
                        .withNote("Complete the full course")
                        .build();

        public static final Prescription PRESCRIPTION_C = new PrescriptionBuilder()
                        .withPatientId("P-10293")
                        .withMedicationName("Loratadine")
                        .withDosage(10f)
                        .withFrequency(1)
                        .withStartDate(LocalDateTime.of(2025, 3, 20, 10, 0))
                        .withDuration(10)
                        .withNote("Take once daily for allergy relief")
                        .build();

        public static final Prescription PRESCRIPTION_D = new PrescriptionBuilder()
                        .withPatientId("P-20485")
                        .withMedicationName("Ibuprofen")
                        .withDosage(200f)
                        .withFrequency(2)
                        .withStartDate(LocalDateTime.of(2025, 1, 15, 8, 0))
                        .withDuration(5)
                        .withNote("Take only when in pain")
                        .build();

        private TypicalPrescriptions() {
        } // prevents instantiation

        /**
         * Returns an {@code HospitalContactsXpm} with all the typical prescriptions
         * added.
         */
        public static HospitalContactsXpm getTypicalHospitalContactsXpm() {
                HospitalContactsXpm ab = new HospitalContactsXpm();
                ab.addPrescription(PRESCRIPTION_A);
                ab.addPrescription(PRESCRIPTION_B);
                ab.addPrescription(PRESCRIPTION_C);
                ab.addPrescription(PRESCRIPTION_D);
                return ab;
        }
}

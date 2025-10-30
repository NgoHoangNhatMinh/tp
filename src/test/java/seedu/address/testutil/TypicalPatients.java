//package seedu.address.testutil;
//
//import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
//import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import seedu.address.model.HospitalContactsXpm;
//import seedu.address.model.person.Patient;
//
/// **
// * A utility class containing a list of {@code Patient} objects to be used in tests.
// */
//public class TypicalPatients {
//
//    public static final Patient ALICE = new PatientBuilder().withName("Alice Pauline")
//            .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
//            .withPhone("94351253")
//            .build();
//    public static final Patient BENSON = new PatientBuilder().withName("Benson Meier")
//            .withAddress("311, Clementi Ave 2, #02-25")
//            .withEmail("johnd@example.com").withPhone("98765432")
//            .build();
//    public static final Patient CARL = new PatientBuilder().withName("Carl Kurz").withPhone("95352563")
//            .withEmail("heinz@example.com").withAddress("wall street").build();
//    public static final Patient DANIEL = new PatientBuilder().withName("Daniel Meier").withPhone("87652533")
//            .withEmail("cornelia@example.com").withAddress("10th street").build();
//    public static final Patient ELLE = new PatientBuilder().withName("Elle Meyer").withPhone("9482224")
//            .withEmail("werner@example.com").withAddress("michegan ave").build();
//    public static final Patient FIONA = new PatientBuilder().withName("Fiona Kunz").withPhone("9482427")
//            .withEmail("lydia@example.com").withAddress("little tokyo").build();
//    public static final Patient GEORGE = new PatientBuilder().withName("George Best").withPhone("9482442")
//            .withEmail("anna@example.com").withAddress("4th street").build();
//
//    // Manually added
//    public static final Patient HOON = new PatientBuilder().withName("Hoon Meier").withPhone("8482424")
//            .withEmail("stefan@example.com").withAddress("little india").build();
//    public static final Patient IDA = new PatientBuilder().withName("Ida Mueller").withPhone("8482131")
//            .withEmail("hans@example.com").withAddress("chicago ave").build();
//
//    // Manually added - Patient's details found in {@code CommandTestUtil}
//    public static final Patient AMY = new PatientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
//            .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY).build();
//    public static final Patient BOB = new PatientBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
//            .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
//            .build();
//
//    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER
//
//    private TypicalPatients() {} // prevents instantiation
//
//    /**
//     * Returns an {@code HospitalContactsXpm} with all the typical persons.
//     */
//    public static HospitalContactsXpm getTypicalHospitalContactsXpm() {
//        HospitalContactsXpm ab = new HospitalContactsXpm();
//        for (Patient person : getTypicalPatients()) {
//            ab.addPatient(person);
//        }
//        return ab;
//    }
//
//    public static List<Patient> getTypicalPatients() {
//        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
//    }
//}

package seedu.address.testutil;

import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.HospitalContactsXpm;
import seedu.address.model.person.Patient;

/**
 * A utility class containing a list of {@code Patient} objects to be used in
 * tests.
 */
public class TypicalPatients {

        public static final Patient ALICE = new PatientBuilder().withName("Alice Pauline")
                        .withAddress("123, Jurong West Ave 6, #08-111").withEmail("alice@example.com")
                        .withPhone("94351253").withBirthday("1985-03-15").withGender("Female")
                        .withEmergency("John Pauline: 91234567").withId("S8512345A").withLang("English")
                        .build();

        public static final Patient BENSON = new PatientBuilder().withName("Benson Meier")
                        .withAddress("311, Clementi Ave 2, #02-25").withEmail("johnd@example.com")
                        .withPhone("98765432").withBirthday("1992-07-22").withGender("Male")
                        .withEmergency("Sarah Meier: 87654321").withId("S9223456B").withLang("Chinese")
                        .build();

        public static final Patient CARL = new PatientBuilder().withName("Carl Kurz")
                        .withPhone("95352563").withEmail("heinz@example.com").withAddress("wall street")
                        .withBirthday("1988-11-30").withGender("Male")
                        .withEmergency("Anna Kurz: 81234567").withId("S8834567C").withLang("German")
                        .build();

        public static final Patient DANIEL = new PatientBuilder().withName("Daniel Meier")
                        .withPhone("87652533").withEmail("cornelia@example.com").withAddress("10th street")
                        .withBirthday("1991-04-18").withGender("Male")
                        .withEmergency("Lisa Meier: 94567890").withId("S9145678D").withLang("English")
                        .build();

        public static final Patient ELLE = new PatientBuilder().withName("Elle Meyer")
                        .withPhone("9482224").withEmail("werner@example.com").withAddress("michegan ave")
                        .withBirthday("1993-09-05").withGender("Female")
                        .withEmergency("Tom Meyer: 82345678").withId("S9356789E").withLang("French")
                        .build();

        public static final Patient FIONA = new PatientBuilder().withName("Fiona Kunz")
                        .withPhone("9482427").withEmail("lydia@example.com").withAddress("little tokyo")
                        .withBirthday("1987-12-12").withGender("Female")
                        .withEmergency("Mark Kunz: 83456789").withId("S8767890F").withLang("Japanese")
                        .build();

        public static final Patient GEORGE = new PatientBuilder().withName("George Best")
                        .withPhone("9482442").withEmail("anna@example.com").withAddress("4th street")
                        .withBirthday("1994-02-28").withGender("Male")
                        .withEmergency("Mary Best: 84567890").withId("S9478901G").withLang("English")
                        .build();

        // Manually added
        public static final Patient HOON = new PatientBuilder().withName("Hoon Meier")
                        .withPhone("8482424").withEmail("stefan@example.com").withAddress("little india")
                        .withBirthday("1990-06-15").withGender("Male")
                        .withEmergency("Priya Meier: 85678901").withId("S9089012H").withLang("Hindi")
                        .build();

        public static final Patient IDA = new PatientBuilder().withName("Ida Mueller")
                        .withPhone("8482131").withEmail("hans@example.com").withAddress("chicago ave")
                        .withBirthday("1989-08-20").withGender("Female")
                        .withEmergency("Hans Mueller: 86789012").withId("S8990123I").withLang("German")
                        .build();

        // Manually added - Patient's details found in {@code CommandTestUtil}
        public static final Patient AMY = new PatientBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
                        .withEmail(VALID_EMAIL_AMY).withAddress(VALID_ADDRESS_AMY)
                        .withBirthday("1990-01-01").withGender("Female")
                        .withEmergency("Emergency Contact: 91111111").withId("S9012345J").withLang("English")
                        .build();

        public static final Patient BOB = new PatientBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
                        .withEmail(VALID_EMAIL_BOB).withAddress(VALID_ADDRESS_BOB)
                        .withBirthday("1992-03-15").withGender("Male")
                        .withEmergency("Bob Senior: 92222222").withId("S9234567K").withLang("Chinese")
                        .build();

        public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

        private TypicalPatients() {
        } // prevents instantiation

        /**
         * Returns an {@code HospitalContactsXpm} with all the typical patients.
         */
        public static HospitalContactsXpm getTypicalHospitalContactsXpm() {
                HospitalContactsXpm ab = new HospitalContactsXpm();
                for (Patient patient : getTypicalPatients()) {
                        ab.addPatient(patient);
                }
                return ab;
        }

        /**
         * Returns a list of typical patients.
         */
        public static List<Patient> getTypicalPatients() {
                return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
        }
}

package seedu.hospital.storage;

//import static org.junit.jupiter.api.Assertions.assertEquals;

import static seedu.hospital.storage.JsonAdaptedPatient.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.hospital.testutil.Assert.assertThrows;
import static seedu.hospital.testutil.TypicalPatients.BENSON;

import org.junit.jupiter.api.Test;

import seedu.hospital.commons.exceptions.IllegalValueException;
import seedu.hospital.model.person.Address;
import seedu.hospital.model.person.Email;
import seedu.hospital.model.person.Name;
import seedu.hospital.model.person.Phone;

public class JsonAdaptedPatientTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_BIRTHDAY = "invalid-date";


    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final String VALID_BIRTHDAY = BENSON.getBirthday().toString();
    private static final String VALID_GENDER = BENSON.getGender();
    private static final String VALID_EMERGENCY = BENSON.getEmergency();
    private static final String VALID_ID = BENSON.getId();
    private static final String VALID_LANG = BENSON.getLang();

    //    Tests are commented out temporarily to be modified later.

    //    @Test
    //    public void toModelType_validPatientDetails_returnsPatient() throws Exception {
    //        JsonAdaptedPatient person = new JsonAdaptedPatient(BENSON);
    //        assertEquals(BENSON, person.toModelType());
    //    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(
                INVALID_NAME, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_GENDER, VALID_EMERGENCY, VALID_ID, VALID_LANG
        );
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(
                null, VALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_GENDER, VALID_EMERGENCY, VALID_ID, VALID_LANG
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(
                VALID_NAME, INVALID_PHONE, VALID_EMAIL, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_GENDER, VALID_EMERGENCY, VALID_ID, VALID_LANG
        );
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(
                VALID_NAME, null, VALID_EMAIL, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_GENDER, VALID_EMERGENCY, VALID_ID, VALID_LANG
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, INVALID_EMAIL, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_GENDER, VALID_EMERGENCY, VALID_ID, VALID_LANG
        );
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, null, VALID_ADDRESS,
                VALID_BIRTHDAY, VALID_GENDER, VALID_EMERGENCY, VALID_ID, VALID_LANG
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, INVALID_ADDRESS,
                VALID_BIRTHDAY, VALID_GENDER, VALID_EMERGENCY, VALID_ID, VALID_LANG
        );
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedPatient person = new JsonAdaptedPatient(
                VALID_NAME, VALID_PHONE, VALID_EMAIL, null,
                VALID_BIRTHDAY, VALID_GENDER, VALID_EMERGENCY, VALID_ID, VALID_LANG
        );
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

}

package seedu.hospital.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static seedu.hospital.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

//import seedu.hospital.commons.exceptions.IllegalValueException;
//import seedu.hospital.commons.util.JsonUtil;
import seedu.hospital.model.HospitalContacts;
import seedu.hospital.model.appointment.Appointment;
//import seedu.hospital.testutil.TypicalPatients;

public class JsonSerializableHospitalContactsTest {

    private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
            "JsonSerializableHospitalContactsTest");
    private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER.resolve("typicalPatientsHospitalContacts.json");
    private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER.resolve("invalidPatientHospitalContacts.json");
    private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER.resolve("duplicatePatientHospitalContacts.json");

    // Tests are commented out temporarily to be modified later.
    // @Test
    // public void toModelType_typicalPatientsFile_success() throws Exception {
    // JsonSerializableHospitalContacts dataFromFile =
    // JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
    // JsonSerializableHospitalContacts.class).get();
    // HospitalContacts hospitalContactsFromFile = dataFromFile.toModelType();
    // HospitalContacts typicalPatientsHospitalContacts =
    // TypicalPatients.getTypicalHospitalContacts();
    // assertEquals(hospitalContactsFromFile, typicalPatientsHospitalContacts);
    // }

    // @Test
    // public void toModelType_invalidPatientFile_throwsIllegalValueException()
    // throws Exception {
    // JsonSerializableHospitalContacts dataFromFile =
    // JsonUtil.readJsonFile(INVALID_PERSON_FILE,
    // JsonSerializableHospitalContacts.class).get();
    // assertThrows(IllegalValueException.class, dataFromFile::toModelType);
    // }

    // @Test
    // public void toModelType_duplicatePatients_throwsIllegalValueException()
    // throws Exception {
    // JsonSerializableHospitalContacts dataFromFile =
    // JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
    // JsonSerializableHospitalContacts.class).get();
    // assertThrows(IllegalValueException.class,
    // JsonSerializableHospitalContacts.MESSAGE_DUPLICATE_PERSON,
    // dataFromFile::toModelType);
    // }

    @Test
    public void toModelType_validAppointments_success() throws Exception {
        Appointment appt = new Appointment("P001",
                LocalDateTime.of(2025, 10, 10, 10, 0),
                "Dr Tan", "Review");

        HospitalContacts ab = new HospitalContacts();
        ab.addAppointment(appt);

        JsonSerializableHospitalContacts jsonAb = new JsonSerializableHospitalContacts(ab);
        HospitalContacts converted = jsonAb.toModelType();

        assertEquals(ab.getAppointmentList(), converted.getAppointmentList());
    }
}

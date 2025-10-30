package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
//import static seedu.address.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

//import seedu.address.commons.exceptions.IllegalValueException;
//import seedu.address.commons.util.JsonUtil;
import seedu.address.model.HospitalContactsXpm;
import seedu.address.model.appointment.Appointment;
//import seedu.address.testutil.TypicalPatients;

public class JsonSerializableHospitalContactsXpmTest {

        private static final Path TEST_DATA_FOLDER = Paths.get("src", "test", "data",
                        "JsonSerializableHospitalContactsXpmTest");
        private static final Path TYPICAL_PERSONS_FILE = TEST_DATA_FOLDER
                        .resolve("typicalPatientsHospitalContactsXpm.json");
        private static final Path INVALID_PERSON_FILE = TEST_DATA_FOLDER
                        .resolve("invalidPatientHospitalContactsXpm.json");
        private static final Path DUPLICATE_PERSON_FILE = TEST_DATA_FOLDER
                        .resolve("duplicatePatientHospitalContactsXpm.json");

        // Tests are commented out temporarily to be modified later.
        // @Test
        // public void toModelType_typicalPatientsFile_success() throws Exception {
        // JsonSerializableHospitalContactsXpm dataFromFile =
        // JsonUtil.readJsonFile(TYPICAL_PERSONS_FILE,
        // JsonSerializableHospitalContactsXpm.class).get();
        // HospitalContactsXpm hospitalContactsXpmFromFile = dataFromFile.toModelType();
        // HospitalContactsXpm typicalPatientsHospitalContactsXpm =
        // TypicalPatients.getTypicalHospitalContactsXpm();
        // assertEquals(hospitalContactsXpmFromFile,
        // typicalPatientsHospitalContactsXpm);
        // }

        // @Test
        // public void toModelType_invalidPatientFile_throwsIllegalValueException()
        // throws Exception {
        // JsonSerializableHospitalContactsXpm dataFromFile =
        // JsonUtil.readJsonFile(INVALID_PERSON_FILE,
        // JsonSerializableHospitalContactsXpm.class).get();
        // assertThrows(IllegalValueException.class, dataFromFile::toModelType);
        // }

        // @Test
        // public void toModelType_duplicatePatients_throwsIllegalValueException()
        // throws Exception {
        // JsonSerializableHospitalContactsXpm dataFromFile =
        // JsonUtil.readJsonFile(DUPLICATE_PERSON_FILE,
        // JsonSerializableHospitalContactsXpm.class).get();
        // assertThrows(IllegalValueException.class,
        // JsonSerializableHospitalContactsXpm.MESSAGE_DUPLICATE_PERSON,
        // dataFromFile::toModelType);
        // }

        @Test
        public void toModelType_validAppointments_success() throws Exception {
                Appointment appt = new Appointment("P001",
                                LocalDateTime.of(2025, 10, 10, 10, 0),
                                "Dr Tan", "Review");

                HospitalContactsXpm ab = new HospitalContactsXpm();
                ab.addAppointment(appt);

                JsonSerializableHospitalContactsXpm jsonAb = new JsonSerializableHospitalContactsXpm(ab);
                HospitalContactsXpm converted = jsonAb.toModelType();

                assertEquals(ab.getAppointmentList(), converted.getAppointmentList());
        }
}

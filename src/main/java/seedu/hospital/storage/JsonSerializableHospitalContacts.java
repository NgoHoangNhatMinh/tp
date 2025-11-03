package seedu.hospital.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.hospital.commons.exceptions.IllegalValueException;
import seedu.hospital.model.HospitalContacts;
import seedu.hospital.model.ReadOnlyHospitalContacts;
import seedu.hospital.model.appointment.Appointment;
import seedu.hospital.model.person.Patient;
import seedu.hospital.model.prescription.Prescription;

/**
 * An Immutable HospitalContacts that is serializable to JSON format.
 */
@JsonRootName(value = "hospitalcontacts")
public class JsonSerializableHospitalContacts {

    public static final String MESSAGE_DUPLICATE_PERSON = "Patients list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "Appointments list contains duplicate appointment(s).";
    public static final String MESSAGE_DUPLICATE_PRESCRIPTION =
            "Prescriptions list contains duplicate prescription(s).";

    private final List<JsonAdaptedPatient> persons = new ArrayList<>();
    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();
    private final List<JsonAdaptedPrescription> prescriptions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableHospitalContacts} with the specified
     * lists of persons and appointments.
     * This constructor is used by Jackson during deserialization from JSON.
     * If the provided lists are {@code null}, the corresponding internal lists
     * remain empty.
     *
     * @param persons       the list of {@link JsonAdaptedPerson} objects
     *                      representing persons
     * @param appointments  the list of {@link JsonAdaptedAppointment} objects
     *                      representing appointments
     * @param prescriptions the list of {@link JsonAdaptedPrescription} objects
     *                      representing prescriptions
     */
    @JsonCreator
    public JsonSerializableHospitalContacts(@JsonProperty("persons") List<JsonAdaptedPatient> persons,
            @JsonProperty("appointments") List<JsonAdaptedAppointment> appointments,
            @JsonProperty("prescriptions") List<JsonAdaptedPrescription> prescriptions) {
        if (persons != null) {
            this.persons.addAll(persons);
        }
        if (appointments != null) {
            this.appointments.addAll(appointments);
        }
        if (prescriptions != null) {
            this.prescriptions.addAll(prescriptions);
        }
    }

    /**
     * Converts a given {@link ReadOnlyHospitalContacts} into a
     * {@code JsonSerializableHospitalContacts}.
     * It creates JSON-adapted versions of each person, appointment and prescription
     * contained
     * in the provided {@code ReadOnlyHospitalContacts}.
     *
     * @param source the {@link ReadOnlyHospitalContacts} to convert to a
     *               JSON-serializable form
     */
    public JsonSerializableHospitalContacts(ReadOnlyHospitalContacts source) {
        persons.addAll(source.getPatientList().stream()
                .map(JsonAdaptedPatient::new)
                .collect(Collectors.toList()));
        appointments.addAll(source.getAppointmentList().stream()
                .map(JsonAdaptedAppointment::new)
                .collect(Collectors.toList()));
        prescriptions.addAll(source.getPrescriptionList().stream()
                .map(JsonAdaptedPrescription::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this hospital book into the model's {@code HospitalContacts} object.
     *
     * @throws IllegalValueException if there are any data constraints violated.
     */
    public HospitalContacts toModelType() throws IllegalValueException {
        HospitalContacts hospitalContacts = new HospitalContacts();

        for (JsonAdaptedPatient jsonAdaptedPatient : persons) {
            Patient person = jsonAdaptedPatient.toModelType();
            if (hospitalContacts.hasPatient(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            hospitalContacts.addPatient(person);
        }

        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            if (hospitalContacts.hasAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
            hospitalContacts.addAppointment(appointment);
        }

        for (JsonAdaptedPrescription jsonAdaptedPrescription : prescriptions) {
            Prescription prescription = jsonAdaptedPrescription.toModelType();
            if (hospitalContacts.hasPrescription(prescription)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PRESCRIPTION);
            }
            hospitalContacts.addPrescription(prescription);
        }

        return hospitalContacts;
    }
}

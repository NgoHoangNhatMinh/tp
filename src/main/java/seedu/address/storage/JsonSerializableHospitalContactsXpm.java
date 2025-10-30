package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.HospitalContactsXpm;
import seedu.address.model.ReadOnlyHospitalContactsXpm;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Patient;
import seedu.address.model.prescription.Prescription;

/**
 * An Immutable HospitalContactsXpm that is serializable to JSON format.
 */
@JsonRootName(value = "hospitalcontactsxpm")
public class JsonSerializableHospitalContactsXpm {

    public static final String MESSAGE_DUPLICATE_PERSON = "Patients list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "Appointments list contains duplicate appointment(s).";
    public static final String MESSAGE_DUPLICATE_PRESCRIPTION = "Prescriptions list contains duplicate prescription(s).";

    private final List<JsonAdaptedPatient> persons = new ArrayList<>();
    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();
    private final List<JsonAdaptedPrescription> prescriptions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableHospitalContactsXpm} with the specified
     * lists of
     * persons and appointments.
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
    public JsonSerializableHospitalContactsXpm(@JsonProperty("persons") List<JsonAdaptedPatient> persons,
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
     * Converts a given {@link ReadOnlyHospitalContactsXpm} into a
     * {@code JsonSerializableHospitalContactsXpm}.
     * It creates JSON-adapted versions of each person, appointment and prescription
     * contained
     * in the provided {@code ReadOnlyHospitalContactsXpm}.
     *
     * @param source the {@link ReadOnlyHospitalContactsXpm} to convert to a
     *               JSON-serializable form
     */
    public JsonSerializableHospitalContactsXpm(ReadOnlyHospitalContactsXpm source) {
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
     * Converts this address book into the model's {@code HospitalContactsXpm}
     * object.
     *
     * @throws IllegalValueException if there are any data constraints violated.
     */
    public HospitalContactsXpm toModelType() throws IllegalValueException {
        HospitalContactsXpm hospitalContactsXpm = new HospitalContactsXpm();

        for (JsonAdaptedPatient jsonAdaptedPatient : persons) {
            Patient person = jsonAdaptedPatient.toModelType();
            if (hospitalContactsXpm.hasPatient(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            hospitalContactsXpm.addPatient(person);
        }

        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            if (hospitalContactsXpm.hasAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
            hospitalContactsXpm.addAppointment(appointment);
        }

        for (JsonAdaptedPrescription jsonAdaptedPrescription : prescriptions) {
            Prescription prescription = jsonAdaptedPrescription.toModelType();
            if (hospitalContactsXpm.hasPrescription(prescription)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PRESCRIPTION);
            }
            hospitalContactsXpm.addPrescription(prescription);
        }

        return hospitalContactsXpm;
    }
}

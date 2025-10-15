package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.appointment.Appointment;
<<<<<<< HEAD
import seedu.address.model.person.Patient;
=======
import seedu.address.model.person.Person;
import seedu.address.model.prescription.Prescription;
>>>>>>> c34ce1c415a3d1e274aa8cc0643a6addf9a63a7d

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
public class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Patients list contains duplicate person(s).";
    public static final String MESSAGE_DUPLICATE_APPOINTMENT = "Appointments list contains duplicate appointment(s).";
    public static final String MESSAGE_DUPLICATE_PRESCRIPTION =
        "Prescriptions list contains duplicate prescription(s).";

    private final List<JsonAdaptedPatient> persons = new ArrayList<>();
    private final List<JsonAdaptedAppointment> appointments = new ArrayList<>();
    private final List<JsonAdaptedPrescription> prescriptions = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the specified lists of persons and appointments.
     * This constructor is used by Jackson during deserialization from JSON.
     * If the provided lists are {@code null}, the corresponding internal lists remain empty.
     *
     * @param persons the list of {@link JsonAdaptedPerson} objects representing persons
     * @param appointments the list of {@link JsonAdaptedAppointment} objects representing appointments
     * @param prescriptions the list of {@link JsonAdaptedPrescription} objects representing prescriptions
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPatient> persons,
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
     * Converts a given {@link ReadOnlyAddressBook} into a {@code JsonSerializableAddressBook}.
     * It creates JSON-adapted versions of each person, appointment and prescription contained
     * in the provided {@code ReadOnlyAddressBook}.
     *
     * @param source the {@link ReadOnlyAddressBook} to convert to a JSON-serializable form
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
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
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there are any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();

        for (JsonAdaptedPatient jsonAdaptedPatient : persons) {
            Patient person = jsonAdaptedPatient.toModelType();
            if (addressBook.hasPatient(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            addressBook.addPatient(person);
        }

        for (JsonAdaptedAppointment jsonAdaptedAppointment : appointments) {
            Appointment appointment = jsonAdaptedAppointment.toModelType();
            if (addressBook.hasAppointment(appointment)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_APPOINTMENT);
            }
            addressBook.addAppointment(appointment);
        }

        for (JsonAdaptedPrescription jsonAdaptedPrescription : prescriptions) {
            Prescription prescription = jsonAdaptedPrescription.toModelType();
            if (addressBook.hasPrescription(prescription)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PRESCRIPTION);
            }
            addressBook.addPrescription(prescription);
        }

        return addressBook;
    }
}

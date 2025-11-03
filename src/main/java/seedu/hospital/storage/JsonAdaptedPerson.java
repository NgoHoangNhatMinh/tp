package seedu.hospital.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.hospital.commons.exceptions.IllegalValueException;
import seedu.hospital.model.person.Address;
import seedu.hospital.model.person.Birthday;
import seedu.hospital.model.person.Email;
import seedu.hospital.model.person.Name;
import seedu.hospital.model.person.Patient;
import seedu.hospital.model.person.Phone;
import seedu.hospital.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Patient}.
 */
class JsonAdaptedPatient {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Patient's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String birthday;
    private final String gender;
    private final String emergency;
    private final String id;
    private final String lang;


    /**
     * Constructs a {@code JsonAdaptedPatient} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPatient(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                              @JsonProperty("email") String email, @JsonProperty("hospital") String address,
                              @JsonProperty("birthday") String birthday, @JsonProperty("gender") String gender,
                              @JsonProperty("emergency") String emergency, @JsonProperty("id") String id,
                              @JsonProperty("lang") String lang) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.birthday = birthday;
        this.gender = gender;
        this.emergency = emergency;
        this.id = id;
        this.lang = lang;

    }
    //
    //@JsonCreator
    //public JsonAdaptedPatient(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
    //                          @JsonProperty("email") String email, @JsonProperty("hospital") String hospital) {
    //
    //    this.name = name;
    //    this.phone = phone;
    //    this.email = email;
    //    this.hospital = hospital;
    //    this.birthday = "";
    //    this.gender = "";
    //    this.emergency = "";
    //    this.id = "";
    //    this.lang = "";
    //}

    /**
     * Converts a given {@code Patient} into this class for Jackson use.
     */
    public JsonAdaptedPatient(Patient source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        birthday = source.getBirthday().toString();
        gender = source.getGender();
        emergency = source.getEmergency();
        id = source.getId();
        lang = source.getLang();
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Patient} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Patient toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        final Address modelAddress = new Address(address);

        final Set<Tag> modelTags = new HashSet<>(personTags);

        if (birthday == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "birthday"));
        }

        if (gender == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "gender"));
        }

        if (emergency == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "emergency contact"));
        }

        if (id == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "ID"));
        }

        if (lang == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, "language"));
        }

        return new Patient(modelName, new Birthday(birthday), gender, modelPhone, modelEmail,
                modelAddress, emergency, id, lang);
    }

}

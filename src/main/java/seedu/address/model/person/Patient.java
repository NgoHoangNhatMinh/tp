package seedu.address.model.person;

import seedu.address.model.tag.Tag;

import java.util.Objects;
import java.util.Set;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

/*
Command format: patient add /patient <PATIENT_ID> /name "<FULL_NAME>" /dob <BIRTHDATE> /gender <GENDER>
 /phone <PHONE> /email <EMAIL> /address "<ADDRESS>" /emergency “<EMERGENCY>" /id <IDENTITY_NUMBER>
 /language <LANG>
*/

public class Patient extends Person {
    private final String patientId;
    private final Birthday birthday;
    private final String gender;
    private final String emergency;
    private final String id;
    private final String lang;

    public Patient (String patientId, Name name, Birthday birthday, String gender, Phone phone, Email email,
                    Address address, String emergency, String id, String lang, Set<Tag> tags) {
        super(name, phone, email, address, tags);

        requireAllNonNull(patientId, birthday, gender, emergency, id, lang);

        this.patientId = patientId;
        this.birthday = birthday;
        this.gender = gender;
        this.emergency = emergency;
        this.id = id;
        this.lang = lang;
    }

    public String getPatientId() {
        return patientId;
    }

    public Birthday getBirthday() {
        return birthday;
    }

    public String getGender() {
        return gender;
    }

    public String getEmergency() {
        return emergency;
    }

    public String getId() {
        return id;
    }

    public String getLang() {
        return lang;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Patient)) {
            return false;
        }

        if (!super.equals(other)) {
            return false;
        }

        final Patient otherPatient = (Patient) other;

        return patientId.equals(otherPatient.patientId)
                && id.equals(otherPatient.id)
                && gender.equals(otherPatient.gender)
                && birthday.equals(otherPatient.birthday);
        // Not checking for lang or emergency, since they may change over time.
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), patientId, id, gender, birthday);
    }

    @Override
    public String toString() {
        return String.format("Patient[" + super.getName() + birthday + "gender=%s, patientId=%s, id=%s, " + super.getPhone() + super.getEmail() + super.getAddress() + "emergency=%s, language=%s]",
                gender, patientId, id, emergency, lang);
    }
}

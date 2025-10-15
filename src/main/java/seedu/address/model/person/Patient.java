package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;


/*
Command format: patient add /n "<FULL_NAME>" /dob <BIRTHDATE> /gender <GENDER>
 /phone <PHONE> /email <EMAIL> /addr "<ADDRESS>" /em “<EMERGENCY>" /id <IDENTITY_NUMBER>
 /lang <LANG>
*/



/**
 * Represents a Patient in the hospital system.
 */
public class Patient extends Person {
    private final Birthday birthday;
    private final String gender;
    private final String emergency;
    private final String id;
    private final String lang;

    public Patient(Name name, Birthday birthday, String gender, Phone phone, Email email,
                    Address address, String emergency, String id, String lang, Set<Tag> tags) {
        super(name, phone, email, address, tags);

        requireAllNonNull(birthday, gender, emergency, id, lang);

        this.birthday = birthday;
        this.gender = gender;
        this.emergency = emergency;
        this.id = id;
        this.lang = lang;
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

        return id.equals(otherPatient.id)
                && gender.equals(otherPatient.gender)
                && birthday.equals(otherPatient.birthday);
        // Not checking for language or emergency, since they may change over time.
    }


    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, gender, birthday);
    }

    @Override
    public String toString() {
        return String.format("Patient[" + super.getName() + birthday + "gender=%s, patientId=%s, id=%s, "
                        + super.getPhone() + super.getEmail() + super.getAddress() + "emergency=%s, language=%s]",
                gender, id, emergency, lang);
    }
}

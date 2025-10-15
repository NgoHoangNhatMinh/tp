package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Patient in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Patient {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;
    private final String patientId;
    private final Birthday birthday;
    private final String gender;
    private final String emergency;
    private final String id;
    private final String lang;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();

    /**
     * Every field must be present and not null.
     */
    public Patient(String patientId, Name name, Birthday birthday, String gender, Phone phone, Email email,
                    Address address, String emergency, String id, String lang, Set<Tag> tags) {

        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);

        requireAllNonNull(patientId, birthday, gender, emergency, id, lang);

        this.patientId = patientId;
        this.birthday = birthday;
        this.gender = gender;
        this.emergency = emergency;
        this.id = id;
        this.lang = lang;
    }

    /**
     * Temporary constructor to not break the tests.
     */
    public Patient(Name name, Phone phone, Email email, Address address, Set<Tag> tags) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);

        this.patientId = "";
        this.birthday = new Birthday(LocalDateTime.now());
        this.gender = "";
        this.emergency = "";
        this.id = "";
        this.lang = "";
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
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

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if both patients have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePatient(Patient otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both patients have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
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
        // Not checking for language or emergency, since they may change over time.
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, phone, email, address, tags, patientId, id, gender, birthday);
    }

    @Override
    public String toString() {
        return String.format("Patient[" + getName() + birthday + "gender=%s, patientId=%s, id=%s, "
                        + getPhone() + getEmail() + getAddress() + "emergency=%s, language=%s]",
                gender, patientId, id, emergency, lang);
    }

}

package seedu.hospital.testutil;

import seedu.hospital.model.HospitalContacts;
import seedu.hospital.model.person.Patient;

/**
 * A utility class to help with building Addressbook objects.
 * Example usage: <br>
 * {@code HospitalContacts ab = new HospitalContactsBuilder().withPatientt("John", "Doe").build();}
 */
public class HospitalContactsBuilder {

    private HospitalContacts hospitalContacts;

    public HospitalContactsBuilder() {
        hospitalContacts = new HospitalContacts();
    }

    public HospitalContactsBuilder(HospitalContacts hospitalContacts) {
        this.hospitalContacts = hospitalContacts;
    }

    /**
     * Adds a new {@code Patientt} to the {@code HospitalContacts} that we are building.
     */
    public HospitalContactsBuilder withPatient(Patient person) {
        hospitalContacts.addPatient(person);
        return this;
    }

    public HospitalContacts build() {
        return hospitalContacts;
    }
}

package seedu.address.testutil;

import seedu.address.model.HospitalContactsXpm;
import seedu.address.model.person.Patient;

/**
 * A utility class to help with building HospitalContactsXpm objects.
 * Example usage: <br>
 * {@code HospitalContactsXpm ab = new HospitalContactsXpmBuilder().withPatientt("John", "Doe").build();}
 */
public class HospitalContactsXpmBuilder {

    private HospitalContactsXpm hospitalContactsXpm;

    public HospitalContactsXpmBuilder() {
        hospitalContactsXpm = new HospitalContactsXpm();
    }

    public HospitalContactsXpmBuilder(HospitalContactsXpm hospitalContactsXpm) {
        this.hospitalContactsXpm = hospitalContactsXpm;
    }

    /**
     * Adds a new {@code Patientt} to the {@code HospitalContactsXpm} that we are
     * building.
     */
    public HospitalContactsXpmBuilder withPatient(Patient person) {
        hospitalContactsXpm.addPatient(person);
        return this;
    }

    public HospitalContactsXpm build() {
        return hospitalContactsXpm;
    }
}

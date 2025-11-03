package seedu.hospital.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.hospital.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.hospital.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.hospital.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.hospital.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.hospital.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import seedu.hospital.commons.core.index.Index;
import seedu.hospital.commons.util.CollectionUtil;
import seedu.hospital.commons.util.ToStringBuilder;
import seedu.hospital.logic.Messages;
import seedu.hospital.logic.commands.exceptions.CommandException;
import seedu.hospital.model.Model;
import seedu.hospital.model.person.Address;
import seedu.hospital.model.person.Email;
import seedu.hospital.model.person.Name;
import seedu.hospital.model.person.Patient;
import seedu.hospital.model.person.Phone;

/**
 * Edits the details of an existing person in the hospital book.
 */
public class EditCommand extends Command {

    public static final String COMMAND_WORD = "edit";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the person identified "
            + "by the index number used in the displayed person list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + PREFIX_NAME + "NAME] "
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "Example: " + COMMAND_WORD + " 1 "
            + PREFIX_PHONE + "91234567 "
            + PREFIX_EMAIL + "johndoe@example.com";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Edited Patient: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the hospital book.";

    private final Index index;
    private final EditPatientDescriptor editPatientDescriptor;

    /**
     * @param index                 of the person in the filtered person list to edit
     * @param editPatientDescriptor details to edit the person with
     */
    public EditCommand(Index index, EditPatientDescriptor editPatientDescriptor) {
        requireNonNull(index);
        requireNonNull(editPatientDescriptor);

        this.index = index;
        this.editPatientDescriptor = new EditPatientDescriptor(editPatientDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient personToEdit = lastShownList.get(index.getZeroBased());
        Patient editedPatient = createEditedPatient(personToEdit, editPatientDescriptor);

        if (!personToEdit.isSamePatient(editedPatient) && model.hasPatient(editedPatient)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPatient(personToEdit, editedPatient);
        model.updateFilteredPatientList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(editedPatient)));
    }

    /**
     * Creates and returns a {@code Patient} with the details of {@code personToEdit}
     * edited with {@code editPatientDescriptor}.
     */
    private static Patient createEditedPatient(Patient personToEdit, EditPatientDescriptor editPatientDescriptor) {
        assert personToEdit != null;

        Name updatedName = editPatientDescriptor.getName().orElse(personToEdit.getName());
        Phone updatedPhone = editPatientDescriptor.getPhone().orElse(personToEdit.getPhone());
        Email updatedEmail = editPatientDescriptor.getEmail().orElse(personToEdit.getEmail());
        Address updatedAddress = editPatientDescriptor.getAddress().orElse(personToEdit.getAddress());
        //Set<Tag> updatedTags = editPatientDescriptor.getTags().orElse(personToEdit.getTags());

        return new Patient(updatedName, updatedPhone, updatedEmail, updatedAddress);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditCommand otherEditCommand)) {
            return false;
        }

        return index.equals(otherEditCommand.index)
                && editPatientDescriptor.equals(otherEditCommand.editPatientDescriptor);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("index", index)
                .add("editPatientDescriptor", editPatientDescriptor)
                .toString();
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class EditPatientDescriptor {
        private Name name;
        private Phone phone;
        private Email email;
        private Address address;


        public EditPatientDescriptor() {
        }

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public EditPatientDescriptor(EditPatientDescriptor toCopy) {
            setName(toCopy.name);
            setPhone(toCopy.phone);
            setEmail(toCopy.email);
            setAddress(toCopy.address);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(name, phone, email, address);
        }

        public void setName(Name name) {
            this.name = name;
        }

        public Optional<Name> getName() {
            return Optional.ofNullable(name);
        }

        public void setPhone(Phone phone) {
            this.phone = phone;
        }

        public Optional<Phone> getPhone() {
            return Optional.ofNullable(phone);
        }

        public void setEmail(Email email) {
            this.email = email;
        }

        public Optional<Email> getEmail() {
            return Optional.ofNullable(email);
        }

        public void setAddress(Address address) {
            this.address = address;
        }

        public Optional<Address> getAddress() {
            return Optional.ofNullable(address);
        }

        @Override
        public boolean equals(Object other) {
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditPatientDescriptor otherEditPatientDescriptor)) {
                return false;
            }

            return Objects.equals(name, otherEditPatientDescriptor.name)
                    && Objects.equals(phone, otherEditPatientDescriptor.phone)
                    && Objects.equals(email, otherEditPatientDescriptor.email)
                    && Objects.equals(address, otherEditPatientDescriptor.address);
        }

        @Override
        public String toString() {
            return new ToStringBuilder(this)
                    .add("name", name)
                    .add("phone", phone)
                    .add("email", email)
                    .add("hospital", address)
                    .toString();
        }
    }
}

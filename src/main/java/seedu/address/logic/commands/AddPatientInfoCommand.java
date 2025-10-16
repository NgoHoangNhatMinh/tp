package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;


import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

import seedu.address.model.person.Patient;


/**
 * Adds patient information to the address book.
 */
public class AddPatientInfoCommand extends Command {

    public static final String COMMAND_WORD = "i-add"; // i for patient information

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a patient's information.\n"
            + "Parameters: "
            + "/n \"NAME\" "
            + "/dob BIRTHDATE "
            + "/gender GENDER "
            + "/phone PHONE "
            + "/email EMAIL "
            + "/addr \"ADDRESS\" "
            + "/em \"EMERGENCY_CONTACT\" "
            + "/id IDENTITY_NUMBER "
            + "/lang LANGUAGE \n";


    public static final String MESSAGE_SUCCESS = "New patient added: %1$s";
    public static final String MESSAGE_DUPLICATE_PATIENT = "This patient already exists in the address book.";

    private final Patient toAdd;

    /**
     * Constructs an {@code AddPatientInfoCommand} with the specified patient details.
     *
     * @param patient The {@code Patient} object containing the patient details. Must not be {@code null}.
     */
    public AddPatientInfoCommand(Patient patient) {
        requireNonNull(patient);
        this.toAdd = patient;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPatient(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PATIENT);
        }

        model.addPatient(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof AddPatientInfoCommand
                && toAdd.equals(((AddPatientInfoCommand) other).toAdd));
    }
}

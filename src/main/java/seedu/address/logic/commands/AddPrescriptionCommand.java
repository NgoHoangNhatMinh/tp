package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.commands.AddAppointmentCommand.MESSAGE_PATIENT_NOT_FOUND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DOSAGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DURATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FREQUENCY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MEDICATION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT;
import static seedu.address.storage.JsonSerializableAddressBook.MESSAGE_DUPLICATE_PRESCRIPTION;

import java.util.Optional;

import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Patient;
import seedu.address.model.prescription.Prescription;

/**
 * Prescribe medication for an existing patient.
 */
public class AddPrescriptionCommand extends Command {

    public static final String COMMAND_WORD = "p-add";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Prescribes medication to a patient. "
        + "Parameters: "
        + PREFIX_PATIENT + " PATIENT"
        + PREFIX_MEDICATION + " MEDICATION"
        + PREFIX_DOSAGE + " DOSAGE"
        + PREFIX_FREQUENCY + " FREQUENCY"
        + PREFIX_DURATION + " DURATION" +"\n"
        + "Example: p-add p/Alex Yeoh m/Methamphetamine d/1000 f/3 dur/365";

    public static final String MESSAGE_SUCCESS = "New prescription added: %1$s";

    private final Prescription toAdd;

    public AddPrescriptionCommand(Prescription prescription) {
        this.toAdd = prescription;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        Optional<Patient> matchedPatient = model.getAddressBook()
                .getPatientList()
                .stream()
                .filter(p -> p.getName().fullName.equals(toAdd.getPatientId()))
                .findFirst();

        if (matchedPatient.isEmpty()) {
            throw new CommandException(String.format(MESSAGE_PATIENT_NOT_FOUND, toAdd.getPatientId()));
        }

        if (model.hasPrescription(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PRESCRIPTION);
        }

        model.addPrescription(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, Messages.format(toAdd)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof AddPrescriptionCommand c)) {
            return false;
        }

        return toAdd.equals(c.toAdd);
    }
}

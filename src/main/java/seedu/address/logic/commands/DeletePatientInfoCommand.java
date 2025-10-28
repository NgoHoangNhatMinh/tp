package seedu.address.logic.commands;

import seedu.address.commons.util.ToStringBuilder;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;
import seedu.address.model.person.Patient;

import java.util.List;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

public class DeletePatientInfoCommand extends Command{
    public static final String COMMAND_WORD = "i-delete";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the patient information identified by the patient name.\n"
            + "Parameters: " + PREFIX_NAME + "PATIENT_NAME";

    public static final String MESSAGE_DELETE_PATIENT_SUCCESS = "Patient delete: %1$s";
    public static final String MESSAGE_PATIENT_NOT_FOUND = "No patient found with name '%1$s'";

    private final String patientName;

    public DeletePatientInfoCommand(String patientName) {
        this.patientName = patientName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPatientList();

        Patient patientToDelete = null;
        for (Patient p: lastShownList) {
//            TODO: change name to class Name
            if (p.getName().equals(new Name(patientName))) {
                patientToDelete = p;
                break;
            }
        }

        if (patientToDelete == null) {
            throw new CommandException(String.format(MESSAGE_PATIENT_NOT_FOUND, patientName));
        }

        model.deletePatient(patientToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_PATIENT_SUCCESS, patientToDelete));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeletePatientInfoCommand)) {
            return false;
        }

        DeletePatientInfoCommand c = (DeletePatientInfoCommand) other;
        return patientName.equals(c.patientName);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("patientName", patientName)
                .toString();
    }
}

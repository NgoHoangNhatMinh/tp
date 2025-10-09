package seedu.address.logic.commands;

import static seedu.address.logic.parser.CliSyntax.PREFIX_PATIENT;

import seedu.address.model.Model;

/**
 * Prescribe medication for an existing patient
 */
public class PrescribeCommand extends Command {

    public static final String COMMAND_WORD = "prescription-add";

    public static final String MESSAGE_USAGE = COMMAND_WORD
        + ": Prescribes medication to a patient. "
        + "Parameters: "
        + PREFIX_PATIENT + "PATIENT";

    @Override
    public CommandResult execute(Model model) {
        return new CommandResult("Prescribing medication to patient");
    }
}

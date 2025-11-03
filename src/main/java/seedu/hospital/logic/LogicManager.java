package seedu.hospital.logic;

import java.io.IOException;
import java.nio.file.AccessDeniedException;
import java.nio.file.Path;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import seedu.hospital.commons.core.GuiSettings;
import seedu.hospital.commons.core.LogsCenter;
import seedu.hospital.logic.commands.Command;
import seedu.hospital.logic.commands.CommandResult;
import seedu.hospital.logic.commands.exceptions.CommandException;
import seedu.hospital.logic.parser.HospitalContactsParser;
import seedu.hospital.logic.parser.exceptions.ParseException;
import seedu.hospital.model.Model;
import seedu.hospital.model.ReadOnlyHospitalContacts;
import seedu.hospital.model.appointment.Appointment;
import seedu.hospital.model.person.Patient;
import seedu.hospital.model.prescription.Prescription;
import seedu.hospital.storage.Storage;

/**
 * The main LogicManager of the app.
 */
public class LogicManager implements Logic {
    public static final String FILE_OPS_ERROR_FORMAT = "Could not save data due to the following error: %s";

    public static final String FILE_OPS_PERMISSION_ERROR_FORMAT =
            "Could not save data to file %s due to insufficient permissions to write to the file or the folder.";

    private final Logger logger = LogsCenter.getLogger(LogicManager.class);

    private final Model model;
    private final Storage storage;
    private final HospitalContactsParser hospitalContactsParser;

    /**
     * Constructs a {@code LogicManager} with the given {@code Model} and {@code Storage}.
     */
    public LogicManager(Model model, Storage storage) {
        this.model = model;
        this.storage = storage;
        hospitalContactsParser = new HospitalContactsParser();
    }

    @Override
    public CommandResult execute(String commandText) throws CommandException, ParseException {
        logger.info("----------------[USER COMMAND][" + commandText + "]");

        CommandResult commandResult;
        Command command = hospitalContactsParser.parseCommand(commandText);
        commandResult = command.execute(model);

        try {
            storage.saveHospitalContacts(model.getHospitalContacts());
        } catch (AccessDeniedException e) {
            throw new CommandException(String.format(FILE_OPS_PERMISSION_ERROR_FORMAT, e.getMessage()), e);
        } catch (IOException ioe) {
            throw new CommandException(String.format(FILE_OPS_ERROR_FORMAT, ioe.getMessage()), ioe);
        }

        return commandResult;
    }

    @Override
    public ReadOnlyHospitalContacts getHospitalContacts() {
        return model.getHospitalContacts();
    }

    @Override
    public ObservableList<Patient> getFilteredPatientList() {
        return model.getFilteredPatientList();
    }

    @Override
    public ObservableList<Prescription> getFilteredPrescriptionList() {
        return model.getFilteredPrescriptionList();
    }

    @Override
    public ObservableList<Appointment> getFilteredAppointmentList() {
        return model.getFilteredAppointmentList();
    }

    @Override
    public Path getHospitalContactsFilePath() {
        return model.getHospitalContactsFilePath();
    }

    @Override
    public GuiSettings getGuiSettings() {
        return model.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        model.setGuiSettings(guiSettings);
    }
}

package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.Optional;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.appointment.Appointment;
import seedu.address.model.person.Patient;

/**
 * Views all appointments for a given patient, optionally within a date range.
 * Results are shown in ascending date-time order.
 */
public class ViewAppointmentsCommand extends Command {

    public static final String COMMAND_WORD = "a-view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows appointments for a patient, "
            + "sorted by date-time.\n"
            + "Parameters: p/PATIENT_NAME [from/YYYY-MM-DD] [to/YYYY-MM-DD]\n"
            + "Example: " + COMMAND_WORD + " p/John Doe\n"
            + "Example: " + COMMAND_WORD + " p/John Doe from/2025-01-01 to/2025-12-31";

    public static final String MESSAGE_PATIENT_NOT_FOUND = "Patient '%1$s' does not exist in the address book.";
    public static final String MESSAGE_SUCCESS_WITH_RANGE =
            "Showing %1$d appointment(s) for %2$s from %3$s to %4$s (inclusive).";
    public static final String MESSAGE_SUCCESS_NO_RANGE =
            "Showing %1$d appointment(s) for %2$s.";

    private final String patientName;
    private final Optional<LocalDate> fromDate;
    private final Optional<LocalDate> toDate;

    public ViewAppointmentsCommand(String patientName,
                                   Optional<LocalDate> fromDate,
                                   Optional<LocalDate> toDate) {
        requireNonNull(patientName);
        this.patientName = patientName;
        this.fromDate = fromDate;
        this.toDate = toDate;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        boolean patientExists = model.getAddressBook().getPatientList()
                .stream()
                .map(Patient::getName)
                .anyMatch(n -> n.fullName.equals(patientName));

        if (!patientExists) {
            throw new CommandException(String.format(MESSAGE_PATIENT_NOT_FOUND, patientName));
        }

        // Build inclusive date-time bounds if provided
        Optional<LocalDateTime> lowerBound = fromDate.map(d -> d.atStartOfDay());
        Optional<LocalDateTime> upperBound = toDate.map(d -> d.atTime(LocalTime.MAX)); // inclusive end-of-day

        // Filter predicate: match patient + optional range
        model.updateFilteredAppointmentList(appt -> {
            if (!appt.getPatientId().equals(patientName)) {
                return false;
            }
            LocalDateTime dt = appt.getDateTime();
            if (lowerBound.isPresent() && dt.isBefore(lowerBound.get())) {
                return false;
            }
            if (upperBound.isPresent() && dt.isAfter(upperBound.get())) {
                return false;
            }
            return true;
        });

        // Sort ascending by date-time
        model.setAppointmentListComparator(Comparator.comparing(Appointment::getDateTime));

        int count = model.getFilteredAppointmentList().size();

        if (fromDate.isPresent() || toDate.isPresent()) {
            String fromStr = fromDate.map(LocalDate::toString).orElse("-");
            String toStr = toDate.map(LocalDate::toString).orElse("-");
            return new CommandResult(String.format(
                    MESSAGE_SUCCESS_WITH_RANGE, count, patientName, fromStr, toStr));
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS_NO_RANGE, count, patientName));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof ViewAppointmentsCommand)) {
            return false;
        }
        ViewAppointmentsCommand o = (ViewAppointmentsCommand) other;
        return patientName.equals(o.patientName)
                && fromDate.equals(o.fromDate)
                && toDate.equals(o.toDate);
    }
}

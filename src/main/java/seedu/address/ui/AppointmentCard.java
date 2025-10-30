package seedu.address.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.appointment.Appointment;

/**
 * Represents a single {@code Appointment} card in the list.
 */
public class AppointmentCard extends UiPart<Region> {

    private static final String FXML = "AppointmentCard.fxml";

    public final Appointment appointment;

    @FXML private HBox cardPane;
    @FXML private Label patient;
    @FXML private Label doctor;
    @FXML private Label datetime;
    @FXML private Label note;

    /**
     * Creates and initializes an {@code AppointmentCard} to display the details of the given {@link Appointment}.
     * The card shows the patient ID, doctor, date/time, and note for the appointment.
     *
     * @param appointment The {@code Appointment} whose details are to be displayed. Must not be {@code null}.
     */
    public AppointmentCard(Appointment appointment) {
        super(FXML);
        this.appointment = appointment;
        patient.setText("Patient: " + appointment.getPatientName());
        doctor.setText("Doctor: " + appointment.getDoctor());
        datetime.setText("Date: " + appointment.getDateTime().toString());
        note.setText("Note: " + appointment.getReason());
    }
}

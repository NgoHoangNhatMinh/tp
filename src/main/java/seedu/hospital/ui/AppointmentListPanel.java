package seedu.hospital.ui;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.hospital.model.appointment.Appointment;

/**
 * Panel containing the list of appointments.
 */
public class AppointmentListPanel extends UiPart<Region> {

    private static final String FXML = "AppointmentListPanel.fxml";

    @FXML
    private ListView<Appointment> appointmentListView;

    /**
     * Creates an {@code AppointmentListPanel} that displays a list of {@link Appointment} objects.
     *
     * @param appointments The observable list of appointments to display. Must not be {@code null}.
     */
    public AppointmentListPanel(ObservableList<Appointment> appointments) {
        super(FXML);
        appointmentListView.setItems(appointments);
        appointmentListView.setCellFactory(listView -> new AppointmentListViewCell());
    }

    /** Custom cell to render each appointment card. */
    class AppointmentListViewCell extends ListCell<Appointment> {
        @Override
        protected void updateItem(Appointment appointment, boolean empty) {
            super.updateItem(appointment, empty);
            if (empty || appointment == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new AppointmentCard(appointment).getRoot());
            }
        }
    }
}

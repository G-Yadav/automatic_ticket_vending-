package sample.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class JourneyDetail implements Initializable {

    @FXML
    private TextField from;

    @FXML
    private TextField to;

    @FXML
    private DatePicker datePicker;

    @FXML
    private ComboBox<String> choiceBox;

    @FXML
    private Button findTrain;

    @FXML
    private Label usernameLabel;

    @FXML
    private Button logout;

    @FXML
    private Label warning;

    @FXML
    public void initialize(URL url , ResourceBundle rs) {
        datePicker.setValue(LocalDate.now());
        choiceBox.setItems(MainController.list);
        choiceBox.getSelectionModel().selectFirst();
        usernameLabel.setText(MainController.name);
        warning.setText("");
    }

    @FXML
    void findTrainButtonClicked(MouseEvent event) {
        try{
            String  fromStation = from.getText().trim();
            if(fromStation.isEmpty()) {
                from.clear();
                from.requestFocus();
                throw new Exception("Arrival Cannot be empty");
            }
            String toStation = to.getText().trim();
            if(toStation.isEmpty()) {
                to.clear();
                to.requestFocus();
                throw new Exception("Destination cannot be empty");
            }

            LocalDate dateString = datePicker.getValue();
            if (dateString.isBefore(LocalDate.now())) {
                datePicker.requestFocus();
                throw new Exception("Please enter a valid date");
            }
            String day = dateString.getDayOfWeek().toString();
            String type = choiceBox.getSelectionModel().getSelectedItem();
            MainController.from = fromStation;
            MainController.to = toStation;
            MainController.date = dateString;
            MainController.day = day;
            MainController.typeClass = type;

            Node node = (Node) event.getSource();
            Stage takeStage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/Availability.fxml"));
            takeStage.setScene(new Scene(root , 780 , 400));
            takeStage.setTitle("Availability");
            takeStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            warning.setText(e.getMessage());
        }
    }

    @FXML
    void logoutButtonClicked(MouseEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage takeStage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/Login.fxml"));
            takeStage.setScene(new Scene(root , 600 ,400));
            takeStage.setTitle("Login");
            takeStage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}

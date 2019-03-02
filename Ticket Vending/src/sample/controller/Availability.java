package sample.controller;

import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.datamodel.TrainData;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ResourceBundle;

public class Availability implements Initializable {

    @FXML
    private TextField from;

    @FXML
    private Label usernameLabel;

    @FXML
    private Button logout;

    @FXML
    private TextField to;

    @FXML
    private DatePicker date;

    @FXML
    private ComboBox<String> typeClass;

    @FXML
    private Button modifyButton;

    @FXML
    private TableView<TrainData> tableView;

    @FXML
    private TableColumn<TrainData, String> trainName;

    @FXML
    private TableColumn<TrainData, LocalTime> departureTime;

    @FXML
    private TableColumn<TrainData, LocalTime> arrivalTime;

    @FXML
    private TableColumn<TrainData , Double> amount;

    @FXML
    private TableColumn<TrainData,String> type;
    @FXML
    private TableColumn<TrainData, Button> availability;

    @FXML
    private Label warning;

    @FXML
    private Label fromLabel;

    @FXML
    private Label toLabel;

    public void initialize(URL url , ResourceBundle rs) {
        warning.setText("");

        from.setText(MainController.from);
        to.setText(MainController.to);
        date.setValue(MainController.date);
        typeClass.setItems(MainController.list);
        typeClass.getSelectionModel().select(MainController.typeClass);

        usernameLabel.setText(MainController.name);

        fromLabel.setText(MainController.from);
        toLabel.setText(MainController.to);

        trainName.setCellValueFactory(new PropertyValueFactory<>("name"));
        departureTime.setCellValueFactory(new PropertyValueFactory<>("arrivalTime"));
        arrivalTime.setCellValueFactory(new PropertyValueFactory<>("reachingTime"));
        amount.setCellValueFactory(new PropertyValueFactory<>("amount"));
        type.setCellValueFactory(new PropertyValueFactory<>("type"));
        availability.setCellValueFactory(new PropertyValueFactory<>("availability"));

        tableView.setItems(
                FXCollections.observableArrayList(
                        new TrainData("Rajdhani" , LocalTime.of(11,20),LocalTime.of(13 , 25) ,524.00, MainController.typeClass ,new Button("Payment"))
                )
        );

        //code to fill table view with train details
    }



    @FXML
    void logoutButtonClicked(MouseEvent event) {
        try {
            //because of duplicate code
            String take = null;
            Node node = (Node) event.getSource();
            Stage takeStage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/Login.fxml"));
            takeStage.setScene(new Scene(root , 600 ,400));
            takeStage.setTitle("Login");
            takeStage.show();
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @FXML
    void modifyButtonClicked(MouseEvent event) {
        try{
            //because of duplicate code
            String take = null;
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

            LocalDate dateString = date.getValue();
            if (dateString.isBefore(LocalDate.now())) {
                date.requestFocus();
                throw new Exception("Please enter a valid date");
            }
            String day = dateString.getDayOfWeek().toString();
            String type = typeClass.getSelectionModel().getSelectedItem();
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

}

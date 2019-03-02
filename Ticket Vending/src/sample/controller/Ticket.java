package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Ticket implements Initializable {

    @FXML
    private TextArea textArea;

    @FXML
    private Label homepageLabel;

    @FXML
    private Label usernameLabel;

    public void initialize(URL url , ResourceBundle rs) {
        textArea.setText(
                "Name: "+MainController.name+"\nFrom: "+MainController.from+
                       "\nTo: "+ MainController.to + "\nTrain: " + MainController.train+
                       "\nClass: "+MainController.typeClass + "\nJourney Date: " + MainController.date
        );
    }

    @FXML
    void homepageLabelClicked(MouseEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage takeStage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/JourneyDetail.fxml"));
            takeStage.setScene(new Scene(root , 600 ,400));
            takeStage.setTitle("IRCTC");
            takeStage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void logoutClicked(ActionEvent event) {
        try {
            //because of duplicate code again
            String rtae  = null;
            //this too
            rtae = "Gaurav";
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

    @FXML
    void mouseEnter(MouseEvent event) {
        homepageLabel.setTextFill(Color.web("#00ff00"));
    }

    @FXML
    void mouseExit(MouseEvent event) {
        homepageLabel.setTextFill(Color.web("#ffffff"));
    }

}

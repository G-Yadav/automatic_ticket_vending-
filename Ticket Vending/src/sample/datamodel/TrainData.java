package sample.datamodel;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.controller.MainController;

import java.io.IOException;
import java.time.LocalTime;

public class TrainData {
    private String name;
    private LocalTime arrivalTime;
    private LocalTime reachingTime;
    private Double amount;
    private String type;
    private Button availability;

    public TrainData(String name, LocalTime arrivalTime, LocalTime reachingTime,Double amount, String type ,Button availability) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.reachingTime = reachingTime;
        this.amount = amount;
        this.type = type;
        this.availability = availability;
        this.availability.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Node node = (Node) actionEvent.getSource();
                    Stage takeStage = (Stage) node.getScene().getWindow();
                    Parent root = FXMLLoader.load(getClass().getResource("../fxml/Payment.fxml"));
                    takeStage.setScene(new Scene(root, 600, 400));
                    takeStage.setTitle("Payment");
                    takeStage.show();
                } catch(IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public LocalTime getReachingTime() {
        return reachingTime;
    }

    public void setReachingTime(LocalTime reachingTime) {
        this.reachingTime = reachingTime;
    }

    public Button getAvailability() {
        return availability;
    }

    public void setAvailability(Button availability) {
        this.availability = availability;
    }
}

package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Payment implements Initializable {

    @FXML
    private TextField dCardNo;

    @FXML
    private TextField dName;

    @FXML
    private TextField dMonth;

    @FXML
    private TextField dYear;

    @FXML
    private TextField dCVV;

    @FXML
    private Button dPayment;

    @FXML
    private Button cPayment;

    @FXML
    private TextField cCardNo;

    @FXML
    private TextField cName;

    @FXML
    private TextField cMonth;

    @FXML
    private TextField cYear;

    @FXML
    private TextField cCVV;

    @FXML
    private TextField upiId;

    @FXML
    private Label amountLabel;

    @FXML
    private Label dWarning;

    @FXML
    private Label cWarning;

    public void initialize(URL url , ResourceBundle rs) {
        cWarning.setText("");
        dWarning.setText("");
    }

    @FXML
    void paymentButtonClicked(ActionEvent event) {

        if (event.getSource().equals(cPayment)) {
            try {
                String cardNo = cCardNo.getText().trim();
                if (!cardNo.matches("^[0-9]{16}$")) {
                    cCardNo.requestFocus();
                    throw new Exception("Please enter valid credentials");
                }
                String name = cName.getText().trim();
                if (name.isEmpty()) {
                    cName.requestFocus();
                    throw new Exception("Name field cannot be empty");
                }
                String month = cMonth.getText().trim();
                if (!month.matches("^[0-9]{1,2}$")) {
                    cMonth.requestFocus();
                    throw new Exception("Please enter valid credentials");
                }
                String year = cYear.getText().trim();
                if (!year.matches("^[0-9]{2,4}$")) {
                    cYear.requestFocus();
                    throw new Exception("Please enter valid credentials");
                }
                String cvv = cCVV.getText().trim();
                if (!cvv.matches("^[0-9]{3}$")) {
                    cCVV.requestFocus();
                    throw new Exception("Please enter valid credentials");
                }

                Node node = (Node) event.getSource();
                Stage takeStage = (Stage) node.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("../fxml/Ticket.fxml"));
                takeStage.setTitle("Ticket");
                takeStage.setScene(new Scene(root, 600, 400));
                takeStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                cWarning.setText(e.getMessage());
            }
        } else {
            try {
                String cardNo = dCardNo.getText().trim();
                if(!cardNo.matches("^[0-9]{16}$")){
                    dCardNo.requestFocus();
                    throw new Exception("Please enter valid credentials");
                }
                String name = dName.getText().trim();
                if(name.isEmpty()) {
                    dName.requestFocus();
                    throw new Exception("Name field cannot be empty");
                }

                String year = dYear.getText().trim();
                if(!year.matches("^[0-9]{2,4}$")){
                    dYear.requestFocus();
                    throw new Exception("Please enter valid year");
                }
                String month = dMonth.getText().trim();
                if(!month.matches("^[0-9]{1,2}$")) {
                    dMonth.requestFocus();
                    throw new Exception("Please enter valid month");
                }
                String cvv = dCVV.getText().trim();
                if(!cvv.matches("^[0-9]{3}$")){
                    dCVV.requestFocus();
                    throw new Exception("Please enter valid CVV");
                }
                Node node = (Node) event.getSource();
                Stage takeStage = (Stage) node.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("../fxml/Ticket.fxml"));
                takeStage.setTitle("Ticket");
                takeStage.setScene(new Scene(root , 600,400));
                takeStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                dWarning.setText(e.getMessage());
            }
        }
    }


    @FXML
    void requestPaymentClicked(ActionEvent event) {
        try{
            String upiid = upiId.getText().trim();
            if(upiid.isEmpty()) {
                upiId.requestFocus();
                throw new Exception("enter id");
            }
            Node node = (Node) event.getSource();
            Stage takeStage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/Ticket.fxml"));
            takeStage.setTitle("Ticket");
            takeStage.setScene(new Scene(root , 600,400));
            takeStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }  catch(Exception e) {
            upiId.setPromptText(e.getMessage());
        }
    }
}

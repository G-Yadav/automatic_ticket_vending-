package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private CheckBox rememberCheckBox;

    @FXML
    private Button loginButton;

    @FXML
    private Label forgetLabel;

    @FXML
    private Button signUpButton;

    @FXML
    private Label errorMessage;

    @FXML
    void forgetClicked(MouseEvent event) {

    }

    @FXML
    void forgetHoverEnter(MouseEvent event) {
        forgetLabel.setTextFill(Color.web("#0000ff"));
    }

    @FXML
    void forgetHoverExit(MouseEvent event) {
        forgetLabel.setTextFill(Color.web("#000000"));
    }

    @FXML
    void onClick(ActionEvent event) {
        try (
                // change connection to IRCTC database
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Amazon" , "root" , "")
        ) {
            //Make another class for queries
            String Login_Query = "SELECT id,name,type FROM logindetail where username=? and password=?";
            String user = usernameField.getText().trim();
            if(user.isEmpty()) {
                usernameField.selectAll();
                usernameField.requestFocus();
                errorMessage.setText("Please Enter Username");
            }
            String pass = passwordField.getText().trim();
            if(pass.isEmpty()) {
                passwordField.selectAll();
                passwordField.requestFocus();
                errorMessage.setText("Enter valid password or username");
            }
            PreparedStatement data = connection.prepareStatement(Login_Query);
            data.setString(1,user);
            data.setString(2,pass);
            ResultSet rs = data.executeQuery();
            Integer id = null;
//            String type = null;
            String name = null;
            while(rs.next()) {
                id = rs.getInt("id");
                name = rs.getString("name");
//                type = rs.getString("type");
            }
            if (id != null) {
                MainController.name = name;
                Node node = (Node) event.getSource();
                Stage takeStage = (Stage) node.getScene().getWindow();
                Parent root = FXMLLoader.load(getClass().getResource("../fxml/JourneyDetail.fxml"));
                takeStage.setTitle("IRCTC");
                takeStage.setScene(new Scene(root,600 ,400));
                takeStage.show();
            } else {
                errorMessage.setText("Please Enter Valid Credential");
                passwordField.clear();
                passwordField.requestFocus();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage() + " or Connection to database fails. Please Start XAMPP server");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void signUpClicked(ActionEvent event) {
        try {
            Node node = (Node) event.getSource();
            Stage takeStage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(getClass().getResource("../fxml/Signup.fxml"));
            takeStage.setScene(new Scene(root , 600 ,400));
            takeStage.setTitle("Signup");
            takeStage.show();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

}

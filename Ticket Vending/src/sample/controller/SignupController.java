package sample.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.*;

public class SignupController {

    @FXML
    private TextField firstname;

    @FXML
    private TextField lastname;

    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField confirm;

    @FXML
    private Button continueButton;

    @FXML
    private Label errorMessage;

    @FXML
    void continueClicked(ActionEvent event) {
        try (
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/Amazon", "root", "")
        ) {
            errorMessage.setTextFill(Color.web("#ff0000"));
            errorMessage.setText("");
            String name = firstname.getText().trim();
            if(name.isEmpty()) {
                firstname.clear();
                firstname.requestFocus();
                throw new Exception("Please enter firstname") ;
            }

            String last = lastname.getText().trim();
            if(last.isEmpty()) {
                lastname.clear();
                lastname.requestFocus();
                throw new Exception("Please enter lastname");
            }

            String user = username.getText().trim();
            if(user.isEmpty()) {
                username.requestFocus();
                errorMessage.setTextFill(Color.web("#ff0000"));
                throw new Exception("Username Cannot be Empty");
            }
            String pass = password.getText();
            if(pass.isEmpty()){
                password.requestFocus();
                errorMessage.setTextFill(Color.web("#ff0000"));
                throw new Exception("Password field cannot be Empty");
            }
            String confirmText = confirm.getText();

            if (!confirmText.equals(pass)) {
                errorMessage.setTextFill(Color.web("#ff0000"));
                password.clear();
                confirm.clear();
                password.requestFocus();
                throw new Exception("PasswordMismatch");
            }

            String INSERT_USER = "INSERT INTO logindetail(name , last ,username , password , type) VALUES(?,?,? , ? ,\"User\")";
            PreparedStatement insertStatement = connection.prepareStatement(INSERT_USER);
            insertStatement.setString(1, name);
            insertStatement.setString(2, last);
            insertStatement.setString(3, user);
            insertStatement.setString(4, pass);
            Integer integer = insertStatement.executeUpdate();

            Node node = (Node) event.getSource();
            Stage takestage = (Stage) node.getScene().getWindow();
            Scene login = new Scene(FXMLLoader.load(getClass().getResource("../Addition.fxml")) , 600,400);
            takestage.setScene(login);
            takestage.setTitle("Login");
            takestage.show();


        } catch (SQLIntegrityConstraintViolationException ex) {
            errorMessage.setTextFill(Color.web("#ff0000"));
            password.clear();
            confirm.clear();
            username.requestFocus();
            errorMessage.setText("This username is already taken");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex){
            errorMessage.setText(ex.getMessage());
        }
    }

}

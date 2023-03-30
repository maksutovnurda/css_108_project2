package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import utils.User;

import java.io.IOException;

public class SignupController {

    @FXML
    Label error;
    @FXML
    private TextField email;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password2;

    @FXML
    private TextField username;

    @FXML
    private RadioButton maleButton;

    @FXML
    void viewLogIn(ActionEvent event) throws IOException {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(new FXMLLoader(getClass().getResource("/view/login.fxml")).load()));
    }

    @FXML
    void signUp(ActionEvent event) throws IOException {
        String gender = maleButton.isSelected() ? "Male" : "Female";
        User user = new User(email.getText(), username.getText(), password.getText(), gender, 0);
        error.setVisible(true);
        if (password.getText() == "" || email.getText() == "" || username.getText() == "") {
            error.setText("All fields must be filled!");
        } else if (!user.validateEmail()) {
            error.setText("Incorrect email format!");
        } else if (!user.validateEmail()) {
            error.setText("Incorrect email format!");
        } else if (!user.validateUsername()) {
            error.setText("Incorrect username format!");
        } else if (!user.validatePassword()) {
            error.setText("Password must be at least 9 characters!");
        } else if (!password.getText().equals(password2.getText())) {
            error.setText("Passwords are not same!");
        } else {
            if (!user.writeToFile()) {
                error.setText("This username already registered!");
            } else {
                ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(new FXMLLoader(getClass().getResource("/view/login.fxml")).load()));
                Alert a = new Alert(Alert.AlertType.INFORMATION);
                a.setHeaderText("Account created successfully, now you can log in");
                a.show();
            }

        }
    }

}

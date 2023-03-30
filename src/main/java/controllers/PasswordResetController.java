package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.User;

import java.io.IOException;

public class PasswordResetController {
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private PasswordField password2;
    @FXML
    private Label error;
    @FXML
    private Label success;
    @FXML
    void viewLogIn(ActionEvent event) throws IOException {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(new FXMLLoader(getClass().getResource("/view/login.fxml")).load()));
    }

    @FXML
    void viewLogIn(MouseEvent event) throws IOException {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(new FXMLLoader(getClass().getResource("/view/login.fxml")).load()));
    }
    @FXML
    void resetPassword(ActionEvent event) throws IOException {
        User user = new User(username.getText(), password.getText());
        error.setVisible(true);
        if (!user.userExists()) {
            error.setText("This username not registered!");
        } else if (!user.validatePassword()) {
            error.setText("Password must be at least 9 characters!");
        } else if (!password.getText().equals(password2.getText())) {
            error.setText("Passwords are not same!");
        } else {
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(new FXMLLoader(getClass().getResource("/view/login.fxml")).load()));
            Alert a = new Alert(Alert.AlertType.INFORMATION);
            a.setHeaderText("Password changed successfully");
            a.show();
            user.changePassword();
        }
    }
}

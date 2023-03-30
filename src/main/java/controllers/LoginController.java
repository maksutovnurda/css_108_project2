package controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import utils.User;

import java.io.IOException;

public class LoginController {
    @FXML
    private TextField username;

    @FXML
    private PasswordField password;

    @FXML
    private CheckBox remember;

    @FXML
    private Label error;

    @FXML
    void viewSignUp(ActionEvent event) throws IOException {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(new FXMLLoader(getClass().getResource("/view/signup.fxml")).load()));
    }

    @FXML
    void logIn(ActionEvent event) throws IOException {
        User user = new User(username.getText(), password.getText());
        error.setVisible(true);
        if (!user.userCheck())
            error.setText("Incorrect username/password!");
        else {
            if (remember.isSelected()) user.remember();
            ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(new FXMLLoader(getClass().getResource("/view/news.fxml")).load()));
        }
    }

    @FXML
    void viewPasswordReset(MouseEvent event) throws IOException {
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(new FXMLLoader(getClass().getResource("/view/password_reset.fxml")).load()));
    }
}

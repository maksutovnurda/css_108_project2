package service;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import utils.User;

import java.io.FileInputStream;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        String view = (new User().isLoggedIn()) ? "/view/news.fxml" : "/view/login.fxml";
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(view));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Technological Development News");
        stage.getIcons().add(new Image(new FileInputStream("src/main/resources/images/icon.png")));
        stage.setScene(scene);
        stage.show();
    }
    public static void main(String[] args) {
        launch();
    }
}

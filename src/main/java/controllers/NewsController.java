package controllers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utils.User;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class NewsController {
    @FXML
    private FlowPane posts;


    @FXML
    public void logOut(ActionEvent event) throws IOException {
        new User().logOut();
        ((Stage) ((Node) event.getSource()).getScene().getWindow()).setScene(new Scene(new FXMLLoader(getClass().getResource("/view/login.fxml")).load()));
    }

    @FXML
    void openContact(ActionEvent event) throws IOException, URISyntaxException {
        java.awt.Desktop.getDesktop().browse(new URL("https://t.me/maksutovnurda").toURI());
    }

    @FXML
    void showPosts(ActionEvent event) throws IOException {
        String category = ((Node) event.getSource()).getId();

        List<String> imageLinks = Files.readAllLines(Paths.get("src/main/resources/databases/news.txt"));

        ImageView loadingAnimation = new ImageView(new Image(new FileInputStream("src/main/resources/images/loading.gif")));
        loadingAnimation.setFitHeight(50);
        loadingAnimation.setFitWidth(50);
        posts.setAlignment(Pos.CENTER);
        posts.getChildren().clear();
        posts.getChildren().add(loadingAnimation);

        Task<Void> task = new Task<>() {
            @Override
            protected Void call() throws Exception {
                for (String link : imageLinks) {
                    if (!link.startsWith(category))
                        continue;
                    else
                        link = link.substring(category.length()+1).strip();
                    VBox vBox = new VBox();
                    vBox.setAlignment(Pos.CENTER);
                    vBox.setPrefSize(145, 145);
                    vBox.getStyleClass().add("post_image");

                    Image image = new Image(link);
                    ImageView imageView = new ImageView(image);
                    imageView.setPreserveRatio(true);
                    imageView.setFitWidth(145);
                    imageView.setFitHeight(145);

                    Platform.runLater(() -> {
                        posts.setAlignment(Pos.TOP_LEFT);
                        posts.getChildren().remove(loadingAnimation);
                        vBox.getChildren().add(imageView);
                        posts.getChildren().add(vBox);
                    });
                }
                return null;
            }
        };

        new Thread(task).start();
    }

}

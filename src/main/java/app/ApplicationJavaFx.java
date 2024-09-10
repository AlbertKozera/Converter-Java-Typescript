package app;

import annotation.JavaFxApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lombok.extern.log4j.Log4j;
import service.UserConfigService;

import java.io.IOException;


@Log4j
@JavaFxApplication
public class ApplicationJavaFx extends Application {
    private static Stage primaryStage;
    private final UserConfigService userConfigService = new UserConfigService();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader(ApplicationJavaFx.class.getResource("/fxml/main.fxml"));
        AnchorPane anchorPane = fxmlLoader.load();
        Scene scene = new Scene(anchorPane);
        primaryStage.getIcons().add(new Image(ApplicationJavaFx.class.getResourceAsStream("/img/applicationLogo.png")));
        primaryStage.setTitle("Class converter");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.show();
        log.info("Application started");
        userConfigService.createConfigFileIfNotExists();
    }

    public static void switchScene(String fxml) throws IOException {
        Parent parent = FXMLLoader.load(ApplicationJavaFx.class.getResource("/fxml/" + fxml));
        primaryStage.getScene().setRoot(parent);
    }
}

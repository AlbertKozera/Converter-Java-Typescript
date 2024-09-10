package controller;

import app.ApplicationJavaFx;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import java.io.IOException;

public class MainController {

    public void switchToOption(ActionEvent actionEvent) throws IOException {
        Button button = (Button) actionEvent.getSource();
        ApplicationJavaFx.switchScene( button.getId() + ".fxml");
    }

    public void switchToGenerator(ActionEvent actionEvent) throws IOException {
        Button button = (Button) actionEvent.getSource();
        ApplicationJavaFx.switchScene( button.getId() + ".fxml");
    }

    public void closeApplication(){
        Platform.exit();
    }

}

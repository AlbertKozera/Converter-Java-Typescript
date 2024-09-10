package controller;

import app.ApplicationJavaFx;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import lombok.extern.log4j.Log4j;
import config.UserConfiguration;
import service.UserConfigService;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

@Log4j
public class OptionController {

    private final UserConfigService userConfigService = new UserConfigService();
    @FXML
    private TextField saveTo;
    @FXML
    private CheckBox threadEnabled;


    @FXML
    void initialize() {
        UserConfiguration userConfiguration = userConfigService.loadConfigFile();
        saveTo.setText(userConfiguration.getSaveTo());
        threadEnabled.setSelected(userConfiguration.isThreadEnabled());
    }

    public void choosePatchToStorageFiles() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        try {
            File file = directoryChooser.showDialog(null);
            if (Objects.nonNull(file)) {
                saveTo.setText(file.getAbsolutePath());
            }
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public void saveChanges() {
        userConfigService.saveConfigOption(getOptionProperties());
    }

    private UserConfiguration getOptionProperties() {
        return UserConfiguration.builder()
                .saveTo(saveTo.getText())
                .threadEnabled(threadEnabled.isSelected())
                .build();
    }

    public void switchToMain(ActionEvent actionEvent) throws IOException {
        Button button = (Button) actionEvent.getSource();
        ApplicationJavaFx.switchScene(button.getId() + ".fxml");
    }

}

package controller;

import app.ApplicationJavaFx;
import config.ListViewCell;
import converter.Language;
import javafx.beans.value.ChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import lombok.extern.log4j.Log4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import service.EnumInstanceService;
import service.GeneratorService;
import service.LoaderService;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.*;
import java.util.stream.Collectors;

@Log4j
public class GeneratorController {

    private final GeneratorService generatorService = new GeneratorService();
    private final EnumInstanceService enumInstanceService = new EnumInstanceService();
    private Collection<File> files;
    private Map<String, String> icons = new HashMap<>();
    private Map<String, String> extensions = new HashMap<>();
    private Language convertFrom;
    private Language convertTo;


    @FXML
    private ListView<File> leftListView;
    @FXML
    private ListView<File> rightListView;
    @FXML
    private ChoiceBox<String> leftLanguageChooser = new ChoiceBox<>();
    @FXML
    private ChoiceBox<String> rightLanguageChooser = new ChoiceBox<>();
    @FXML
    private ImageView leftImage;
    @FXML
    private ImageView rightImage;
    @FXML
    private Button uploadFileButton;

    @FXML
    void initialize() {
        fillConversionListChooser();
        extensions = LoaderService.loadExtension();
        icons = LoaderService.loadIcon();
        leftChoiceBoxListener();
        rightChoiceBoxListener();
        leftListView.setCellFactory(param -> new ListViewCell());
        rightListView.setCellFactory(param -> new ListCell<File>() {
            @Override
            protected void updateItem(File item, boolean empty) {
                super.updateItem(item, empty);
                setText(item == null ? null : item.getName());
            }
        });
    }

    private void fillConversionListChooser() {
        List<Language> enumValues = Arrays.asList(Language.values());
        List<String> languageName = getLanguage(enumValues);
        leftLanguageChooser.getItems().addAll(languageName);
    }

    private void leftChoiceBoxListener() {
        leftLanguageChooser.getSelectionModel()
                .selectedItemProperty()
                .addListener(inputListener());
    }

    private void rightChoiceBoxListener() {
        rightLanguageChooser.getSelectionModel()
                .selectedItemProperty()
                .addListener(outputListener());
    }

    private ChangeListener<String> inputListener() {
        return (observable, oldValue, newValue) -> {
            uploadFileButton.setVisible(true);
            convertFrom = Language.valueOf(newValue);
            setUpImage(leftImage, convertFrom.toString());

            rightLanguageChooser.getItems().clear();
            rightLanguageChooser.getItems().addAll(enumInstanceService.getListOfPossibleConversionType(newValue));
        };
    }

    private ChangeListener<String> outputListener() {
        return (observable, oldValue, newValue) -> {
            if (StringUtils.isBlank(newValue)) {
                setUpImage(rightImage, "BLANK");
                rightListView.getItems().clear();
            } else {
                convertTo = Language.valueOf(newValue);
                setUpImage(rightImage, convertTo.toString());
            }
        };
    }

    private void setUpImage(ImageView image, String fileName) {
        image.setImage(new Image(icons.get(fileName)));
    }

    public void uploadFile() {
        if (!Objects.isNull(convertFrom)) {
            FileChooser fileChooser = getFileChooser(convertFrom, extensions.get(convertFrom.toString()));
            files = fileChooser.showOpenMultipleDialog(null);
            leftListView.getItems().addAll(generatorService.checkFile(files));
        }
    }

    public void convertLeftToRight() throws IOException {
        FileUtils.deleteDirectory(new File(LoaderService.loadConfigFile().getSaveTo()));
        new File(LoaderService.loadConfigFile().getSaveTo()).mkdir();
        files = leftListView.getItems();
        if (Objects.isNull(files) || files.isEmpty() || Objects.isNull(convertTo)) {
            return;
        }
        convertFrom.convert(files, convertTo);
        rightListView.getItems().addAll(new File(LoaderService.loadConfigFile().getSaveTo()).listFiles());
        leftListView.getItems().clear();
    }

    public void clearLeftList() {
        leftListView.getItems().clear();
    }

    public void clearRightList() {
        rightListView.getItems().clear();
    }

    public void openFilesDirectory() {
        try {
            Desktop.getDesktop().open(new File(LoaderService.loadConfigFile().getSaveTo()));
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }

    private List<String> getLanguage(List<Language> enumValues) {
        return enumValues.stream()
                .map(Language::name)
                .collect(Collectors.toList());
    }

    private FileChooser getFileChooser(Language language, String extension) {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(language.toString().toLowerCase() + " file", extension);
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser;
    }

    public void switchToMain(ActionEvent actionEvent) throws IOException {
        Button button = (Button) actionEvent.getSource();
        ApplicationJavaFx.switchScene(button.getId() + ".fxml");
    }

}

package config;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

import java.io.File;

public class ListViewCell extends ListCell<File> {

    private HBox hbox = new HBox();
    private Label label = new Label("(empty)");
    private Pane pane = new Pane();
    private String style = getClass().getResource("/css/listView.css").toExternalForm();
    private Hyperlink hyperlink = new Hyperlink();
    private FontAwesomeIconView fontawesome = new FontAwesomeIconView(FontAwesomeIcon.TIMES);

    public ListViewCell() {
        super();
        hbox.getChildren().addAll(label, pane, hyperlink);
        HBox.setHgrow(pane, Priority.ALWAYS);
        hyperlink.setGraphic(fontawesome);
        hyperlink.setOnAction(event -> getListView().getItems().remove(getItem()));
        fontawesome.setSize("16");
        hyperlink.getStylesheets().add(style);
    }

    @Override
    protected void updateItem(File item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        setGraphic(null);

        if (item != null && !empty) {
            label.setText(item.getName());
            setGraphic(hbox);
        }
    }

}

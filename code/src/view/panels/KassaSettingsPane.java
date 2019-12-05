package view.panels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import model.domain.SaveEnum;

import java.util.List;

public class KassaSettingsPane extends GridPane {
    private ComboBox loadSaveBox;
    private Label loadSaveLabel;
    private List<String> loadSaves = SaveEnum.getSaveStrategies();
    private Button opslaan;

    public KassaSettingsPane() {
        this.setPadding(new Insets(5,5,5, 5));
        this.setVgap(5);
        this.setHgap(5);

        loadSaveLabel = new Label("Kies je in- en uitvoer type");
        ObservableList observableList;
        loadSaveBox = new ComboBox(FXCollections.observableArrayList(loadSaves));
        opslaan = new Button("Opslaan");
    }
}

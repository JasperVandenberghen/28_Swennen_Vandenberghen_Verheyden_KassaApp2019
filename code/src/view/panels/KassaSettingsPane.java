package view.panels;

import controller.SettingsController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.domain.Artikel;
import model.domain.ArtikelDbEnum;
import model.domain.SaveEnum;

import java.util.List;

public class KassaSettingsPane extends VBox {
    private ComboBox loadSaveBox;
    private ComboBox dbTypeBox;
    private Label loadSaveLabel;
    private Label dbTypeLabel;
    private List<String> loadSaves = SaveEnum.getSaveStrategies();
    private List<String> dbTypeList = ArtikelDbEnum.getSaveStrategies();
    private Button opslaan;

    public KassaSettingsPane(SettingsController settingsController) {
//        this.setPadding(new Insets(5,5,5, 5));
//        this.setVgap(5);
//        this.setHgap(5);
        settingsController.setView(this);
        loadSaveLabel = new Label("Kies je data opslag type");
        dbTypeLabel = new Label("Kies je databestandtype");
        loadSaveBox = new ComboBox(FXCollections.observableArrayList(loadSaves));
        dbTypeBox = new ComboBox(FXCollections.observableArrayList(dbTypeList));
        loadSaveBox.getSelectionModel().selectFirst();
        dbTypeBox.getSelectionModel().selectFirst();
        opslaan = new Button("Opslaan");
        this.getChildren().addAll( dbTypeLabel, dbTypeBox, loadSaveLabel,loadSaveBox,opslaan);

        opslaan.setOnAction(event -> {
            settingsController.setProperties(dbTypeBox.getValue().toString(), loadSaveBox.getValue().toString());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Successfully saved");

            alert.showAndWait();
        });
    }


}

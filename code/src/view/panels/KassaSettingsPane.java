package view.panels;

import controller.SettingsController;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.domain.ArtikelDbEnum;
import model.domain.KortingEnum;
import model.domain.SaveEnum;

import java.util.List;

public class KassaSettingsPane extends VBox {
    private ComboBox loadSaveBox;
    private ComboBox dbTypeBox;
    private ComboBox kortingTypeBox;
    private Label loadSaveLabel;
    private Label dbTypeLabel;
    private Label kortingTypeLabel;
    private Label kortingsAantalLabel;
    private TextField kortingsAantalField;
    private List<String> loadSaves = SaveEnum.getSaveStrategies();
    private List<String> dbTypeList = ArtikelDbEnum.getSaveStrategies();
    private List<String> kortingTypeList = KortingEnum.getKortingen();
    private Button opslaan;

    public KassaSettingsPane(SettingsController settingsController) {
//        this.setPadding(new Insets(5,5,5, 5));
//        this.setVgap(5);
//        this.setHgap(5);
        settingsController.setView(this);
        loadSaveLabel = new Label("Kies je data opslag type");
        dbTypeLabel = new Label("Kies je databestandtype");
        kortingTypeLabel = new Label("Kies je kortingtype");
        kortingsAantalLabel = new Label("Geef je kortingshoeveelheid in");
        kortingsAantalField = new TextField();
        kortingsAantalField.setTranslateX(5);
        kortingsAantalField.setMinWidth(1);
        loadSaveBox = new ComboBox(FXCollections.observableArrayList(loadSaves));
        dbTypeBox = new ComboBox(FXCollections.observableArrayList(dbTypeList));
        kortingTypeBox = new ComboBox(FXCollections.observableArrayList(kortingTypeList));
        loadSaveBox.getSelectionModel().selectFirst();
        dbTypeBox.getSelectionModel().selectFirst();
        kortingTypeBox.getSelectionModel().selectFirst();
        opslaan = new Button("Opslaan");
        this.getChildren().addAll( dbTypeLabel, dbTypeBox, loadSaveLabel,loadSaveBox, kortingTypeLabel, kortingTypeBox, kortingsAantalLabel, kortingsAantalField, opslaan);

        //setKortinsAantal moet nog gewijzigd worden op basis van de invoer van het textfield, maar dat moet ge is tonen hoe ge dat moet doen dan
        //er moet ook nog een alert gegeven worden wanneer kortingshoeveelheid niet is ingevuld
        opslaan.setOnAction(event -> {
            settingsController.setProperties(dbTypeBox.getValue().toString(), loadSaveBox.getValue().toString(), kortingTypeBox.getValue().toString());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText(null);
            alert.setContentText("Successfully saved");

            alert.showAndWait();
        });
    }


}

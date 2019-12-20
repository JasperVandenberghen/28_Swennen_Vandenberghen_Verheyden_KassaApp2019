package view.panels;

import controller.SettingsController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import model.domain.ArtikelDbEnum;
import model.domain.KortingEnum;
import model.domain.MessageHandler;
import model.domain.SaveEnum;
import view.PaneMethods;

import java.util.List;

public class SettingsPane extends GridPane {
    private ComboBox loadSaveBox;
    private ComboBox dbTypeBox;
    private ComboBox kortingTypeBox;
    private Label loadSaveLabel;
    private Label dbTypeLabel;
    private Label kortingTypeLabel;
    private Label kortingsAantalLabel;
    private Label drempelLabel;
    private Label categorieLabel;
    private Label titleKassaBonSettings;
    private CheckBox algemeneBoodschapHeaderBox;
    private Label boodschaplabelHeaderLabel;
    private Label boodschaplabelFooterLabel;
    private TextField algemeneBoodschapHeaderField;
    private TextField algemeneBoodschapFooterField;
    private CheckBox datumTijdHeaderBox;
    private CheckBox totalePrijsKortingFooterBox;
    private CheckBox prijsBtwFooterBox;
    private CheckBox algemeneFooterBox;
    private TextField kortingsAantalField;
    private TextField drempelAantal;
    private TextField categorie;
    private List<String> loadSaves = SaveEnum.getSaveStrategies();
    private List<String> dbTypeList = ArtikelDbEnum.getSaveStrategies();
    private List<String> kortingTypeList = KortingEnum.getKortingen();
    private Button opslaan;
    private String algemeneHeader;
    private String datumtijdHeader;
    private String prijsKortingFooter;
    private String prijsbtwFooter;
    private String algemeneboodschapFooter;


    public SettingsPane(SettingsController settingsController) {
        this.setPadding(new Insets(8,15,5, 5));
        this.setVgap(5);
        this.setHgap(15);
        settingsController.setView(this);
        loadSaveLabel = new Label("Kies je data opslag type");
        dbTypeLabel = new Label("Kies je databestandtype");
        kortingTypeLabel = new Label("Kies je kortingtype");
        kortingsAantalLabel = new Label("Geef je kortingshoeveelheid in (%)");
        drempelLabel = new Label("Voeg hier je drempel toe indien drempelkorting.");
        categorieLabel = new Label("Voeg hier je categorie toe indien groepskorting.");
        kortingsAantalField = new TextField("0");
        algemeneBoodschapHeaderBox = new CheckBox("Algemene boodschap toevoegen aan header");
        boodschaplabelHeaderLabel = new Label("Voeg hier je boodschap voor de header in");
        boodschaplabelFooterLabel = new Label("Voeg hier je boodschap voor de footer in");
        algemeneBoodschapHeaderField = new TextField();
        algemeneBoodschapFooterField = new TextField();
        datumTijdHeaderBox = new CheckBox("Datum & tijd toevoegen aan header");
        totalePrijsKortingFooterBox = new CheckBox("Voeg totale prijs + korting toe aan footer");
        prijsBtwFooterBox = new CheckBox("Voeg prijs met btw toe aan footer");
        algemeneFooterBox = new CheckBox("Voeg een algemene boodschap toe aan footer");
        titleKassaBonSettings = new Label("Kassabon");
        String style = "-fx-font-weight: bold; -fx-font-size:18;";
        titleKassaBonSettings.setStyle(style);
        drempelAantal = new TextField("0");
        categorie = new TextField();
        kortingsAantalField.setTranslateX(5);
        kortingsAantalField.setMinWidth(1);
        drempelAantal.setTranslateX(5);
        drempelAantal.setMinWidth(1);
        loadSaveBox = new ComboBox(FXCollections.observableArrayList(loadSaves));
        dbTypeBox = new ComboBox(FXCollections.observableArrayList(dbTypeList));
        kortingTypeBox = new ComboBox(FXCollections.observableArrayList(kortingTypeList));
        loadSaveBox.getSelectionModel().selectFirst();
        dbTypeBox.getSelectionModel().selectFirst();
        kortingTypeBox.getSelectionModel().selectFirst();
        opslaan = new Button("Alle settings opslaan");
        Node[] firstColumnNodes = {dbTypeLabel, dbTypeBox, loadSaveLabel,loadSaveBox, kortingTypeLabel, kortingTypeBox, drempelLabel, drempelAantal, categorieLabel, categorie, kortingsAantalLabel, kortingsAantalField, opslaan};
        Node[] secondColumnNodes = {titleKassaBonSettings, algemeneBoodschapHeaderBox, boodschaplabelHeaderLabel, algemeneBoodschapHeaderField, datumTijdHeaderBox, totalePrijsKortingFooterBox, prijsBtwFooterBox, algemeneFooterBox, boodschaplabelFooterLabel, algemeneBoodschapFooterField};
        PaneMethods.addToGridPaneAscendingRow(firstColumnNodes, this, 0);
        PaneMethods.addToGridPaneAscendingRow(secondColumnNodes, this, 1);



        /*voegKortingToe.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Korting toevoegen");
            alert.setHeaderText(null);
            if(!kortingsAantalField.getText().trim().isEmpty()){
                if(Double.parseDouble(kortingsAantalField.getText()) > 0 && (Double.parseDouble(kortingsAantalField.getText()) < 100)){
                    settingsController.addKorting(kortingTypeBox.getValue().toString(), kortingsAantalField.getText(), categorie.getText(), drempelAantal.getText());
                    alert.setContentText("Korting succesvol toegevoegd");}
                else{
                    alert.setContentText("Je getal moet tussen 0 en 100 liggen.");
                }
            }
            else {
                alert.setContentText("Je moet eerst een hoeveelheid korting ingeven.");
            }

            alert.showAndWait();
        });*/



        opslaan.setOnAction(event -> {
            if(algemeneBoodschapHeaderBox.isSelected()){algemeneHeader = "true";} else {algemeneHeader = "false";}
            if(datumTijdHeaderBox.isSelected()){datumtijdHeader = "true";} else {datumtijdHeader = "false";}
            if(totalePrijsKortingFooterBox.isSelected()){prijsKortingFooter = "true";} else {prijsKortingFooter = "false";}
            if(prijsBtwFooterBox.isSelected()){prijsbtwFooter = "true";} else {prijsbtwFooter = "false";}
            if(algemeneFooterBox.isSelected()){algemeneboodschapFooter = "true";} else {algemeneboodschapFooter = "false";}

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Korting toevoegen");
            alert.setHeaderText(null);
            if(!kortingsAantalField.getText().trim().isEmpty()){
                if(Double.parseDouble(kortingsAantalField.getText()) >= 0 && (Double.parseDouble(kortingsAantalField.getText()) <= 100)){
                    settingsController.addKorting(kortingTypeBox.getValue().toString(), kortingsAantalField.getText(), categorie.getText(), drempelAantal.getText());
                    alert.setContentText("Korting succesvol toegevoegd");}
                else{
                    alert.setContentText("Je getal moet tussen 0 en 100 liggen.");
                }
            }
            else {
                alert.setContentText("Je moet eerst een hoeveelheid korting ingeven.");
            }

            alert.showAndWait();
            settingsController.setProperties(dbTypeBox.getValue().toString(), loadSaveBox.getValue().toString(), kortingTypeBox.getValue().toString(),kortingsAantalField.getText(), categorie.getText(), drempelAantal.getText(), algemeneHeader, algemeneBoodschapHeaderField.getText(), datumtijdHeader, prijsKortingFooter, prijsbtwFooter, algemeneboodschapFooter, algemeneBoodschapFooterField.getText());

            MessageHandler.showAlert("Successfully saved");
        });

    }


}

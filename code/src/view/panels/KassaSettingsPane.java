package view.panels;

import controller.SettingsController;
import javafx.collections.FXCollections;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import model.domain.ArtikelDbEnum;
import model.domain.KortingEnum;
import model.domain.MessageHandler;
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
    private Label drempelLabel;
    private Label categorieLabel;
    private CheckBox algemeneBoodschapHeader;
    private CheckBox datumTijdHeader;
    private CheckBox totalePrijsKortingFooter;
    private CheckBox prijsBtwFooter;
    private CheckBox algemeneFooter;
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


    public KassaSettingsPane(SettingsController settingsController) {
//        this.setPadding(new Insets(5,5,5, 5));
//        this.setVgap(5);
//        this.setHgap(5);
        settingsController.setView(this);
        loadSaveLabel = new Label("Kies je data opslag type");
        dbTypeLabel = new Label("Kies je databestandtype");
        kortingTypeLabel = new Label("Kies je kortingtype");
        kortingsAantalLabel = new Label("Geef je kortingshoeveelheid in (%)");
        drempelLabel = new Label("Voeg hier je drempel toe indien drempelkorting.");
        categorieLabel = new Label("Voeg hier je categorie toe indien groepskorting.");
        kortingsAantalField = new TextField();
        algemeneBoodschapHeader = new CheckBox("Algemene boodschap toevoegen aan header");
        datumTijdHeader = new CheckBox("Datum & tijd toevoegen aan header");
        totalePrijsKortingFooter = new CheckBox("Voeg totale prijs + korting toe aan footer");
        prijsBtwFooter = new CheckBox("Voeg prijs met btw toe aan footer");
        algemeneFooter = new CheckBox("Voeg een algemene boodschap toe aan footer");
        drempelAantal = new TextField();
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
        opslaan = new Button("Opslaan");
        this.getChildren().addAll( dbTypeLabel, dbTypeBox, loadSaveLabel,loadSaveBox, kortingTypeLabel, kortingTypeBox, drempelLabel, drempelAantal, categorieLabel, categorie, kortingsAantalLabel, kortingsAantalField, algemeneBoodschapHeader, datumTijdHeader, totalePrijsKortingFooter, prijsBtwFooter, algemeneFooter, opslaan);



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
            if(algemeneBoodschapHeader.isSelected()){algemeneHeader = "true";} else {algemeneHeader = "false";}
            if(datumTijdHeader.isSelected()){datumtijdHeader = "true";} else {datumtijdHeader = "false";}
            if(totalePrijsKortingFooter.isSelected()){prijsKortingFooter = "true";} else {prijsKortingFooter = "false";}
            if(prijsBtwFooter.isSelected()){prijsbtwFooter = "true";} else {prijsbtwFooter = "false";}
            if(algemeneFooter.isSelected()){algemeneboodschapFooter = "true";} else {algemeneboodschapFooter = "false";}
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
            settingsController.setProperties(dbTypeBox.getValue().toString(), loadSaveBox.getValue().toString(), kortingTypeBox.getValue().toString(),kortingsAantalField.getText(), categorie.getText(), drempelAantal.getText(), algemeneHeader, datumtijdHeader, prijsKortingFooter, prijsbtwFooter, algemeneboodschapFooter);

            MessageHandler.showAlert("Successfully saved");
        });

    }


}

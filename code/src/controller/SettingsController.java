package controller;

import model.domain.PropertiesHandler;
import model.domain.Korting.KortingHandler;
import view.panels.SettingsPane;

import java.util.Properties;

public class SettingsController {
    private SettingsPane settingsPane;
    private PropertiesHandler propertiesHandler;
    private KortingHandler kortingHandler;


    public SettingsController(PropertiesHandler propertiesHandler, KortingHandler kortingHandler) {
        this.propertiesHandler = propertiesHandler;
        this.kortingHandler = kortingHandler;
    }

    public void setView(SettingsPane settingsPane){this.settingsPane = settingsPane;}

    public SettingsPane getSettingsPane() {
        return settingsPane;
    }

    public void setProperties(String dbType, String typeLoadSave, String typeKorting, String aantalKorting, String categorie, String drempel, String algemeneHeader, String algemeneBoodschapHeaderField, String datumTijdHeader, String prijsKortingFooter, String prijsBtwFooter, String algemeneFooter, String algemeneBoodschapFooterField){
        Properties properties = new Properties();
        properties.setProperty("dbType", dbType);
        properties.setProperty("typeLoadSave", typeLoadSave);
        properties.setProperty("typeKorting", typeKorting);
        properties.setProperty("aantalKorting", aantalKorting);
        properties.setProperty("drempelKorting", drempel);
        properties.setProperty("categorieKorting", categorie);
        properties.setProperty("algemeneHeader", algemeneHeader);
        properties.setProperty("algemeneHeaderField", algemeneBoodschapHeaderField);
        properties.setProperty("datumTijdHeader", datumTijdHeader);
        properties.setProperty("prijsKortingFooter", prijsKortingFooter);
        properties.setProperty("prijsBtwFooter", prijsBtwFooter);
        properties.setProperty("algemeneFooter", algemeneFooter);
        properties.setProperty("algemeneBoodschapFooterField", algemeneBoodschapFooterField);
        this.propertiesHandler.write(properties);
    }
    public void addKorting(String kortingStr, String hoeveelheid, String categorie, String drempel){
        kortingHandler.addKorting(kortingStr, hoeveelheid, categorie, drempel);
    }

    public KortingHandler getKortingHandler() {
        return kortingHandler;
    }
}

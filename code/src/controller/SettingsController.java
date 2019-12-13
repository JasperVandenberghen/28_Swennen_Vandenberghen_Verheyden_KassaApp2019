package controller;

import model.db.PropertiesHandler;
import model.domain.KortingHandler;
import view.panels.KassaSettingsPane;

import java.util.Properties;

public class SettingsController {
    private KassaSettingsPane kassaSettingsPane;
    private PropertiesHandler propertiesHandler;
    private KortingHandler kortingHandler;


    public SettingsController(PropertiesHandler propertiesHandler, KortingHandler kortingHandler) {
        this.propertiesHandler = propertiesHandler;
        this.kortingHandler = kortingHandler;
    }

    public void setView(KassaSettingsPane kassaSettingsPane){this.kassaSettingsPane = kassaSettingsPane;}

    public KassaSettingsPane getKassaSettingsPane() {
        return kassaSettingsPane;
    }

    public void setProperties(String dbType, String typeLoadSave, String typeKorting, String aantalKorting, String categorie, String drempel ){
        Properties properties = new Properties();
        properties.setProperty("dbType", dbType);
        properties.setProperty("typeLoadSave", typeLoadSave);
        properties.setProperty("typeKorting", typeKorting);
        properties.setProperty("aantalKorting", aantalKorting);
        properties.setProperty("drempelKorting", drempel);
        properties.setProperty("categorieKorting", categorie);
        this.propertiesHandler.write(properties);
    }
    public void addKorting(String kortingStr, String hoeveelheid, String categorie, String drempel){
        kortingHandler.addKorting(kortingStr, hoeveelheid, categorie, drempel);
    }

    public KortingHandler getKortingHandler() {
        return kortingHandler;
    }
}

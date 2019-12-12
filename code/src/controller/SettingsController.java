package controller;

import model.db.PropertiesHandler;
import model.domain.KortingHandler;
import model.domain.KortingsFactory;
import model.domain.KortingsStrategy;
import model.domain.Verkoop;
import view.panels.KassaSettingsPane;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

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

    public void setProperties(String dbType, String typeLoadSave){
        Properties properties = new Properties();
        properties.setProperty("dbType", dbType);
        properties.setProperty("typeLoadSave", typeLoadSave);
        this.propertiesHandler.write(properties);
    }
    public void addKorting(String kortingStr, String hoeveelheid, String categorie, String drempel){
        kortingHandler.addKorting(kortingStr, hoeveelheid, categorie, drempel);
    }

}

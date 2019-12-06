package controller;

import model.db.PropertiesHandler;
import view.panels.KassaSettingsPane;

import java.util.Properties;

public class SettingsController {
    private KassaSettingsPane kassaSettingsPane;
    private PropertiesHandler propertiesHandler;

    public SettingsController(PropertiesHandler propertiesHandler) {
        this.propertiesHandler = propertiesHandler;
    }

    public void setView(KassaSettingsPane kassaSettingsPane){this.kassaSettingsPane = kassaSettingsPane;}

    public KassaSettingsPane getKassaSettingsPane() {
        return kassaSettingsPane;
    }

    public void setProperties(String dbType, String typeLoadSave, String typeKorting){
        Properties properties = new Properties();
        properties.setProperty("dbType", dbType);
        properties.setProperty("typeLoadSave", typeLoadSave);
        properties.setProperty("typeKorting", typeKorting);
        this.propertiesHandler.write(properties);
    }


}

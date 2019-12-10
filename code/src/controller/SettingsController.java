package controller;

import model.db.PropertiesHandler;
import model.domain.KortingsFactory;
import model.domain.KortingsStrategy;
import view.panels.KassaSettingsPane;

import java.util.HashSet;
import java.util.Properties;
import java.util.Set;

public class SettingsController {
    private KassaSettingsPane kassaSettingsPane;
    private PropertiesHandler propertiesHandler;
    private Set<KortingsStrategy> kortingen = new HashSet<>();

    public SettingsController(PropertiesHandler propertiesHandler) {
        this.propertiesHandler = propertiesHandler;
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
        if(kortingStr.trim().isEmpty() || hoeveelheid.trim().isEmpty()){throw new IllegalArgumentException("Voeg een korting of hoeveelheid toe.");}
        KortingsStrategy korting = new KortingsFactory().getKorting(kortingStr);
        korting.setKorting(hoeveelheid);
        kortingen.add(korting);
        for(KortingsStrategy ks: kortingen){
            if(ks.getOmschrijving().equals("Groepskorting")){
                ks.setCategorie(categorie);
            }
            if(ks.getOmschrijving().equals("Drempelkorting")){
                ks.setDrempel(drempel);
            }
        }
    }

    public Set<KortingsStrategy> getKortingen() {
        return kortingen;
    }

    public void pasKortingenToe(){
        kortingen = getKortingen();
        if(kortingen == null){throw new IllegalArgumentException("Voeg kortingen toe.");}
        for(KortingsStrategy ks: kortingen){
            ks.setTotaalMetKortingKassier();
        }
    }
}

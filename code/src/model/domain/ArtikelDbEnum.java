package model.domain;

import java.util.ArrayList;
import java.util.List;

public enum ArtikelDbEnum {
    INMEMORY ("InMemory","db.ArtikelDbInMemory");

    private final String omschrijving;
    private final String klasseNaam;

    ArtikelDbEnum(String omschrijving, String klasseNaam) {
        this.omschrijving = omschrijving;
        this.klasseNaam = klasseNaam;
    }

    public String getOmschrijving() { return omschrijving; }
    public String getKlasseNaam() { return klasseNaam; }

    public static List<String> getSaveStrategies(){
        List<String> result = new ArrayList<>();
        for (ArtikelDbEnum save : ArtikelDbEnum.values()){
            result.add(save.omschrijving);
        }
        return result;
    }
    
    public static ArtikelDbEnum getSave(String omschrijving){
        for (ArtikelDbEnum save : ArtikelDbEnum.values()){
            if (save.omschrijving.equals(omschrijving)){
                return save;
            }
        }
        throw new DomainException("Er is geen save met omschrijving \"" + omschrijving + "\".");
    }
}

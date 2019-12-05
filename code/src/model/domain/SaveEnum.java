package model.domain;

import java.util.ArrayList;
import java.util.List;

public enum SaveEnum {
    EXCEL ("Excel", "excel.ArtikelExcelLoadSaveStrategy"),
    TXT ("Text", "model.db.ArtikelTekstLoadSave");


    private final String omschrijving;
    private final String klasseNaam;

    SaveEnum(String omschrijving, String klasseNaam) {
        this.omschrijving = omschrijving;
        this.klasseNaam = klasseNaam;
    }

    public String getOmschrijving() { return omschrijving; }
    public String getKlasseNaam() { return klasseNaam; }

    public static List<String> getSaveStrategies(){
        List<String> result = new ArrayList<>();
        for (SaveEnum save : SaveEnum.values()){
            result.add(save.omschrijving);
        }
        return result;
    }
    
    public static SaveEnum getSave(String omschrijving){
        for (SaveEnum save : SaveEnum.values()){
            if (save.omschrijving.equals(omschrijving)){
                return save;
            }
        }
        throw new DomainException("Er is geen save met omschrijving \"" + omschrijving + "\".");
    }
}

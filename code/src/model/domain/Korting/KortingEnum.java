package model.domain.Korting;

import model.domain.DomainException;

import java.util.ArrayList;
import java.util.List;

public enum KortingEnum {

    BASIS ("Geen korting", "model.domain.Korting.GeenKorting"),
    GROEP ("Groepskorting", "model.domain.Korting.GroepsKorting"),
    DREMPEL ("Drempelkorting", "model.domain.Korting.DrempelKorting"),
    DUURSTE ("Duurstekorting", "model.domain.Korting.DuursteKorting");


    private final String omschrijving;
    private final String klasseNaam;

    KortingEnum(String omschrijving, String klasseNaam) {
        this.omschrijving = omschrijving;
        this.klasseNaam = klasseNaam;
    }

    public String getOmschrijving() { return omschrijving; }
    public String getKlasseNaam() { return klasseNaam; }

    public static List<String> getKortingen(){
        List<String> result = new ArrayList<>();
        for (KortingEnum korting : KortingEnum.values()){
            result.add(korting.omschrijving);
        }
        return result;
    }

    public static KortingEnum getKorting(String omschrijving){
        for (KortingEnum korting : KortingEnum.values()){
            if (korting.omschrijving.equals(omschrijving)){
                return korting;
            }
        }
        throw new DomainException("Er is geen korting met omschrijving \"" + omschrijving + "\".");
    }
}

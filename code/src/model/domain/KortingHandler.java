package model.domain;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.panels.KassaKassierPane;

import java.text.DecimalFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class KortingHandler {
    private KassaKassierPane kassaKassierPane;
    private Set<KortingsStrategy> kortingen = new HashSet<>();
    private List<ArtikelContainer> artikelContainers;

    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

    }

    public void addKorting(String kortingStr, String hoeveelheid, String categorie, String drempel){
        if(kortingStr.trim().isEmpty() || hoeveelheid.trim().isEmpty()){throw new IllegalArgumentException("Voeg een korting of hoeveelheid toe.");}
        KortingsStrategy korting = new KortingsFactory().getKorting(kortingStr);
        korting.setKorting(hoeveelheid);
        kortingen.add(korting);
        if(korting.getOmschrijving().equals("Groepskorting")){
            korting.setCategorie(categorie);
        }
        if(korting.getOmschrijving().equals("Drempelkorting")){
            korting.setDrempel(drempel);
        }
    }

    public void setArtikelContainers(List<ArtikelContainer> artikelContainers) {
        List<ArtikelContainer> artikelContainers_new = FXCollections.observableArrayList();
        for (ArtikelContainer artikelContainer: artikelContainers){
            artikelContainers_new.add(artikelContainer.clone());
        }
        this.artikelContainers = artikelContainers_new;
    }

    public Set<KortingsStrategy> getKortingen() {
        return kortingen;
    }

    public double getNewTotaalNaKorting(){
        if(kortingen == null){throw new IllegalArgumentException("Voeg kortingen toe.");}
        for(KortingsStrategy ks: kortingen){
            ks.setArtikelContainers(artikelContainers);
            ks.setTotaalMetKortingKassier();
        }
        return calculateTotaal(artikelContainers);
    }

    public static double calculateTotaal(List<ArtikelContainer> artikelContainers){
        double totaalNakorting = 0;
        for(int i = 0; i != artikelContainers.size(); i++){
            ArtikelContainer ac = artikelContainers.get(i);
            totaalNakorting += ac.getPrijs() * ac.getAantal();
        }
        return totaalNakorting;
    }
}

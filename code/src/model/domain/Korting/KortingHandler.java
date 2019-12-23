package model.domain.Korting;

import javafx.collections.FXCollections;
import model.domain.ArtikelContainer;
import model.domain.DomainException;
import view.panels.KassaKassierPane;

import java.util.ArrayList;
import java.util.List;

public class KortingHandler {
    private KassaKassierPane kassaKassierPane;
    private List<KortingsStrategy> kortingen = new ArrayList<KortingsStrategy>();
    private List<ArtikelContainer> artikelContainers;

    public void addKorting(String kortingStr, String hoeveelheid, String categorie, String drempel){
        if(kortingStr.trim().isEmpty() || hoeveelheid.trim().isEmpty()){throw new IllegalArgumentException("Voeg een korting of hoeveelheid toe.");}
        KortingsStrategy korting = new KortingsFactory().getKorting(kortingStr);
        korting.setKorting(hoeveelheid);

        for(int i = 0; i != kortingen.size(); i++){
            KortingsStrategy ks = kortingen.get(i);
            if(ks.getOmschrijving().equals(korting.getOmschrijving())){
                throw new DomainException("Korting is al opgenomen.");
            }
        }
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

    public List<KortingsStrategy> getKortingen() {
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

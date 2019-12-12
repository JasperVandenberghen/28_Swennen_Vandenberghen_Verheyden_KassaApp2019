package model.domain;

import java.util.List;

public class DuursteKorting extends Korting  {
    private static final String omschrijving = "Duurstekorting";


    public DuursteKorting() {
        
    }

    @Override
    public void setTotaalMetKortingKassier() {
        //artikelen in kassa kkassier ophalen
        double duurst = 0;
        String artikelIdDuurst = "";
        //loopen over artikelen
        for(int i= 0; i != artikelContainers.size(); i++){
            ArtikelContainer ac = artikelContainers.get(i);
            if(ac.getPrijs() > duurst){
                duurst = ac.getPrijs();
                artikelIdDuurst = ac.getArtikelId();
            }
        }
        for(int i= 0; i != artikelContainers.size(); i++){
            ArtikelContainer ac = artikelContainers.get(i);
            if(ac.getArtikelId().equals(artikelIdDuurst)){
                ac.setPrijs(ac.getPrijs() * convertKorting(kortingsAantal));
                artikelContainers.set(i, ac);
            }
        }
    }


    @Override
    public String getOmschrijving() {
        return omschrijving;
    }

    @Override
    public void setCategorie(String categorie) {

    }

    @Override
    public void setDrempel(String drempel) {

    }

    public void setKortingsAantal(String kortingsAantal){
        this.kortingsAantal = Integer.parseInt(kortingsAantal);
    }

}

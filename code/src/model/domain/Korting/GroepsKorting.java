package model.domain.Korting;

import model.domain.ArtikelContainer;

public class GroepsKorting extends Korting{
    private String categorie;
    private static final String omschrijving = "Groepskorting";

    public GroepsKorting() {

    }

    public void setTotaalMetKortingKassier() {
        //loopen door artikelen
        double multiplier = convertKorting(kortingsAantal);
        for(int i = 0;i<artikelContainers.size();i++){
            ArtikelContainer ac = artikelContainers.get(i);
            //als categorie gelijk pas de prijs aan
            if(ac.getArtikelCategorie().equalsIgnoreCase(getCategorie())){
                double prijs = ac.getPrijs();
                //opgehaalde prijs veranderen (bv bij 70% korting, prijs * 0.3)
                ac.setPrijs(prijs * multiplier);
                artikelContainers.set(i, ac);
            }

        }

    }



    @Override
    public String getOmschrijving() {
        return omschrijving;
    }




    public void setDrempel() {

    }


    public void setKortingsAantal(String kortingsAantal){
        this.kortingsAantal = Integer.parseInt(kortingsAantal);
    }

    public String getCategorie() {
        return categorie;
    }

    //setter voor instellen op welke categorie er korting is
    @Override
    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    @Override
    public void setDrempel(String drempel) {

    }
}

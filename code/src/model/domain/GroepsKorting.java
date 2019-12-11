package model.domain;

import java.util.List;

public class GroepsKorting extends Korting{
    private String categorie;
    private static final String omschrijving = "Groepskorting";

    public GroepsKorting() {


    }

    public Verkoop getVerkoop() {
        return verkoop;
    }

    public void setTotaalMetKortingKassier() {
        //verkoop opvragen
        Verkoop verkoop = getVerkoop();
        //artikelen in kassa kassier ophalen
        List<ArtikelContainer> artikelenInKassaKassier = verkoop.getArtikelenInKassaKassier();
        //loopen door artikelen
        double multiplier = convertKorting(kortingsAantal);
        for(int i = 0;i<artikelenInKassaKassier.size();i++){
            ArtikelContainer ac = artikelenInKassaKassier.get(i);
            //als categorie gelijk pas de prijs aan
            if(ac.getArtikelCategorie().equals(getCategorie())){
                double prijs = ac.getPrijs();
                //opgehaalde prijs veranderen (bv bij 70% korting, prijs * 0.7)
                ac.setPrijs(prijs * multiplier);
                artikelenInKassaKassier.set(i, ac);

            }

        }

    }

    public void setVerkoop(Verkoop verkoop) {
        this.verkoop = verkoop;
    }



    @Override
    public void setKorting(String kortingsAantal) {
        this.kortingsAantal = Integer.parseInt(kortingsAantal);
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

    public int getKortingsAantal() {
        return kortingsAantal;
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

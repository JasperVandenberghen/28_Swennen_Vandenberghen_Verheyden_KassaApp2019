package model.domain.Korting;

import model.domain.ArtikelContainer;

import java.util.List;

abstract class Korting implements KortingsStrategy {

    protected double totaal;
    protected double kortingsAantal;
    protected List<ArtikelContainer> artikelContainers;


    public Korting(){

    }

    public void setArtikelContainers(List<ArtikelContainer> artikelContainers) {
        this.artikelContainers = artikelContainers;
    }

    public double getKortingsAantal() {
        return kortingsAantal;
    }


    public void setKortingsAantal(String kortingsAantal){
        this.kortingsAantal = Integer.parseInt(kortingsAantal);
    }

    public double getTotaal() {
        return totaal;
    }

    public void setTotaal(double totaal) {
        this.totaal = totaal;
    }

    public abstract void setTotaalMetKortingKassier();


    //zet korting bv 70(%) om naar 0.3
    public double convertKorting(double kortingsAantal) {
        if(kortingsAantal != 0){
            // 1 has to be converted to double
            return (100 -  kortingsAantal) / 100 ;}
        else{
            return 0;
        }
    }

    @Override
    public void setKorting(String kortingsAantal) {
        this.kortingsAantal = Double.parseDouble(kortingsAantal);
    }

}

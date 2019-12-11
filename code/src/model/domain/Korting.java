package model.domain;

abstract class Korting implements KortingsStrategy {

    private double totaal;
    private int kortingsAantal;
    private Verkoop verkoop;




    public Korting(){

    }

    public int getKortingsAantal() {
        return kortingsAantal;
    }


    public void setVerkoop(Verkoop verkoop) {
        this.verkoop = verkoop;
    }

    public Verkoop getVerkoop() {
        return verkoop;
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


    public abstract void setTotaalMetKortingKlant();

    //zet korting bv 70(%) om naar 0.3
    public double convertKorting(int kortingsAantal){
        if(kortingsAantal != 0){
            return (100 - kortingsAantal) / 100 ;}
        else{
            return 0;
        }
    }

}

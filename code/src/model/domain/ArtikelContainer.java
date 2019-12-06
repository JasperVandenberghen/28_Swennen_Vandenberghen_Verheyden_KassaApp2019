package model.domain;

public class ArtikelContainer {
    private int aantal;
    private Artikel artikel;

    public ArtikelContainer(Artikel artikel) {
        this.artikel = artikel;
        this.aantal = 1;
    }

    public void verhoogAantal(){
        this.aantal++;
    }
    public void verlaagAantal(){
        this.aantal--;
    }

    public double getTotaalPrijs() {
        return aantal * artikel.getPrijs();
    }


    public int getAantal() {
        return aantal;
    }

    public double getPrijs(){
        return artikel.getPrijs();
    }
    public String getArtikelNaam(){
        return artikel.getArtikelNaam();
    }

    public String getArtikelId(){
        return artikel.getArtikelId();
    }

    public boolean equals(Object o) {
        return this.artikel.equals(o);
    }
}

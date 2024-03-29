package model.domain;

import java.util.Objects;

public class ArtikelContainer implements Cloneable{
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

    public String getArtikelCategorie(){return artikel.getCategorie();}

    public void setPrijs(double prijs){
        artikel.setPrijs(prijs);
    }

    public String getArtikelId(){
        return artikel.getArtikelId();
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ArtikelContainer artikel = (ArtikelContainer) o;
        return Objects.equals(this.artikel.getArtikelId(), artikel.getArtikelId());
    }

    public ArtikelContainer clone()
    {
        ArtikelContainer artikelContainer = new ArtikelContainer(artikel.clone());
        artikelContainer.setAantal(aantal);
        return artikelContainer;
    }

    public void setAantal(int aantal) {
        this.aantal = aantal;
    }
}

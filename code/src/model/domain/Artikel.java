package model.domain;

import java.util.Objects;

public class Artikel implements Cloneable{
    private String artikelId;
    private String artikelNaam;
    private String categorie;
    private double prijs;
    private int voorraad;

    public Artikel(String artikelId, String artikelNaam,String categorie, double prijs, int voorraad) {
        setArtikelId(artikelId);
        setArtikelNaam(artikelNaam);
        setCategorie(categorie);
        setPrijs(prijs);
        setVoorraad(voorraad);
    }

    public String getArtikelId() {
        return artikelId;
    }

    private void setArtikelId(String artikelId) {
        this.artikelId = artikelId;
    }

    public String getArtikelNaam() {
        return artikelNaam;
    }

    private void setArtikelNaam(String artikelNaam) {
        this.artikelNaam = artikelNaam;
    }

    public String getCategorie() {
        return categorie;
    }

    private void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public double getPrijs() {
        return prijs;
    }

    protected void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public int getVoorraad() {
        return voorraad;
    }

    private void setVoorraad(int voorraad) {
        this.voorraad = voorraad;
    }

    public void decreaseVoorraad(int aantal){
        voorraad-=aantal;
    }

    @Override
    public String toString() {
        return artikelId + "," + artikelNaam + "," + categorie + "," + prijs + "," + voorraad;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Artikel artikel = (Artikel) o;
        return Objects.equals(artikelId, artikel.artikelId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(artikelId);
    }

    public Artikel clone() {
        Artikel artikel = null;
        try {
            artikel = (Artikel) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new DomainException("Clone not supported");
        }
        return artikel;
    }

}

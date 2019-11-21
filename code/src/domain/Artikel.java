package domain;

public class Artikel {
    private String artikelId;
    private String artikelNaam;
    private String categorie;
    private double prijs;
    private int voorraad;

    public Artikel(String artikelId, String artikelNaam,String categorie, double prijs, int voorraad) {

    }

    public String getArtikelId() {
        return artikelId;
    }

    public void setArtikelId(String artikelId) {
        this.artikelId = artikelId;
    }

    public String getArtikelNaam() {
        return artikelNaam;
    }

    public void setArtikelNaam(String artikelNaam) {
        this.artikelNaam = artikelNaam;
    }

    public double getPrijs() {
        return prijs;
    }

    public void setPrijs(double prijs) {
        this.prijs = prijs;
    }

    public int getVoorraad() {
        return voorraad;
    }

    public void setVoorraad(int voorraad) {
        this.voorraad = voorraad;
    }

    @Override
    public String toString() {
        return artikelId + "," + artikelNaam + "," + categorie + "," + prijs + "," + voorraad;
    }
}

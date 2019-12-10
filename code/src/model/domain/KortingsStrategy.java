package model.domain;

public interface KortingsStrategy {

    void setTotaalMetKortingKassier();
    double convertKorting(int kortingsAantal);
    void setKorting(String hoeveelheid);
    String getOmschrijving();
    void setCategorie(String categorie);
    void setDrempel(String drempel);
    void setVerkoop(Verkoop verkoop);
}

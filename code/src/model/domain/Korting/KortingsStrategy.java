package model.domain.Korting;

import model.domain.ArtikelContainer;

import java.util.List;

public interface KortingsStrategy {

    void setTotaalMetKortingKassier();
    double convertKorting(double kortingsAantal);
    void setKorting(String hoeveelheid);
    String getOmschrijving();
    void setCategorie(String categorie);
    void setDrempel(String drempel);
    void setArtikelContainers(List<ArtikelContainer> artikelContainers);
}

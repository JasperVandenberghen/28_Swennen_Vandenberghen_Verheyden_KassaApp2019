package model.domain;

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

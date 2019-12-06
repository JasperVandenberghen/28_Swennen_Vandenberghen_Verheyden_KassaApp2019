package model.domain;

public interface KortingsStrategy {

    void setTotaalMetKortingKassier();
    void setTotaalMetKortingKlant();
    double convertKorting(int kortingsAantal);
}

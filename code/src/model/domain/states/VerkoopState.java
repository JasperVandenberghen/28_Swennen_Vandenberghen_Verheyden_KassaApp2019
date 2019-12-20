package model.domain.states;

import javafx.scene.control.Button;
import model.domain.ArtikelContainer;

import java.util.List;

public interface VerkoopState {
    public void addArtikel(String artikelId);
    public void removeArtikel(List<Integer> indeces);
    public void beeindigen();
    public void onHoldFunction();
    public void annuleren();
}

package model.domain.states;

import javafx.scene.control.Button;
import model.domain.ArtikelContainer;

public interface VerkoopState {
    public void addArtikel(String artikelId);
    public void afrekenen();
    public void onHoldFunction(Button button);
    public void annuleren();
    public void betalen();
}

package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.db.ConversionToObjectList;
import model.domain.Artikel;
import model.domain.ArtikelContainer;
import model.domain.ArtikelData;
import model.domain.Observer;
import view.panels.KassaKlantPane;

import java.util.List;
import java.util.Map;

public class KassaKlantController implements Observer {
    private Map<String, Artikel> artikelMap;
    private List<ArtikelContainer> artikelenInKassa;
    private KassaKlantPane kassaKlantPane;
    private double totaal;

    public KassaKlantController(Map<String, Artikel> artikelMap, ArtikelData artikelData) {
        this.artikelMap = artikelMap;
        artikelenInKassa = FXCollections.observableArrayList();
        artikelData.registerObserver(this);
    }

    public void setView(KassaKlantPane kassaKlantPane) {
        this.kassaKlantPane = kassaKlantPane;
        this.kassaKlantPane.setObservableList(artikelenInKassa);
    }

    @Override
    public void update(String artikelId) {
        ArtikelContainer artikelGevondenInKassa = null;
        int index = 0;
        for(ArtikelContainer artikelContainer:artikelenInKassa){
            if(artikelContainer.getArtikelId().equalsIgnoreCase(artikelId)){
                artikelGevondenInKassa = artikelContainer;
                break;
            }
            index++;
        }
        if(artikelGevondenInKassa==null){
            ArtikelContainer artikelContainer = new ArtikelContainer(artikelMap.get(artikelId));
            artikelenInKassa.add(artikelContainer);
            totaal += artikelContainer.getPrijs();
        } else{
            artikelGevondenInKassa.verhoogAantal();
            artikelenInKassa.set(index, artikelGevondenInKassa);
            totaal += artikelGevondenInKassa.getPrijs();
        }
        this.kassaKlantPane.setTotaal(totaal);
    }

    public KassaKlantPane getKassaKlantPane() {
        return kassaKlantPane;
    }
}

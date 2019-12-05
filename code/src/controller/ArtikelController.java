package controller;

import javafx.collections.FXCollections;
import model.domain.Artikel;
import view.panels.ProductOverviewPane;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ArtikelController {
    private ProductOverviewPane productOverviewPane;
    private Map<String, Artikel> artikelen;

    public void setView(ProductOverviewPane productOverviewPane){
        this.productOverviewPane = productOverviewPane;
    }

    public ArtikelController(Map<String, Artikel> artikelen) {
        this.artikelen = artikelen;
    }

    public void setArtikelen(Map<String, Artikel> artikelen) {
        this.artikelen = artikelen;
    }

    public void setArtikelenInView(){
        List<Artikel> observableList = FXCollections.observableArrayList();
        Iterator it = artikelen.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry thisEntry = (Map.Entry) it.next();
            Artikel value = (Artikel) thisEntry.getValue();
            observableList.add(value);
        }
        productOverviewPane.setObservableList(observableList);
    }


}

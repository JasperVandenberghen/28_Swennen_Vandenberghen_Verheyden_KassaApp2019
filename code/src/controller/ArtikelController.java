package controller;

import javafx.collections.FXCollections;
import model.domain.Artikel;
import model.domain.ObserverArtikelenInShop;
import model.domain.Verkoop;
import view.panels.ProductOverviewPane;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ArtikelController implements ObserverArtikelenInShop {
    private ProductOverviewPane productOverviewPane;
    private Verkoop verkoop;

    public void setView(ProductOverviewPane productOverviewPane){
        this.productOverviewPane = productOverviewPane;
    }

    public ProductOverviewPane getProductOverviewPane(){
        return productOverviewPane;
    }

    public ArtikelController(Verkoop verkoop) {
        verkoop.registerObserver(this);
    }

    public void setArtikelenInView(List<Artikel> artikels){
        productOverviewPane.setObservableList(artikels);
    }


    @Override
    public void update(List<Artikel> artikels) {
        setArtikelenInView(artikels);
    }
}

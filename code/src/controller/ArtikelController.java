package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.domain.Artikel;
import view.panels.ProductOverviewPane;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ArtikelController {
    private ProductOverviewPane productOverviewPane;

    public ArtikelController(ProductOverviewPane kassaMainPane) {
        this.productOverviewPane = kassaMainPane;
        Map<String, Artikel> artikelen = new HashMap<>();
        Artikel a = new Artikel("3","choco","Belg",3,3);
        artikelen.put(a.getArtikelId(), a);
        List<Artikel> observableList = FXCollections.observableArrayList();
        Iterator it = artikelen.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry thisEntry = (Map.Entry) it.next();
            Artikel value = (Artikel) thisEntry.getValue();
            observableList.add(value);
        }
        TableView<Artikel> table = kassaMainPane.getTable();
        table.setItems((ObservableList<Artikel>) observableList);
        TableColumn<Artikel, String> colArtikelID = new TableColumn<Artikel, String>("ArtikelID");
        colArtikelID.setMinWidth(300);
        colArtikelID.setCellValueFactory(new PropertyValueFactory<Artikel, String>("ArtikelID"));
        TableColumn<Artikel, String> colArtikelNaam = new TableColumn<Artikel, String>("Artikel Naam");
        colArtikelNaam.setMinWidth(300);
        colArtikelNaam.setCellValueFactory(new PropertyValueFactory<Artikel, String>("Artikel Naam"));
        TableColumn<Artikel, String> colCat = new TableColumn<Artikel, String>("Categorie");
        colCat.setMinWidth(300);
        colCat.setCellValueFactory(new PropertyValueFactory<Artikel, String>("Categorie"));
        TableColumn<Artikel, String> colPrijs = new TableColumn<Artikel, String>("Prijs");
        colPrijs.setMinWidth(40);
        colPrijs.setCellValueFactory(new PropertyValueFactory<Artikel, String>("Prijs"));
        TableColumn<Artikel, String> colVoorraad = new TableColumn<Artikel, String>("Voorraad");
        colVoorraad.setMinWidth(40);
        colVoorraad.setCellValueFactory(new PropertyValueFactory<Artikel, String>("Voorraad"));
        table.getColumns().addAll(colArtikelID, colArtikelNaam, colCat, colPrijs, colVoorraad);
    }


}
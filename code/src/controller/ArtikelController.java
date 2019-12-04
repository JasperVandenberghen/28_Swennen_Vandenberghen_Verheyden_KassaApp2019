package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import model.domain.Artikel;
import view.panels.ProductOverviewPane;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ArtikelController {

    private ProductOverviewPane kassaMainPane = new ProductOverviewPane();

    public void setKassaMainPane(ProductOverviewPane kassaMainPane) {
        this.kassaMainPane = kassaMainPane;
    }

    public ArtikelController() {

        Map<String, Artikel> artikelen = new HashMap<>();
        Artikel a = new Artikel("3","choco","Belg",3,3);
        artikelen.put(a.getArtikelId(), a);
        ObservableList<Artikel> observableList;
        observableList = FXCollections.observableArrayList();
        Iterator it = artikelen.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry thisEntry = (Map.Entry) it.next();
            Artikel value = (Artikel) thisEntry.getValue();
            observableList.add(value);
        }





        setKassaMainPane(kassaMainPane);

        TableView<Artikel> table = kassaMainPane.getTable();
        table.setItems(observableList);
        TableColumn<Artikel, String> colArtikelID = new TableColumn<Artikel, String>("ArtikelId");
        colArtikelID.setMinWidth(200);
        colArtikelID.setCellValueFactory(new PropertyValueFactory<Artikel, String>("artikelId"));
        TableColumn<Artikel, String> colArtikelNaam = new TableColumn<Artikel, String>("Artikel Naam");
        colArtikelNaam.setMinWidth(200);
        colArtikelNaam.setCellValueFactory(new PropertyValueFactory<Artikel, String>("artikelNaam"));
        TableColumn<Artikel, String> colCat = new TableColumn<Artikel, String>("Categorie");
        colCat.setMinWidth(200);
        colCat.setCellValueFactory(new PropertyValueFactory<Artikel, String>("categorie"));
        TableColumn<Artikel, String> colPrijs = new TableColumn<Artikel, String>("Prijs");
        colPrijs.setMinWidth(40);
        colPrijs.setCellValueFactory(new PropertyValueFactory<Artikel, String>("prijs"));
        TableColumn<Artikel, String> colVoorraad = new TableColumn<Artikel, String>("Voorraad");
        colVoorraad.setMinWidth(40);
        colVoorraad.setCellValueFactory(new PropertyValueFactory<Artikel, String>("voorraad"));
        table.getColumns().addAll(colArtikelID, colArtikelNaam, colCat, colPrijs, colVoorraad);
    }



}

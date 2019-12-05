package view.panels;

import controller.ArtikelController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import model.domain.Artikel;

import java.util.List;


public class ProductOverviewPane extends GridPane {
	private TableView<Artikel> table;
	private List<Artikel> observableList;
	
	
	public ProductOverviewPane(ArtikelController cont) {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.table = new TableView<>();
        
		this.add(new Label("Products:"), 0, 0, 1, 1);
		this.add(table, 0,1);
		cont.setView(this);
	}

	public void setObservableList(List<Artikel> observableList) {
		this.observableList = observableList;
		table.setItems((javafx.collections.ObservableList<model.domain.Artikel>)observableList);
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

	public ProductOverviewPane() {

	}

}

package view.panels;

import controller.KassaKlantController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.domain.Artikel;
import model.domain.ArtikelContainer;

import java.util.List;


public class KassaKlantPane extends KassaViewTemplate {

	public KassaKlantPane(KassaKlantController cont) {
		super();
		this.add(new Label("Products:"), 0, 0, 1, 1);
		this.add(table, 0,1);
		this.add(totaal, 0,2);
		cont.setView(this);

	}

	public void setObservableList(List<ArtikelContainer> observableList) {
		this.observableList = observableList;
		table.setItems((ObservableList<ArtikelContainer>)observableList);
		TableColumn<ArtikelContainer, String> colAantal = new TableColumn<ArtikelContainer, String>("Aantal");
		colAantal.setMinWidth(200);
		colAantal.setCellValueFactory(new PropertyValueFactory<ArtikelContainer, String>("aantal"));
		TableColumn<ArtikelContainer, String> colArtikelNaam = new TableColumn<ArtikelContainer, String>("Artikel Naam");
		colArtikelNaam.setMinWidth(200);
		colArtikelNaam.setCellValueFactory(new PropertyValueFactory<ArtikelContainer, String>("artikelNaam"));
		TableColumn<ArtikelContainer, String> colPrijs = new TableColumn<ArtikelContainer, String>("Prijs");
		colPrijs.setMinWidth(40);
		colPrijs.setCellValueFactory(new PropertyValueFactory<ArtikelContainer, String>("prijs"));
		table.getColumns().addAll(colAantal, colArtikelNaam, colPrijs);
	}

}

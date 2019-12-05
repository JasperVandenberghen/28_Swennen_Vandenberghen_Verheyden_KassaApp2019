package view.panels;

import controller.KassaKassierController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.domain.Artikel;

import java.util.List;


public class KassaKassierPane extends GridPane {
	private TableView<Artikel> table;
	private List<Artikel> observableList;
	private TextField inputArtikel;
	private Label totaal;


	public KassaKassierPane(KassaKassierController cont) {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.table = new TableView<>();
		this.inputArtikel = new TextField();
		this.totaal = new Label("0");

		this.add(new Label("Scan article"), 0, 0, 1, 1);
		this.add(inputArtikel, 1, 0, 1, 1);
		this.add(new Label("Products in register:"), 0, 1, 1, 1);
		this.add(table, 0,2);
		this.add(totaal, 0,3);
		cont.setView(this);

		observableList = FXCollections.observableArrayList();
		table.setItems((ObservableList<Artikel>)observableList);
		TableColumn<Artikel, String> colAantal = new TableColumn<Artikel, String>("Aantal");
		colAantal.setMinWidth(200);
		colAantal.setCellValueFactory(new PropertyValueFactory<Artikel, String>("aantal"));
		TableColumn<Artikel, String> colArtikelNaam = new TableColumn<Artikel, String>("Artikel Naam");
		colArtikelNaam.setMinWidth(200);
		colArtikelNaam.setCellValueFactory(new PropertyValueFactory<Artikel, String>("artikelNaam"));
		TableColumn<Artikel, String> colPrijs = new TableColumn<Artikel, String>("Prijs");
		colPrijs.setMinWidth(40);
		colPrijs.setCellValueFactory(new PropertyValueFactory<Artikel, String>("prijs"));
		table.getColumns().addAll(colAantal, colArtikelNaam, colPrijs);

	}

	public void setObservableList(List<Artikel> observableList) {
		this.observableList = observableList;
	}

	public void setTotaal(double totaal) {
		this.totaal.setText(Double.toString(totaal));
	}
}

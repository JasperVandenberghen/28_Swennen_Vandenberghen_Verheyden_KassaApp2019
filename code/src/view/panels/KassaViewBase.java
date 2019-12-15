package view.panels;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.domain.ArtikelContainer;

import java.util.List;

public class KassaViewBase extends GridPane {
    protected TableView<ArtikelContainer> table;
    protected List<ArtikelContainer> observableList;
    protected Label totaal;

    public KassaViewBase() {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.table = new TableView<>();
        this.totaal = new Label("");
        this.setTotaal("0");
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

    public void setTotaal(String totaal) {
        this.totaal.setText("Totaal: " + totaal);
    }


}

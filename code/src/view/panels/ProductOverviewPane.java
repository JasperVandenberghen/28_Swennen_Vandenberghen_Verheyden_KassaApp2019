package view.panels;

import controller.ArtikelController;
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


public class ProductOverviewPane extends GridPane {
	private TableView<Artikel> table;
	
	
	public ProductOverviewPane(ArtikelController cont) {
		this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.table = new TableView<>();
        
		this.add(new Label("Products:"), 0, 0, 1, 1);
		this.add(table, 0,1);

		
		
	}

	public ProductOverviewPane() {

	}

	public TableView<Artikel> getTable() {
		return table;
	}
}

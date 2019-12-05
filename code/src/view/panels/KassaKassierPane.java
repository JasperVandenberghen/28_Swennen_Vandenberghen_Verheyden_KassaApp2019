package view.panels;

import controller.KassaKassierController;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import model.domain.ArtikelContainer;

import java.util.List;


public class KassaKassierPane extends KassaViewTemplate {

	private TextField inputArtikel;


	public KassaKassierPane(KassaKassierController cont) {
		this.inputArtikel = new TextField();
		HBox scan = new HBox();
		Label labelScan = new Label("Scan article:");
		labelScan.setTranslateY(5);
		inputArtikel.setTranslateX(5);
		scan.getChildren().add(labelScan);
		scan.getChildren().add(inputArtikel);
		this.add(scan, 0, 0);
		this.add(new Label("Products in register:"), 0, 1, 1, 1);
		this.add(table, 0,2);
		this.add(totaal, 0,3);
		cont.setView(this);

		inputArtikel.setOnAction(event -> {
			cont.setNieuwArtikel(inputArtikel.getText());
		});

	}

	public void setTotaal(double totaal) {
		this.totaal.setText(Double.toString(totaal));
	}
}

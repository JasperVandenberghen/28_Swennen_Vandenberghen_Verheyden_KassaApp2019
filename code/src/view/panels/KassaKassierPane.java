package view.panels;

import controller.KassaKassierController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class KassaKassierPane extends KassaViewTemplate {

	private TextField inputArtikel;
	private Button removeKnop;


	public KassaKassierPane(KassaKassierController cont) {
		this.inputArtikel = new TextField();
		removeKnop = new Button("Remove");
		HBox scan = new HBox();
		Label labelScan = new Label("Scan article:");
		Label labelDelete = new Label("   Delete article:");
		labelScan.setTranslateY(5);
		labelDelete.setTranslateY(3);
		inputArtikel.setTranslateX(5);
		removeKnop.setTranslateX(3);
		scan.getChildren().add(labelScan);
		scan.getChildren().add(inputArtikel);
		scan.getChildren().add(labelDelete);
		scan.getChildren().add(removeKnop);


		this.add(scan, 0, 0);
		this.add(new Label("Products in register:"), 0, 1, 1, 1);
		this.add(table, 0,2);
		this.add(totaal, 0,3);
		cont.setView(this);


		inputArtikel.setOnAction(event -> {
			cont.setNieuwArtikel(inputArtikel.getText());
		});

		removeKnop.setOnAction(event -> {
			cont.removeArtikelen(this.table.getSelectionModel().getSelectedIndices());
		});

	}


}

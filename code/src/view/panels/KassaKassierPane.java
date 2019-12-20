package view.panels;

import controller.KassaKassierController;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;


public class KassaKassierPane extends KassaViewBase {

	private TextField inputArtikel;
	private Button removeKnop;
	private Button onHoldKnop;
	private Button annuleerKnop;
	private Button afrekenKnop;


	public KassaKassierPane(KassaKassierController cont) {
		this.inputArtikel = new TextField();
		removeKnop = new Button("Remove");
		HBox scan = new HBox();
		Label labelScan = new Label("Scan article:");
		Label labelDelete = new Label("   Delete article:");
		onHoldKnop = new Button("Plaats on Hold");
		afrekenKnop = new Button("Afrekenen");
		annuleerKnop = new Button("Annuleer verkoop");
		labelScan.setTranslateY(5);
		labelDelete.setTranslateY(3);
		inputArtikel.setTranslateX(5);
		removeKnop.setTranslateX(3);
		afrekenKnop.setTranslateX(3);
		scan.getChildren().add(labelScan);
		scan.getChildren().add(inputArtikel);
		scan.getChildren().add(labelDelete);
		scan.getChildren().add(removeKnop);

		VBox informatieVerkoopPane = new VBox();
		informatieVerkoopPane.getChildren().addAll(totaal, korting, eindTotaal);

		this.add(scan, 0, 0);
		this.add(new Label("Products in register:"), 0, 1, 1, 1);
		this.add(table, 0,2);

		// column 1
		this.add(onHoldKnop, 1, 0);
		this.add(annuleerKnop, 1, 1);
		this.add(afrekenKnop, 1,2);
		this.add(informatieVerkoopPane, 1, 3);
		this.setValignment(informatieVerkoopPane, VPos.TOP); // To align vertically in the cell
		cont.setView(this);


		inputArtikel.setOnAction(event -> {
			cont.setNieuwArtikel(inputArtikel.getText());
		});

		removeKnop.setOnAction(event -> {
			cont.removeArtikelenStateFunction(this.table.getSelectionModel().getSelectedIndices());
		});

		onHoldKnop.setOnAction(event -> {
			cont.setVerkoopOnHold();
		});

		annuleerKnop.setOnAction(event -> {
			cont.annuleerVerkoop();
		});

		afrekenKnop.setOnAction(event -> {
			cont.beeindigen();
			cont.printKassaBon();
		});

	}

	public void setOnHoldKnopTekst(String tekst) {
		this.onHoldKnop.setText(tekst);
	}
	public void setAfrekenKnop(String tekst) {
		this.afrekenKnop.setText(tekst);
	}

}

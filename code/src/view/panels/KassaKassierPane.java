package view.panels;

import controller.KassaKassierController;
import controller.SettingsController;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;


public class KassaKassierPane extends KassaViewTemplate {

	private TextField inputArtikel;
	private Button removeKnop;
	private Button onHoldKnop;
	private Button afrekenen;


	public KassaKassierPane(KassaKassierController cont, SettingsController set) {
		this.inputArtikel = new TextField();
		removeKnop = new Button("Remove");
		HBox scan = new HBox();
		Label labelScan = new Label("Scan article:");
		Label labelDelete = new Label("   Delete article:");
		onHoldKnop = new Button("Plaats on Hold");
		afrekenen = new Button("Afrekenen");
		labelScan.setTranslateY(5);
		labelDelete.setTranslateY(3);
		inputArtikel.setTranslateX(5);
		removeKnop.setTranslateX(3);
		afrekenen.setTranslateX(3);
		scan.getChildren().add(labelScan);
		scan.getChildren().add(inputArtikel);
		scan.getChildren().add(labelDelete);
		scan.getChildren().add(removeKnop);
		scan.getChildren().add(afrekenen);


		this.add(scan, 0, 0);
		this.add(new Label("Products in register:"), 0, 1, 1, 1);
		this.add(table, 0,2);
		this.add(totaal, 0,3);

		// column 1
		this.add(onHoldKnop, 1, 0);
		this.add(afrekenen, 1,2);
		cont.setView(this);


		inputArtikel.setOnAction(event -> {
			cont.setNieuwArtikel(inputArtikel.getText());
		});

		removeKnop.setOnAction(event -> {
			cont.removeArtikelen(this.table.getSelectionModel().getSelectedIndices());
		});

		onHoldKnop.setOnAction(event -> {
			cont.setVerkoopOnHold(onHoldKnop);
		});

		afrekenen.setOnAction(event -> {
			set.pasKortingenToe();
		});

	}

	public void setOnHoldKnopTekst(String tekst) {
		this.onHoldKnop.setText(tekst);
	}

}

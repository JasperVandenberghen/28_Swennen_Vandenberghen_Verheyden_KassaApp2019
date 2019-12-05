package view;

import controller.KassaKlantController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.panels.KassaKlantPane;

public class KlantView {
	private Stage stage = new Stage();		
		
	public KlantView(KassaKlantController kassaKlantController){
		stage.setTitle("KLANT VIEW");
		stage.setResizable(false);		
		stage.setX(775);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 500, 500);
		root.getChildren().add(kassaKlantController.getKassaKlantPane());
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}
}

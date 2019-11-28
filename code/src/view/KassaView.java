package view;

import controller.ArtikelController;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;	

public class KassaView {
	private Stage stage = new Stage();
		
	public KassaView(ArtikelController cont){
		stage.setTitle("KASSA VIEW");
		stage.setResizable(false);		
		stage.setX(20);
		stage.setY(20);
		Group root = new Group();
		Scene scene = new Scene(root, 750, 500);
		KassaMainPane kassaMainPane = new KassaMainPane(cont);
		kassaMainPane.prefHeightProperty().bind(scene.heightProperty());
		kassaMainPane.prefWidthProperty().bind(scene.widthProperty());
		root.getChildren().add(kassaMainPane);
		stage.setScene(scene);
		stage.sizeToScene();			
		stage.show();		
	}

}

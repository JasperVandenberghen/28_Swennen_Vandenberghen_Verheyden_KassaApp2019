package application;

import controller.ArtikelController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.KassaView;
import view.KlantView;

import java.util.Properties;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		String typeLoadSave = "Tekst";
		KlantView klantView = new KlantView();

		ArtikelController artikelController = new ArtikelController();
		artikelController.setKassaMainPane(artikelController.getKassaMainPane());
		KassaView kassaView = new KassaView(artikelController);

		Properties properties = new Properties();



	}
	
	public static void main(String[] args) {


		launch(args);
	}
}

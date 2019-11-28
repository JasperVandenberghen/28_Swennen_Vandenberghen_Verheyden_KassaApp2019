package application;

import controller.ArtikelController;
import javafx.application.Application;
import javafx.stage.Stage;
import view.KassaView;
import view.KlantView;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		KassaView kassaView = new KassaView();
		KlantView klantView = new KlantView();

		ArtikelController artikelController = new ArtikelController(kassaView.getKassaMainPane().getProductOverviewPane());



	}
	
	public static void main(String[] args) {


		launch(args);
	}
}

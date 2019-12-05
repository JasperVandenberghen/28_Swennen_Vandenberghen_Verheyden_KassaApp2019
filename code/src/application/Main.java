package application;

import controller.ArtikelController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.db.ArtikelDbContext;
import model.db.ArtikelDbStrategyFactory;
import model.domain.Artikel;
import model.domain.ArtikelDbEnum;
import model.domain.SaveEnum;
import view.KassaView;
import view.KlantView;

import java.util.Map;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		String typeDb = "InMemory";
		String typeLoadSave = "Text";

		KlantView klantView = new KlantView();

		// DB INITIATION
		ArtikelDbContext artikelDbContext = new ArtikelDbContext(typeDb, typeLoadSave);
		Map<String, Artikel> artikelMap = artikelDbContext.getAll();


		// TAB ARTIKELEN
		ArtikelController artikelController = new ArtikelController(artikelMap);
		KassaView kassaView = new KassaView(artikelController);
		artikelController.setArtikelenInView();



	}
	
	public static void main(String[] args) {


		launch(args);
	}
}

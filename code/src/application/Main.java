package application;

import controller.ArtikelController;
import controller.KassaKassierController;
import controller.KassaKlantController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.db.ArtikelDbContext;
import model.domain.Artikel;
import model.domain.ArtikelData;
import view.KassaView;
import view.KlantView;
import view.panels.KassaKassierPane;
import view.panels.KassaKlantPane;

import java.util.Map;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		String typeDb = "InMemory";
		String typeLoadSave = "Text";

		// DB INITIATION
		ArtikelDbContext artikelDbContext = new ArtikelDbContext(typeDb, typeLoadSave);
		Map<String, Artikel> artikelMap = artikelDbContext.getAll();

		// TAB KASSA
		ArtikelData artikelData = new ArtikelData();

		KassaKassierController kassaKassierController = new KassaKassierController(artikelMap, artikelData);
		KassaKassierPane kassaKassierPane = new KassaKassierPane(kassaKassierController);

		KassaKlantController kassaKlantController = new KassaKlantController(artikelMap);
		KassaKlantPane kassaKlantPane = new KassaKlantPane(kassaKlantController);

		// TAB ARTIKELEN
		ArtikelController artikelController = new ArtikelController(artikelMap);
		KassaView kassaView = new KassaView(artikelController, kassaKassierController);
		artikelController.setArtikelenInView();


		KlantView klantView = new KlantView(kassaKlantController);
		System.out.println("hll");
	}
	
	public static void main(String[] args) {


		launch(args);
	}
}

package application;

import controller.ArtikelController;
import controller.KassaKassierController;
import controller.KassaKlantController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.db.ArtikelDbContext;
import model.domain.Artikel;
import model.domain.Verkoop;
import view.KassaView;
import view.KlantView;
import view.panels.KassaKassierPane;
import view.panels.KassaKlantPane;

import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Properties;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		String typeDb = "InMemory";
		String typeLoadSave = "Text";
		Properties kassaProps = new Properties();

		kassaProps.setProperty("SaveLoadType", "Text");

		Path propertyFile = Paths.get("Kassa.Properties");

		try{
			Writer propWriter = Files.newBufferedWriter(propertyFile);
			kassaProps.store(propWriter, "Kassa properties");
			propWriter.close();

		} catch (IOException e) {
			e.printStackTrace();
		}

		// DB INITIATION
		ArtikelDbContext artikelDbContext = new ArtikelDbContext(typeDb, typeLoadSave);
		Map<String, Artikel> artikelMap = artikelDbContext.getAll();

		// TAB KASSA
		Verkoop verkoop = new Verkoop(artikelMap);

		KassaKassierController kassaKassierController = new KassaKassierController(verkoop);
		KassaKassierPane kassaKassierPane = new KassaKassierPane(kassaKassierController);

		KassaKlantController kassaKlantController = new KassaKlantController(verkoop);
		KassaKlantPane kassaKlantPane = new KassaKlantPane(kassaKlantController);

		// TAB ARTIKELEN
		ArtikelController artikelController = new ArtikelController(artikelMap);
		KassaView kassaView = new KassaView(artikelController, kassaKassierController);
		artikelController.setArtikelenInView();


		KlantView klantView = new KlantView(kassaKlantController);
	}
	
	public static void main(String[] args) {


		launch(args);
	}
}

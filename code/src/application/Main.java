package application;

import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;
import model.db.ArtikelDbContext;
import model.db.PropertiesHandler;
import model.domain.*;
import view.KassaView;
import view.KlantView;
import view.panels.KassaKassierPane;
import view.panels.KassaKlantPane;
import view.panels.SettingsPane;
import view.panels.LogView;

import java.util.Map;
import java.util.Properties;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		PropertiesHandler propertiesHandler = new PropertiesHandler();
		Properties properties = propertiesHandler.read();
		String typeDb = properties.getProperty("dbType");
		String typeLoadSave = properties.getProperty("typeLoadSave");
		String typeKorting = properties.getProperty("typeKorting");
		String kortingsAantal = properties.getProperty("aantalKorting");
		String categorieKorting = properties.getProperty("categorieKorting");
		String drempelKorting = properties.getProperty("drempelKorting");


		KortingHandler kortingHandler = new KortingHandler();

		// DB INITIATION
		ArtikelDbContext artikelDbContext = new ArtikelDbContext(typeDb, typeLoadSave);
		Map<String, Artikel> artikelMap = artikelDbContext.getAll();

		// TAB KASSA
		LogHandler logHandler = new LogHandler();
		Verkoop verkoop = new Verkoop(artikelMap, kortingHandler, logHandler, artikelDbContext);
		verkoop.registerObserver(artikelDbContext);

		SettingsController settingsController = new SettingsController(propertiesHandler, kortingHandler);
		try{
			settingsController.getKortingHandler().addKorting(typeKorting,kortingsAantal, categorieKorting, drempelKorting);
		} catch (NullPointerException e) {

		}

		new SettingsPane(settingsController);

		KassaKassierController kassaKassierController = new KassaKassierController(verkoop);
		new KassaKassierPane(kassaKassierController);


		KassaKlantController kassaKlantController = new KassaKlantController(verkoop);
		new KassaKlantPane(kassaKlantController);

		// Tab LOG
		LogController logController = new LogController(logHandler);
		new LogView(logController);

		// TAB ARTIKELEN
		ArtikelController artikelController = new ArtikelController(verkoop);
		KassaView kassaView = new KassaView(artikelController, kassaKassierController, settingsController, logController);
		verkoop.artikelenMapToListAndNotifyObservers();

		KlantView klantView = new KlantView(kassaKlantController);
	}
	
	public static void main(String[] args) {


		launch(args);
	}
}

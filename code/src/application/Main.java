package application;

import controller.ArtikelController;
import controller.KassaKassierController;
import controller.KassaKlantController;
import controller.SettingsController;
import javafx.application.Application;
import javafx.stage.Stage;
import model.db.ArtikelDbContext;
import model.db.PropertiesHandler;
import model.domain.Artikel;
import model.domain.Verkoop;
import view.KassaView;
import view.KlantView;
import view.panels.KassaKassierPane;
import view.panels.KassaKlantPane;
import view.panels.KassaSettingsPane;

import java.util.Map;
import java.util.Properties;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		PropertiesHandler propertiesHandler = new PropertiesHandler();
		Properties properties = propertiesHandler.read();
		String typeDb = properties.getProperty("dbType");
		String typeLoadSave = properties.getProperty("typeLoadSave");

		/*ArtikelExcelLoadSaveStrategy bla = new ArtikelExcelLoadSaveStrategy();
		bla.setFile("code/artikel.xls");
		System.out.println(bla.load());
*/
		// DB INITIATION
		ArtikelDbContext artikelDbContext = new ArtikelDbContext(typeDb, typeLoadSave);
		Map<String, Artikel> artikelMap = artikelDbContext.getAll();

		// TAB KASSA
		Verkoop verkoop = new Verkoop(artikelMap);

		SettingsController settingsController = new SettingsController(propertiesHandler);
		KassaSettingsPane kassaSettingsPane = new KassaSettingsPane(settingsController);

		KassaKassierController kassaKassierController = new KassaKassierController(verkoop);
		KassaKassierPane kassaKassierPane = new KassaKassierPane(kassaKassierController, settingsController);

		KassaKlantController kassaKlantController = new KassaKlantController(verkoop);
		KassaKlantPane kassaKlantPane = new KassaKlantPane(kassaKlantController);

		// Tab SETTINGS


		// TAB ARTIKELEN
		ArtikelController artikelController = new ArtikelController(artikelMap);
		KassaView kassaView = new KassaView(artikelController, kassaKassierController, settingsController);
		artikelController.setArtikelenInView();


		KlantView klantView = new KlantView(kassaKlantController);
	}
	
	public static void main(String[] args) {


		launch(args);
	}
}

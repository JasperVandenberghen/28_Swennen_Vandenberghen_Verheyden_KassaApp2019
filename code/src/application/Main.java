package application;
	
import db.ArtikelDb;
import db.ArtikelTekstLoadSave;
import javafx.application.Application;
import javafx.stage.Stage;
import view.KassaView;
import view.KlantView;

import java.util.ArrayList;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) {
		KassaView kassaView = new KassaView();
		KlantView klantView = new KlantView();
	}
	
	public static void main(String[] args) {
		ArtikelDb artikelDb = new ArtikelDb();
		ArtikelTekstLoadSave artikelTekstLoadSave = new ArtikelTekstLoadSave();
		ArrayList artikelen = (ArrayList) artikelTekstLoadSave.load();
		artikelDb.addMultiple(artikelen);
		System.out.println(artikelDb);

		launch(args);
	}
}

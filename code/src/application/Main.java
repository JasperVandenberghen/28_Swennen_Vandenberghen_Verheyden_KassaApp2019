package application;

import controller.ArtikelController;
import excel.ArtikelExcelLoadSaveStrategy;
import javafx.application.Application;
import javafx.stage.Stage;
import jxl.read.biff.BiffException;
import view.KassaView;
import view.KlantView;

import java.io.IOException;

public class Main extends Application {

	@Override
	public void start(Stage primaryStage) throws IOException, BiffException {
		String typeLoadSave = "Tekst";
		KlantView klantView = new KlantView();

		ArtikelController artikelController = new ArtikelController();
		KassaView kassaView = new KassaView(artikelController);
		artikelController.setArtikelenInView();
		ArtikelExcelLoadSaveStrategy excel = new ArtikelExcelLoadSaveStrategy();
		excel.setFile("artikel.xls");
		excel.load();


	}
	
	public static void main(String[] args) throws IOException, BiffException {



		launch(args);
	}
}

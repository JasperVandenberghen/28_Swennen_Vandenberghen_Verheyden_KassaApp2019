package view;


import controller.ArtikelController;
import controller.KassaKassierController;
import controller.LogController;
import controller.SettingsController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import view.panels.ArtikelPane;

public class KassaMainPane extends BorderPane {
	public KassaMainPane(ArtikelController artikelController, KassaKassierController kassaKassierController, SettingsController settingsController, LogController logController){
        ArtikelPane artikelPane = new ArtikelPane(artikelController);
	    TabPane tabPane = new TabPane(); 	    
      Tab kassaTab = new Tab("Kassa", kassaKassierController.getKassaKassierPane());
        Tab artikelTab = new Tab("Artikelen",artikelController.getArtikelPane());
        Tab instellingTab = new Tab("Instellingen", settingsController.getSettingsPane());
        Tab logTab = new Tab("Log", logController.getView());
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}



}

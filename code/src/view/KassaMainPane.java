package view;


import controller.ArtikelController;
import controller.KassaKassierController;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.BorderPane;
import view.panels.ProductOverviewPane;

public class KassaMainPane extends BorderPane {
	public KassaMainPane(ArtikelController artikelController, KassaKassierController kassaKassierController){
        ProductOverviewPane productOverviewPane = new ProductOverviewPane(artikelController);
	    TabPane tabPane = new TabPane(); 	    
      Tab kassaTab = new Tab("Kassa", kassaKassierController.getKassaKassierPane());
        Tab artikelTab = new Tab("Artikelen",artikelController.getProductOverviewPane());
        Tab instellingTab = new Tab("Instellingen");
        Tab logTab = new Tab("Log");
        tabPane.getTabs().add(kassaTab);
        tabPane.getTabs().add(artikelTab);
        tabPane.getTabs().add(instellingTab);
        tabPane.getTabs().add(logTab);
	    this.setCenter(tabPane);
	}



}

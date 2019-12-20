package controller;

import javafx.scene.control.Button;
import model.db.PropertiesHandler;
import model.domain.KassaBon.KassaBon;
import model.domain.KassaBon.KassaBonBasis;
import model.domain.KassaBon.KassaBonFooter;
import model.domain.KassaBon.KassaBonHeader;
import model.domain.Observer;
import model.domain.Verkoop;
import view.panels.KassaKassierPane;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class KassaKassierController implements Observer {
    private KassaKassierPane kassaKassierPane;
    private Verkoop verkoop;

    public KassaKassierController(Verkoop verkoop) {
        this.verkoop = verkoop;
        verkoop.registerObserver(this);
        verkoop.setKassaKassierController(this);

    }

    public void setView(KassaKassierPane kassaKassierPane){
        this.kassaKassierPane = kassaKassierPane;
        this.kassaKassierPane.setObservableList(verkoop.getArtikelenInKassaKassier());
    }

    public void setNieuwArtikel(String artikelId){
        verkoop.addArtikelStateFunction(artikelId);
    }

    public void removeArtikelenStateFunction(List<Integer> indeces){
        verkoop.removeArtikelenStateFunction(indeces);
    }

    public KassaKassierPane getKassaKassierPane() {
        return kassaKassierPane;
    }

    @Override
    public void update(String totaal, String korting, String eindTotaal) {
        this.kassaKassierPane.setTotaal(totaal);
        kassaKassierPane.setKorting(korting);
        kassaKassierPane.setEindtotaal(eindTotaal);
    }

    public void setVerkoopOnHold(Button button){
        verkoop.onHoldFunction(button);
    }

    public void beeindigen(Button button){
        verkoop.beeindigenStateFunction(button);
    }


    public String convertDateToString(Date indate)
    {
        String dateString = null;
        SimpleDateFormat sdfr = new SimpleDateFormat("dd-MMM-yyyy " + indate.getHours() + ":"+ indate.getMinutes() +":" + indate.getSeconds() + "\n");

        try{
            dateString = sdfr.format( indate );
        }catch (Exception ex ){
            System.out.println(ex);
        }
        return dateString;
    }

    public String printBTWOpTotaal(){
        return "Totaal: €" + verkoop.getEindTotaal() + "\nExclusief BTW: €" + verkoop.getEindTotaal() * 0.94 + "\n" + "BTW 6%";
    }


    public void printKassaBon() {
        PropertiesHandler propertiesHandler = new PropertiesHandler();
        Properties properties = propertiesHandler.read();
        String algemeneHeaderBoodschap = properties.getProperty("algemeneHeaderField");
        String algemeneFooterBoodschap = properties.getProperty("algemeneBoodschapFooterField");
        String algemeneHeader = properties.getProperty("algemeneHeader");
        String algemeneFooter = properties.getProperty("algemeneFooter");
        String prijsKortingFooter = properties.getProperty("prijsKortingFooter");
        String datumTijdHeader = properties.getProperty("datumTijdHeader");
        String prijsBtwFooter = properties.getProperty("prijsBtwFooter");
        KassaBon kassaBon = new KassaBonBasis();
        kassaBon.setVerkoop(this.verkoop);

        //toevoegen van header door basisbon door te geven
        kassaBon = new KassaBonHeader(kassaBon);


        if(algemeneHeader.equalsIgnoreCase("true")){
            kassaBon.setDescription(algemeneHeaderBoodschap + " \n");
        }
        if(datumTijdHeader.equalsIgnoreCase("true")){
            Date date = new Date();
            kassaBon.setDescription(convertDateToString(date));
        }

        //toevoegen van footer door basis + header door te geven
        kassaBon = new KassaBonFooter(kassaBon);

        if(algemeneFooter.equalsIgnoreCase("true")){
            kassaBon.setDescription(algemeneFooterBoodschap + " \n");
        }

        if(prijsKortingFooter.equalsIgnoreCase("true")){
            kassaBon.setDescription(verkoop.printTotaalMetKortingString());
        }

        if(prijsBtwFooter.equalsIgnoreCase("true")){
            kassaBon.setDescription(printBTWOpTotaal());
        }

        System.out.println(kassaBon.getDescription());
    }
}

package model.domain;

import model.db.PropertiesHandler;
import model.domain.KassaBon.KassaBon;
import model.domain.KassaBon.KassaBonBasis;
import model.domain.KassaBon.KassaBonFooter;
import model.domain.KassaBon.KassaBonHeader;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class KassaBonHandler {
    private Verkoop verkoop;

    public Verkoop getVerkoop() {
        return verkoop;
    }

    public void setVerkoop(Verkoop verkoop) {
        this.verkoop = verkoop;
    }

    public static String convertDateToString(Date indate)
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
        return "Totaal: €" + String.format("%.2f",verkoop.getEindTotaal()) + "\nExclusief BTW: €" + String.format("%.2f",verkoop.getEindTotaal() * 0.94)+ "\n" + "BTW 6%";
    }
    public String getTotaalMetKortingString(){
        return "Totaal: €" + verkoop.getTotaal() + "\nKorting: €" + verkoop.getKorting();
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

        KassaBon kassaBon = new KassaBonBasis(this);

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
            kassaBon.setDescription(getTotaalMetKortingString());
        }

        if(prijsBtwFooter.equalsIgnoreCase("true")){
            kassaBon.setDescription(printBTWOpTotaal());
        }

        System.out.println(kassaBon.getDescription());
    }
}

package model.domain;

import controller.KassaKassierController;
import controller.KassaKlantController;
import controller.LogController;
import javafx.collections.FXCollections;
import javafx.scene.control.Button;
import model.db.OnHoldHandler;
import model.domain.states.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Verkoop implements Observable {
    private List<Observer> observers;
    private Map<String, Artikel> artikelMap;
    private List<ArtikelContainer> artikelenInKassaKassier;
    private List<ArtikelContainer> artikelenInKassaKlant;
    private double totaal;
    private OnHoldHandler onHoldHandler;
    private KassaKassierController kassaKassierController;
    private KassaKlantController kassaKlantController;
    private KortingHandler kortingHandler;
    private LogHandler logHandler;
    private double korting;
    private double eindTotaal;

    private String totaalText;
    private String eindTotaalText;
    private String kortingText;

    // states
    private VerkoopState legeMandState;
    private VerkoopState scanState;
    private VerkoopState afrekenState;
    private VerkoopState legeMandMetOnHoldState;
    private VerkoopState scanMetOnHoldState;
    private VerkoopState afrekenMetOnHoldState;

    private VerkoopState verkoopState;

    public double getTotaal() {
        return totaal;
    }

    public void setTotaal(double totaal) {
        this.totaal = totaal;
    }

    public Verkoop(Map<String, Artikel> artikelMap, KortingHandler kortingHandler, LogHandler logHandler) {
        observers = new ArrayList<Observer>();
        artikelenInKassaKassier = FXCollections.observableArrayList();
        artikelenInKassaKlant = FXCollections.observableArrayList();
        this.artikelMap = artikelMap;
        this.onHoldHandler = new OnHoldHandler();
        this.kortingHandler = kortingHandler;
        this.logHandler = logHandler;

        // states
        legeMandState = new LegeMandState(this);
        scanState = new ScanState(this);
        afrekenState = new AfrekenenState(this);
        legeMandMetOnHoldState = new LegeMandMetOnHoldState(this);
        scanMetOnHoldState = new ScanMetOnHoldState(this);
        afrekenMetOnHoldState = new AfrekenenMetOnHoldState(this);
        verkoopState = legeMandState;
    }

    public void setKassaKassierController(KassaKassierController kassaKassierController) {
        this.kassaKassierController = kassaKassierController;
    }

    public void setKassaKlantController(KassaKlantController kassaKlantController) {
        this.kassaKlantController = kassaKlantController;
    }

    public List<ArtikelContainer> getArtikelenInKassaKassier() {
        return artikelenInKassaKassier;
    }

    public List<ArtikelContainer> getArtikelenInKassaKlant() {
        return artikelenInKassaKlant;
    }

    public void addArtikelStateFunction(String artikelId){
        verkoopState.addArtikel(artikelId);
    }

    public void addArtikel(String artikelId) {
        try{
            Artikel artikel = artikelMap.get(artikelId).clone();
            ArtikelContainer artikelContainer = new ArtikelContainer(artikel);
            ArtikelContainer artikelContainerKassaKlant = new ArtikelContainer(artikel);
            totaal += artikelContainer.getPrijs();
            addArtikelToKassaKassier(artikelContainer);
            addArtikelToKassaKlant(artikelContainerKassaKlant);
            this.totaalText = "Totaal: € " + FormatNumberClass.parseToStringTwoDecimals(totaal);
            notifyObservers();
        } catch (Exception e){
            MessageHandler.showAlert("De opgegeven artikelcode is niet beschikbaar");
        }
    }

    public void addArtikelToKassaKassier(ArtikelContainer artikelContainer){
        artikelenInKassaKassier.add(artikelContainer);
    }

    public void addArtikelToKassaKlant(ArtikelContainer artikelContainer){
        ArtikelContainer artikelGevondenInKassa = null;
        int index = 0;
        for(ArtikelContainer artikelContainerInKassa:artikelenInKassaKlant){
            if(artikelContainerInKassa.equals(artikelContainer)){
                artikelGevondenInKassa = artikelContainerInKassa;
                break;
            }
            index++;
        }
        if(artikelGevondenInKassa==null){
            artikelenInKassaKlant.add(artikelContainer);
        } else{
            artikelGevondenInKassa.verhoogAantal();
            artikelenInKassaKlant.set(index, artikelGevondenInKassa);
        }
    }

    public void verwijderArtikelInKassaKlant(String artikelId){
        ArtikelContainer artikelGevondenInKassa = null;
        int index = 0;
        for(ArtikelContainer artikelContainerInKassa:artikelenInKassaKlant){
            if(artikelContainerInKassa.getArtikelId().equals(artikelId)){
                artikelGevondenInKassa = artikelContainerInKassa;
                break;
            }
            index++;
        }
        if(artikelGevondenInKassa.getAantal()==1){
            artikelenInKassaKlant.remove(index);
        } else{
            artikelGevondenInKassa.verlaagAantal();
            artikelenInKassaKlant.set(index, artikelGevondenInKassa);
        }
    }

    public void removeArtikelenStateFunction(List<Integer> indeces){
        this.verkoopState.removeArtikel(indeces);
    }

    public void beeindigenStateFunction(Button button){
        this.verkoopState.beeindigen(button);
    }

    public void removeArtikelen(List<Integer> indeces){
        for(Integer integer: indeces){
            String artikelId = this.artikelenInKassaKassier.get(integer).getArtikelId();
            double prijs = this.artikelenInKassaKassier.get(integer).getPrijs();
//            this.artikelenInKassaKlant.removeIf(a -> a.getArtikelId().equals(artikelId) && a.getAantal()==1);
            verwijderArtikelInKassaKlant(artikelId);
            this.artikelenInKassaKassier.remove(integer.intValue());
            totaal-=prijs;
        }
        notifyObservers();
    }

    public void calculateKorting(){

    }

    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if (i >= 0) {
            observers.remove(i);
        }
    }


    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(totaalText, kortingText, eindTotaalText);
        }
    }

    public void plaatsOnHold(Button button){
        this.onHoldHandler.setArtikelen(this.artikelenInKassaKlant, this.artikelenInKassaKassier, totaal);
        newSale();
        button.setText("Haal uit on Hold");
    }

    public void haalVanOnHoldAf(Button button){
        this.artikelenInKassaKassier.addAll(this.onHoldHandler.getArtikelenInKassaKassier());
        this.artikelenInKassaKlant.addAll(this.onHoldHandler.getArtikelenInKassaKlant());
        this.totaal = this.onHoldHandler.getTotaal();
        notifyObservers();
        button.setText("Plaats on Hold");
    }

    public void onHoldFunction(Button button) {
        verkoopState.onHoldFunction(button);
    }

    public void afrekenenStateFunction(Button button){
        verkoopState.beeindigen(button);
    }

    public void afrekenen(Button button){
        button.setText("Betalen");
        kortingHandler.setArtikelContainers(artikelenInKassaKassier);
        double totaalNakorting = kortingHandler.getNewTotaalNaKorting();
        korting = (totaal - totaalNakorting);
        eindTotaal = totaalNakorting;
        setText("Totaal: € " + FormatNumberClass.parseToStringTwoDecimals(totaal), "Korting: €" + FormatNumberClass.parseToStringTwoDecimals(korting), "Eindtotaal: €" + FormatNumberClass.parseToStringTwoDecimals(eindTotaal));
        notifyObservers();
    }

    public void setText(String totaal, String korting, String eindTotaal){
        this.totaalText = totaal;
        this.kortingText = korting;
        this.eindTotaalText = eindTotaal;

    }

    public void betalen(Button button){
        logHandler.addLog(totaal, korting, eindTotaal);
        button.setText("Afrekenen");
        setText("Totaal: € 0","","");
        notifyObservers();
        clearArtikelen();
    }

    public void newSale(){
        clearArtikelen();
    }

    public void clearArtikelen(){
        artikelenInKassaKassier.clear();
        artikelenInKassaKlant.clear();
        this.totaal = 0;
        notifyObservers();
    }

    public void annuleerAfrekenen(){
        // clear javafx shit
        clearArtikelen();
    }

    public OnHoldHandler getOnHoldHandler() {
        return onHoldHandler;
    }

    public KassaKassierController getKassaKassierController() {
        return kassaKassierController;
    }

    public KassaKlantController getKassaKlantController() {
        return kassaKlantController;
    }

    // STATE METHODS
    public VerkoopState getLegeMandState() {
        return legeMandState;
    }

    public void setLegeMandState(VerkoopState legeMandState) {
        this.legeMandState = legeMandState;
    }

    public VerkoopState getScanState() {
        return scanState;
    }

    public void setScanState(VerkoopState scanState) {
        this.scanState = scanState;
    }

    public VerkoopState getAfrekenState() {
        return afrekenState;
    }

    public void setAfrekenState(VerkoopState afrekenState) {
        this.afrekenState = afrekenState;
    }

    public VerkoopState getLegeMandMetOnHoldState() {
        return legeMandMetOnHoldState;
    }

    public void setLegeMandMetOnHoldState(VerkoopState legeMandMetOnHoldState) {
        this.legeMandMetOnHoldState = legeMandMetOnHoldState;
    }

    public VerkoopState getScanMetOnHoldState() {
        return scanMetOnHoldState;
    }

    public void setScanMetOnHoldState(VerkoopState scanMetOnHoldState) {
        this.scanMetOnHoldState = scanMetOnHoldState;
    }

    public VerkoopState getAfrekenMetOnHoldState() {
        return afrekenMetOnHoldState;
    }

    public void setAfrekenMetOnHoldState(VerkoopState afrekenMetOnHoldState) {
        this.afrekenMetOnHoldState = afrekenMetOnHoldState;
    }

    public VerkoopState getVerkoopState() {
        return verkoopState;
    }

    public void setVerkoopState(VerkoopState verkoopState) {
        this.verkoopState = verkoopState;
    }
}

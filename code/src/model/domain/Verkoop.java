package model.domain;

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

    // states
    private VerkoopState legeMandState;
    private VerkoopState scanState;
    private VerkoopState afrekenState;
    private VerkoopState legeMandMetOnHoldState;
    private VerkoopState scanMetOnHoldState;
    private VerkoopState afrekenMetOnHoldState;

    private VerkoopState verkoopState = legeMandState;


    public double getTotaal() {
        return totaal;
    }

    public void setTotaal(double totaal) {
        this.totaal = totaal;
    }

    public Verkoop(Map<String, Artikel> artikelMap) {
        observers = new ArrayList<Observer>();
        artikelenInKassaKassier = FXCollections.observableArrayList();
        artikelenInKassaKlant = FXCollections.observableArrayList();
        this.artikelMap = artikelMap;
        this.onHoldHandler = new OnHoldHandler();

        // states
        legeMandState = new LegeMandState(this);
        scanState = new ScanState(this);
        afrekenState = new AfrekenenState(this);
        legeMandMetOnHoldState = new LegeMandMetOnHoldState(this);
        scanMetOnHoldState = new ScanMetOnHoldState(this);
        afrekenMetOnHoldState = new AfrekenenMetOnHoldState(this);
    }

    public Verkoop(){
        observers = new ArrayList<Observer>();
        artikelenInKassaKassier = FXCollections.observableArrayList();
        artikelenInKassaKlant = FXCollections.observableArrayList();
        this.artikelMap = artikelMap;
        this.onHoldHandler = new OnHoldHandler();

        // states
        legeMandState = new LegeMandState(this);
        scanState = new ScanState(this);
        afrekenState = new AfrekenenState(this);
        legeMandMetOnHoldState = new LegeMandMetOnHoldState(this);
        scanMetOnHoldState = new ScanMetOnHoldState(this);
        afrekenMetOnHoldState = new AfrekenenMetOnHoldState(this);
    }


    public List<ArtikelContainer> getArtikelenInKassaKassier() {
        return artikelenInKassaKassier;
    }

    public List<ArtikelContainer> getArtikelenInKassaKlant() {
        return artikelenInKassaKlant;
    }

    public void addArtikel(String artikelId) {
        try{
            Artikel artikel = artikelMap.get(artikelId).clone();
            ArtikelContainer artikelContainer = new ArtikelContainer(artikel);
            ArtikelContainer artikelContainerKassaKlant = new ArtikelContainer(artikel);
            totaal += artikelContainer.getPrijs();
            addArtikelToKassaKassier(artikelContainer);
            addArtikelToKassaKlant(artikelContainerKassaKlant);
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
            DecimalFormat df = new DecimalFormat("#.00");
            observer.update(df.format(totaal));

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

    public void afrekenenStateFunction(){
        verkoopState.afrekenen();
    }

    public void afrekenen(){

    }

    public void betalen(){
        clearArtikelen();
    }

    public void reguleerAantalVerkopenSindsOnHold(){
        onHoldHandler.increaseAantalVerkopenSindsOnHold();
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

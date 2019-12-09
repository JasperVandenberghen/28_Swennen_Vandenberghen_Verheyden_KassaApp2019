package model.domain;

import javafx.collections.FXCollections;
import model.db.OnHoldHandler;

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
    }

    public List<ArtikelContainer> getArtikelenInKassaKassier() {
        return artikelenInKassaKassier;
    }

    public List<ArtikelContainer> getArtikelenInKassaKlant() {
        return artikelenInKassaKlant;
    }

    public void addArtikel(String artikelId) {
        Artikel artikel = artikelMap.get(artikelId).clone();
        ArtikelContainer artikelContainer = new ArtikelContainer(artikel);
        ArtikelContainer artikelContainerKassaKlant = new ArtikelContainer(artikel);
        totaal += artikelContainer.getPrijs();
        addArtikelToKassaKassier(artikelContainer);
        addArtikelToKassaKlant(artikelContainerKassaKlant);
        notifyObservers();
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

    public String onHoldFunction() {
        String newButtonTekst;
        if(onHoldHandler.isOnHold()){
            if(this.artikelenInKassaKassier.size()!=0){
                MessageHandler.showAlert("U kan enkel artikelen uit on Hold halen wanneer je verkoop afgehandeld zijn.");
                return "Haal uit on Hold";
            }
            this.artikelenInKassaKassier.addAll(this.onHoldHandler.getArtikelenInKassaKassier());
            this.artikelenInKassaKlant.addAll(this.onHoldHandler.getArtikelenInKassaKlant());
            this.totaal = this.onHoldHandler.getTotaal();
            notifyObservers();
            newButtonTekst = "Plaats on Hold";
        } else{
            this.onHoldHandler.setArtikelen(this.artikelenInKassaKlant, this.artikelenInKassaKassier, totaal);
            newSale(true);
            newButtonTekst = "Haal uit on Hold";
        }
        return newButtonTekst;
    }
    public void newSale(boolean resetAantalVerkopenSinds){
        artikelenInKassaKassier.clear();
        artikelenInKassaKlant.clear();
        if(resetAantalVerkopenSinds == false){
            onHoldHandler.increaseAantalVerkopenSindsOnHold();
        }
        this.totaal = 0;
        notifyObservers();
    }
}

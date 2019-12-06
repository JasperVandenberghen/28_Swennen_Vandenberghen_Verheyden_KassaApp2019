package model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Verkoop implements Observable {
    private List<Observer> observers;
    private Map<String, Artikel> artikelMap;
    private List<ArtikelContainer> artikelenInKassaKassier;
    private List<ArtikelContainer> artikelenInKassaKlant;
    private int totaal;

    public Verkoop(Map<String, Artikel> artikelMap) {
        observers = new ArrayList<Observer>();
        this.artikelMap = artikelMap;
    }

    public List<ArtikelContainer> getArtikelenInKassaKassier() {
        return artikelenInKassaKassier;
    }

    public List<ArtikelContainer> getArtikelenInKassaKlant() {
        return artikelenInKassaKlant;
    }

    public void addArtikel(String artikelId) {
        updateObservers();
        ArtikelContainer artikelContainer = new ArtikelContainer(artikelMap.get(artikelId));
        totaal += artikelContainer.getPrijs();
    }

    public void addArtikelToKassaKassier(ArtikelContainer artikelContainer){
        artikelenInKassaKlant.add(artikelContainer);
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

    public void verwijderArtikelInKassaKlant(ArtikelContainer artikelContainer){
        ArtikelContainer artikelGevondenInKassa = null;
        int index = 0;
        for(ArtikelContainer artikelContainerInKassa:artikelenInKassaKlant){
            if(artikelContainerInKassa.equals(artikelContainer)){
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

    public void deleteObserver(Observer o){
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.remove(artikelId);}
    }

    public void removeArtikel(String artikelId) {
        artikelenInKassa.removeIf(a -> a.getArtikelId().equals(artikelId));
    }

    @Override
    public void notifyObservers() {

    }


    public void updateObservers() {
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.update(artikelId);

        }
    }
}

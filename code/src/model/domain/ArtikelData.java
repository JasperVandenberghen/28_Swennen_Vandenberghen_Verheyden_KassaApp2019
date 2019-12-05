package model.domain;

import java.util.ArrayList;
import java.util.List;

public class ArtikelData implements Observable {
    private List<Observer> observers;
    private String artikelId;

    public ArtikelData() {
        observers = new ArrayList<Observer>();
    }

    public void setArtikelId(String artikelId) {
        this.artikelId = artikelId;
        updateObservers();
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

    public void deleteObserver(){
        for (int i = 0; i < observers.size(); i++) {
            Observer observer = (Observer) observers.get(i);
            observer.remove(artikelId);}
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

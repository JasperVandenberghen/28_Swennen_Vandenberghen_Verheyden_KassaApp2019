package model.db;

import model.domain.Artikel;

import java.util.*;

public class ArtikelDbInMemory implements ArtikelDbStrategy{
    private Map<String, Artikel> artikelMap;
    private LoadSaveStrategy loadSaveStrategy;
    public ArtikelDbInMemory(LoadSaveStrategyFactory loadSaveFactory) {
        this.loadSaveStrategy = loadSaveFactory.getLoadSave("tekst");
        artikelMap = loadSaveStrategy.load();
    }

    @Override
    public Artikel get(String id) {
        if(id == null){throw new DbException("Geen id meegegeven");}
        return artikelMap.get(id);
    }

    @Override
    public Map<String, Artikel> getAll() {
        return artikelMap;
    }

    @Override
    public void add(Artikel artikel) {
        if(artikel == null){throw new DbException("Artikel mag niet leeg zijn.");}
        artikelMap.put(artikel.getArtikelId(),artikel);
    }

    public void addMultiple(List<Artikel> list){
        if(list == null || list.size() == 0){throw new DbException("Geen artikelen om toe te voegen.");}
        for(Artikel a: list){
            artikelMap.put(a.getArtikelId(), a);
        }
    }

    @Override
    public void update(Artikel artikel) {

    }

    @Override
    public void delete(String id) {
        if(id == null){throw new DbException("Geen id meegegeven");}
        artikelMap.remove(id);
    }

    @Override
    public int getAantalArtikelen() {
        return artikelMap.size();
    }
}

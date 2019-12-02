package model.db;

import model.domain.Artikel;

import java.util.Map;

public class ArtikelDbContext {
    private Map<String, Artikel> artikelMap;
    private ArtikelDbStrategy artikelDbStrategy;
    public ArtikelDbContext(String typeArtikelDbStrategy) {
        ArtikelDbStrategyFactory artikelDbStrategyFactory = ArtikelDbStrategyFactory.getInstance();
        this.artikelDbStrategy = artikelDbStrategyFactory.getArtikelDbStrategy(typeArtikelDbStrategy);
    }

    public Artikel get(String id) {
    if(id == null){throw new DbException("Geen id meegegeven");}
    return artikelMap.get(id);
}

    public Map<String, Artikel> getAll() {
        return artikelMap;
    }

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

    public void update(Artikel artikel) {

    }

    public void delete(String id) {
        if(id == null){throw new DbException("Geen id meegegeven");}
        artikelMap.remove(id);
    }

    public int getAantalArtikelen() {
        return artikelMap.size();
    }
}

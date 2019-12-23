package database;

import model.domain.Artikel;
import model.domain.DomainException;

import java.util.ArrayList;
import java.util.List;

public class ArtikelDbInMemory implements ArtikelDbStrategy{
    private List<Artikel> artikelList;
    private LoadSaveStrategy loadSaveStrategy;
    public ArtikelDbInMemory(String typeLoadSaveStrategy) {
        LoadSaveStrategyFactory loadSaveStrategyFactory = LoadSaveStrategyFactory.getInstance();
        this.loadSaveStrategy = loadSaveStrategyFactory.getLoadSave(typeLoadSaveStrategy);
        int z = 3;
        try{
            List<?> l = loadSaveStrategy.load();
            artikelList = (ArrayList<Artikel>) l;
        } catch (Exception e){
            throw new DbException("kapoet");
        }
//        this.addMultiple();
    }

    @Override
    public Artikel get(String id) {
        if(id == null){throw new DbException("Geen id meegegeven");}
        for(Artikel artikel: artikelList){
            if(artikel.getArtikelId().equalsIgnoreCase(id)){
                return artikel;
            }
        }
        throw new DomainException("Geen artikel gevonden met id " + id);
    }

    @Override
    public List<Artikel> getAll() {
        return artikelList;
    }

    @Override
    public void add(Artikel artikel) {
        if(artikel == null){throw new DbException("Artikel mag niet leeg zijn.");}
//        artikelList.put(artikel.getArtikelId(),artikel);
    }

    public void addMultiple(List<Artikel> list){
        if(list == null || list.size() == 0){throw new DbException("Geen artikelen voor InMemory om toe te voegen.");}
        for(Artikel a: list){
//            artikelList.put(a.getArtikelId(), a);
        }
    }

    @Override
    public void update(Artikel artikel) {

    }

    @Override
    public void delete(String id) {
        if(id == null){throw new DbException("Geen id meegegeven");}
        artikelList.remove(id);
    }

    @Override
    public int getAantalArtikelen() {
        return artikelList.size();
    }

    @Override
    public void saveArtikelen(List<Artikel> artikels) {
        loadSaveStrategy.save(artikels);
    }
}

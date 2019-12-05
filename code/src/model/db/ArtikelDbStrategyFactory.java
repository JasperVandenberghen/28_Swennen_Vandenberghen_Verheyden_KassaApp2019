package model.db;

import model.domain.ArtikelDbEnum;

public class ArtikelDbStrategyFactory {
    private volatile static ArtikelDbStrategyFactory uniqueInstance = new ArtikelDbStrategyFactory();
    private ArtikelDbStrategyFactory() {
    }

    public ArtikelDbStrategy getArtikelDbStrategy(String typeDb, String typeLoadSave){
        ArtikelDbEnum dbEnum = ArtikelDbEnum.getSave(typeDb);
        String klasseNaam = dbEnum.getKlasseNaam();
        ArtikelDbStrategy artikelDbStrategy = null;
        try {
            Class dbClass = Class.forName(klasseNaam);
            Object dbObject = dbClass.getConstructor(String.class).newInstance(typeLoadSave);
            artikelDbStrategy = (ArtikelDbStrategy) dbObject;
        } catch (Exception e) {
            throw new DbException("The requested strategy could not be found");
        }
        return artikelDbStrategy;
    }

    public static ArtikelDbStrategyFactory getInstance(){
        if(uniqueInstance==null){
            synchronized (ArtikelDbStrategy.class){
                if(uniqueInstance==null){
                    uniqueInstance = new ArtikelDbStrategyFactory();
                }
            }
        }
        return uniqueInstance;
    }
}

package model.db;

import model.domain.ArtikelDbEnum;

import java.lang.reflect.InvocationTargetException;

public class ArtikelDbStrategyFactory {
    private volatile static ArtikelDbStrategyFactory uniqueInstance = new ArtikelDbStrategyFactory();
    private ArtikelDbStrategyFactory() {
    }

    public ArtikelDbStrategy getArtikelDbStrategy(String typeDb, String typeLoadSave){
        ArtikelDbEnum dbEnum = ArtikelDbEnum.getSave(typeDb);
        String klasseNaam = dbEnum.getKlasseNaam();
        ArtikelDbStrategy artikelDbStrategy = null;
        try {
            Class handlerClass = Class.forName(klasseNaam);
            Object handlerObject = handlerClass.getConstructor(String.class).newInstance(typeLoadSave);
            artikelDbStrategy = (ArtikelDbStrategy) handlerObject;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("The requested page could not be found");
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

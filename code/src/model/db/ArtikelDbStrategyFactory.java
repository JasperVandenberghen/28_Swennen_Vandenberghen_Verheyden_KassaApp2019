package model.db;

import java.lang.reflect.InvocationTargetException;

public class ArtikelDbStrategyFactory {
    private volatile static ArtikelDbStrategyFactory uniqueInstance = new ArtikelDbStrategyFactory();
    private ArtikelDbStrategyFactory() {
    }

    public ArtikelDbStrategy getArtikelDbStrategy(String type){
        ArtikelDbStrategy artikelDbStrategy = null;
        try {
            Class handlerClass = Class.forName("model.db.ArtikelDb" + type);
            Object handlerObject = handlerClass.getConstructor().newInstance();
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

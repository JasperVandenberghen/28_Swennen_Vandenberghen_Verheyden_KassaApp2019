package model.db;

import java.lang.reflect.InvocationTargetException;

public class ArtikelDbStrategyFactory {

    public ArtikelDbStrategy getArtikelDbStrategy(String type){
        ArtikelDbStrategy artikelDbStrategy = null;
        try {
            Class handlerClass = Class.forName("model.db" + type + "LoadSave");
            Object handlerObject = handlerClass.getConstructor().newInstance();
            artikelDbStrategy = (ArtikelDbStrategy) handlerObject;
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            throw new RuntimeException("The requested page could not be found");
        }
        return artikelDbStrategy;
    }
}

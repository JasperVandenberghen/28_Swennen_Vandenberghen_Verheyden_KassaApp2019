package model.db;

import java.lang.reflect.InvocationTargetException;

public class LoadSaveFactory {
    private String typeLoadSave;

    public LoadSaveFactory(String typeLoadSave) {
        this.typeLoadSave = typeLoadSave;
    }

   public LoadSaveStrategy getLoadSave(String objectToRead){
        LoadSaveStrategy loadSaveStrategy = null;
       try {
           Class handlerClass = Class.forName("model.db" + objectToRead + this.typeLoadSave + "LoadSave");
           Object handlerObject = handlerClass.getConstructor().newInstance();
           loadSaveStrategy = (LoadSaveStrategy) handlerObject;
       } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
           throw new RuntimeException("The requested page could not be found");
       }
       return loadSaveStrategy;
   }
}

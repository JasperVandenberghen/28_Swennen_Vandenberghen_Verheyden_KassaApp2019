package model.db;

import java.lang.reflect.InvocationTargetException;

public class LoadSaveFactory {
    private String typeLoadSave;

    public LoadSaveFactory(String typeLoadSave) {
        this.typeLoadSave = typeLoadSave;
    }

   public LoadSave getLoadSave(String objectToRead){
        LoadSave loadSave = null;
       try {
           Class handlerClass = Class.forName("model.db" + objectToRead + this.typeLoadSave + "LoadSave");
           Object handlerObject = handlerClass.getConstructor().newInstance();
           loadSave = (LoadSave) handlerObject;
       } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
           throw new RuntimeException("The requested page could not be found");
       }
       return loadSave;
   }
}

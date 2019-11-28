package model.db;

import java.lang.reflect.InvocationTargetException;

public class LoadSaveStrategyFactory {

   public LoadSaveStrategy getLoadSave(String typeLoadSaveStrategy){
        LoadSaveStrategy loadSaveStrategy = null;
       try {
           Class handlerClass = Class.forName("model.db" + typeLoadSaveStrategy + "LoadSave");
           Object handlerObject = handlerClass.getConstructor().newInstance();
           loadSaveStrategy = (LoadSaveStrategy) handlerObject;
       } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
           throw new RuntimeException("The requested page could not be found");
       }
       return loadSaveStrategy;
   }
}

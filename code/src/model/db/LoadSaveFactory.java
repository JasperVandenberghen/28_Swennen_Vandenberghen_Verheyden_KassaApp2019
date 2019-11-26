package model.db;

import java.lang.reflect.InvocationTargetException;

public class LoadSaveFactory {


    public LoadSaveFactory() {
    }

   public LoadSave createLoadSave(String command){
       try {
           Class handlerClass = Class.forName("model.db" + command + "LoadSave");
           Object handlerObject = handlerClass.getConstructor().newInstance();
           handler = (RequestHandler) handlerObject;

       } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
           throw new RuntimeException("The requested page could not be found");
       }
       return handler;
   }
}

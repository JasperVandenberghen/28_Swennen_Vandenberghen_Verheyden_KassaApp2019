package db;

import model.domain.SaveEnum;



public class LoadSaveStrategyFactory {
    private volatile static LoadSaveStrategyFactory uniqueInstance = new LoadSaveStrategyFactory();

    private LoadSaveStrategyFactory() {
    }

    public LoadSaveStrategy getLoadSave(String typeLoadSaveStrategy){
        SaveEnum saveEnum = SaveEnum.getSave(typeLoadSaveStrategy);
        String klasseNaam = saveEnum.getKlasseNaam();
        LoadSaveStrategy loadSaveStrategy = null;
       try {
           Class dbClass = Class.forName(klasseNaam);
           Object dbObject = dbClass.newInstance();
           loadSaveStrategy = (LoadSaveStrategy) dbObject;
       } catch (Exception e) {
           throw new DbException("Geen strategie met deze naam gevonden.");
       }
       return loadSaveStrategy;
   }

    public static LoadSaveStrategyFactory getInstance(){
        if(uniqueInstance==null){
            synchronized (ArtikelDbStrategy.class){
                if(uniqueInstance==null){
                    uniqueInstance = new LoadSaveStrategyFactory();
                }
            }
        }
        return uniqueInstance;
    }
}

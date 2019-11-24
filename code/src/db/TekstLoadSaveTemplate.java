package db;

import java.util.List;

abstract class TekstLoadSaveTemplate {

    final void loadSaveTemplate(){
        load();
        save();
    }

    abstract List load();

    abstract void save(List list);

    abstract void save();

    final void concreteOperation(){

    }

    void hook(){}


}

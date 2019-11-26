package model.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

abstract class TekstLoadSaveTemplate {

    abstract String getFile();

    final void loadSaveTemplate(){
        load();
        save();
    }

    abstract List load();

    public Scanner initializeLoad() throws FileNotFoundException {
        String file = getFile();
        File abstractFile = new File(file);
        Scanner scannerFile = new Scanner(abstractFile);
        return scannerFile;
    }

    abstract void save(List list);

    abstract void save();

    final void concreteOperation(){

    }

    void hook(){}


}

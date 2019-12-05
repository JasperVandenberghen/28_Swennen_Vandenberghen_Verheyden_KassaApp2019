package model.db;

import model.domain.DomainException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract class TekstLoadSaveTemplate implements LoadSaveStrategy{

    public abstract String getFile();

    public ArrayList<?> load() {
        try {
            Scanner scannerFile = initializeLoad();
            ArrayList<ArrayList<String>> info = new ArrayList<ArrayList<String>>();
            while (scannerFile.hasNextLine()) {
                Scanner scannerEenLijn = new Scanner(scannerFile.nextLine()).useDelimiter(",");
                ArrayList<String> rij = new ArrayList<String>();
                while(scannerEenLijn.hasNext()){
                    String woord = scannerEenLijn.next();
                    rij.add(woord);
                }
                info.add(rij);
            }
            return info;
        } catch(FileNotFoundException ex){
            throw new DomainException("Fout bij inlezen", ex);
        }
    }

    public Scanner initializeLoad() throws FileNotFoundException {
        String file = getFile();
        File abstractFile = new File(file);
        Scanner scannerFile = new Scanner(abstractFile);
        return scannerFile;
    }

    public void save(List list){

    }


}

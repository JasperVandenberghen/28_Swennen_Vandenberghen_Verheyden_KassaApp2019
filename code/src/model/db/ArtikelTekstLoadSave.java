package model.db;



import model.domain.Artikel;
import model.domain.DomainException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class ArtikelTekstLoadSave extends TekstLoadSaveTemplate implements LoadSaveStrategy {
    public ArtikelTekstLoadSave() {

    }

    @Override
    public String getFile() {
        return "artikel.txt";
    }

    @Override
    public ArrayList<Artikel> load(){

        ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) super.load();
        return ConversionToObjectList.convertToArtikelList(data);
    }

    @Override
    public void save(List list){
        File artikelFile = new File(getFile());
        try{
            PrintWriter writer = new PrintWriter(artikelFile);

            for(Object a: list){
                writer.println(a.toString());
            }
            writer.close();

        } catch (FileNotFoundException ex){
            throw new DomainException("Fout bij wegschrijven", ex);
        }
    }

}

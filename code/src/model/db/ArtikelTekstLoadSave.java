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
    public void save(String file, List list){
        File artikelFile = new File(file);
        try{

            Artikel nutella = new Artikel("14", "Nutella", "beleg",3.5, 10);
            Artikel lays = new Artikel("15", "Lays Naturel", "chips",2.7,13);
            Artikel dash = new Artikel("16", "Dash","wasproduct", 5.5, 15);
            Artikel frosties = new Artikel("17", "Frosties","cornfalkes", 3.8, 17);
            list.add(nutella);
            list.add(lays);
            list.add(dash);
            list.add(frosties);


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

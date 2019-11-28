package model.db;



import model.domain.Artikel;
import model.domain.DomainException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class ArtikelTekstLoadSave extends TekstLoadSaveTemplate {
    LoadSaveStrategy loadSaveStrategy;
    public ArtikelTekstLoadSave(LoadSaveStrategy l) {
        this.loadSaveStrategy = l;
    }

    @Override
    String getFile() {
        return "artikel.txt";
    }

    @Override
    public Map<String, Artikel> load(){
        ArrayList<ArrayList<String>> data = loadSaveStrategy.load();
        Map<String, Artikel> artikelen = new HashMap<>();

        for(ArrayList<String> rij: data) {
            ListIterator<String> it = rij.listIterator();
            String artikelId = it.next();
            String artikelNaam = it.next();
            String artikelCat = it.next();
            String prijsString = it.next();
            String voorraadString = it.next();
            double prijs = Double.parseDouble(prijsString);
            int voorraad = Integer.parseInt(voorraadString);
            Artikel artikel = new Artikel(artikelId,artikelNaam, artikelCat, prijs,voorraad);
            artikelen.put(artikelId, artikel);
        }
        return artikelen;
    }

    @Override
    public void save(List list){
        File artikelFile = new File("artikel.txt");
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

package model.db;



import model.Artikel;
import model.DomainException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ArtikelTekstLoadSave extends TekstLoadSaveTemplate{


    @Override
    String getFile() {
        return "artikel.txt";
    }

    @Override
    public List load(){

        List<Artikel> artikelen = new ArrayList<>();

        try{
            Scanner scannerFile = initializeLoad();
            while(scannerFile.hasNextLine()){
                Scanner scannerLijn = new Scanner(scannerFile.nextLine());
                scannerLijn.useDelimiter(",");
                String artikelId = scannerLijn.next();
                String artikelNaam = scannerLijn.next();
                String artikelCat = scannerLijn.next();
                String prijsString = scannerLijn.next();
                String voorraadString = scannerLijn.next();
                double prijs = Double.parseDouble(prijsString);
                int voorraad = Integer.parseInt(voorraadString);
                Artikel artikel = new Artikel(artikelId,artikelNaam, artikelCat, prijs,voorraad);
                artikelen.add(artikel);
            }
            return artikelen;
        }
        catch(FileNotFoundException ex){
            throw new DomainException("Fout bij inlezen", ex);
        }
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

    @Override
    void save() {

    }
}

package db;

import domain.Artikel;
import domain.DomainException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class ArtikelDb {
    private Map<String, Artikel> artikelMap;

    public ArtikelDb() {
        artikelMap = new HashMap<>();
    }

    public List<Artikel> leesArtikelen(){
        List<Artikel> artikelen = new ArrayList<>();
        File artikelFile = new File("artikel.txt");
        try{
            Scanner scannerFile = new Scanner(artikelFile);
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

    public List<Artikel> schrijfArtikelen(){
        List <Artikel> artikelenList = new ArrayList<>();
        File artikelFile = new File("artikel.txt");
        try{

            Artikel nutella = new Artikel("14", "Nutella", "beleg",3.5, 10);
            Artikel lays = new Artikel("15", "Lays Naturel", "chips",2.7,13);
            Artikel dash = new Artikel("16", "Dash","wasproduct", 5.5, 15);
            Artikel frosties = new Artikel("17", "Frosties","cornfalkes", 3.8, 17);
            artikelenList.add(nutella);
            artikelenList.add(lays);
            artikelenList.add(dash);
            artikelenList.add(frosties);


            PrintWriter writer = new PrintWriter(artikelFile);

            for(Artikel a: artikelenList){
                writer.println(a.toString());
            }

        } catch (FileNotFoundException ex){
            throw new DomainException("Fout bij wegschrijven", ex);
        }
        return artikelenList;
    }


}

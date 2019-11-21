package db;

import domain.Artikel;
import domain.DomainException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ArtikelDb {
    Map<String, Artikel> artikelMap;

    public ArtikelDb(Map<String, Artikel> artikelMap) {
        artikelMap = new HashMap<>();
        this.artikelMap = artikelMap;
    }

    public void leesArtikelen(){
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
                artikelMap.put(artikelId, artikel);
            }
            System.out.println(artikelMap.toString());
        }
        catch(FileNotFoundException ex){
            throw new DomainException("Fout bij inlezen", ex);
        }
    }

    public void schrijfArtikelen(){
        File artikelen = new File("artikel.txt");
        try{
            PrintWriter writer = new PrintWriter(artikelen);
            writer.println("14,Nutella,beleg,3.5,10");
            writer.println("15,Lays Naturel,chips,2.7,13");
            writer.println("16,Dash,wasproduct,5.5,15");
            writer.println("17,Frosties,cornflakes,3.8,17");
            writer.close();
        } catch (FileNotFoundException ex){
            throw new DomainException("Fout bij wegschrijven", ex);
        }
    }


}

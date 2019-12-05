package model.db;

import model.domain.Artikel;

import java.util.ArrayList;
import java.util.ListIterator;

public class ConversionToObjectList {
    public static ArrayList<Artikel> convertToArtikelList(ArrayList<ArrayList<String>> data){
        ArrayList<Artikel> artikelen = new ArrayList<>();

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
            artikelen.add(artikel);
        }
        return artikelen;
    }
}

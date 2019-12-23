package database;

import javafx.collections.FXCollections;
import model.domain.Artikel;
import model.domain.ArtikelContainer;

import java.util.*;

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

    public static List<ArtikelContainer> convertArtikelenMapToObservableArtikelenList(Map<String, Artikel> artikelMap){
        List<ArtikelContainer> observableList = FXCollections.observableArrayList();
        Iterator it = artikelMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry thisEntry = (Map.Entry) it.next();
            ArtikelContainer value = new ArtikelContainer((Artikel)thisEntry.getValue());
            observableList.add(value);
        }
        return observableList;
    }
}

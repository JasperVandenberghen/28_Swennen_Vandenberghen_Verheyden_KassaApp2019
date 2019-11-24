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

    public Map<String, Artikel> getArtikelMap() {
        return artikelMap;
    }
}

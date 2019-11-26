package model.db;

import model.Artikel;

import java.util.Map;

public interface Db {

    Artikel get(String id);

    Map<String, Artikel> getAll();

    void add(Artikel artikel);

    void update(Artikel artikel);

    void delete(String id);

    int getAantalArtikelen();

}

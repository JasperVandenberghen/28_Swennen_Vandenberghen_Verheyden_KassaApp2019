package database;

import model.domain.Artikel;

import java.util.List;

public interface ArtikelDbStrategy {

    Artikel get(String id);

    List<Artikel> getAll();

    void add(Artikel artikel);

    void update(Artikel artikel);

    void delete(String id);

    int getAantalArtikelen();

    void saveArtikelen(List<Artikel> artikels);
}

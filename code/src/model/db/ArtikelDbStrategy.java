package model.db;

import model.domain.Artikel;

import java.util.Map;

public interface ArtikelDbStrategy {

    Artikel get(String id);

    Map<String, Artikel> getAll();




}

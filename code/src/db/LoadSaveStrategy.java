package db;


import model.domain.Artikel;

import java.util.List;

public interface LoadSaveStrategy {

    List<?> load();
    void save(List list);
    String getFile();
}

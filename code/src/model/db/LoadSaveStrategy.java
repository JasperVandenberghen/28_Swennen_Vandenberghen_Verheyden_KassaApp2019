package model.db;


import java.util.List;

public interface LoadSaveStrategy {

    List<?> load();
    void save(String file, List list);
    String getFile();

}

package database;


import java.util.List;

public interface LoadSaveStrategy {

    List<?> load();
    void save(List list);
    String getFile();
}

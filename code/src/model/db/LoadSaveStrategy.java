package model.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface LoadSaveStrategy {

    List<ArrayList<String>> load();
    void save(List list);

}

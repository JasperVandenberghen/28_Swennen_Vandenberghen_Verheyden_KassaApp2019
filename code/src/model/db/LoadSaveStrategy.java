package model.db;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface LoadSaveStrategy {

    List<?> load();
    void save(List list);

}

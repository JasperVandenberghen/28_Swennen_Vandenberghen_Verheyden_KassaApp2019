package model.db;

import java.util.List;
import java.util.Map;

public interface LoadSave {

    Map load();
    void save(List list);

}

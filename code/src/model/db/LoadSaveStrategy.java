package model.db;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;

import java.io.IOException;
import java.util.List;

public interface LoadSaveStrategy {

    List<?> load();
    void save(String file, List list);
    String getFile();

}

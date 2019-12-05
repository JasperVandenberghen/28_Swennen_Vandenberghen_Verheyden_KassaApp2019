package model.db;

import jxl.read.biff.BiffException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface LoadSaveStrategy {

    List<?> load() throws IOException, BiffException;
    void save(List list);
    String getFile();

}

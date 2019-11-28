package excel;

import model.db.LoadSave;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ExcelLoadSaveStrategy implements LoadSave {
    ExcelPlugin excelPlugin;

    public ExcelLoadSaveStrategy(ExcelPlugin excelPlugin) {
        this.excelPlugin = excelPlugin;
    }

    @Override
    public ArrayList<ArrayList<String>> load() {
        return null;
    }

    @Override
    public void save(List list) {

    }
}

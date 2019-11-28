package excel;

import model.db.LoadSaveStrategy;

import java.util.ArrayList;
import java.util.List;

public class ArtikelExcelLoadSaveStrategy implements LoadSaveStrategy {
    ExcelPlugin excelPlugin;

    public ArtikelExcelLoadSaveStrategy(ExcelPlugin excelPlugin) {
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

package excel;

import model.db.ConversionToObjectList;
import model.db.LoadSaveStrategy;
import model.domain.Artikel;

import java.util.ArrayList;
import java.util.List;

public class ArtikelExcelLoadSaveStrategy implements LoadSaveStrategy {
    private ExcelPluginCode excelPlugin;

    public ArtikelExcelLoadSaveStrategy(ExcelPluginCode excelPlugin) {
        this.excelPlugin = excelPlugin;
    }

    @Override
    public ArrayList<Artikel> load(){
        ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) excelPlugin.load();
        return ConversionToObjectList.convertToArtikelList(data);
    }

    @Override
    public void save(List list) {

    }
}

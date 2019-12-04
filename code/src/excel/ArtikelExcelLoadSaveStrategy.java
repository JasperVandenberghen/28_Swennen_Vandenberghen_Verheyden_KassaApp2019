package excel;

import jxl.read.biff.BiffException;
import model.db.ConversionToObjectList;
import model.db.LoadSaveStrategy;
import model.domain.Artikel;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArtikelExcelLoadSaveStrategy implements LoadSaveStrategy {
    private ExcelPluginCode excelPlugin;
    private String file;


    public ArtikelExcelLoadSaveStrategy(ExcelPluginCode excelPlugin) {
        this.excelPlugin = excelPlugin;
    }

    @Override
    public ArrayList<Artikel> load() throws IOException, BiffException {
        ArrayList<ArrayList<String>> data = (ArrayList<ArrayList<String>>) excelPlugin.read(new File(getFile()));
        return ConversionToObjectList.convertToArtikelList(data);
    }



    @Override
    public void save(List list) {

    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}

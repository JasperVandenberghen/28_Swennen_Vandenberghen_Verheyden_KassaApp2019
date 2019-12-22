package excel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import db.ConversionToObjectList;
import db.LoadSaveStrategy;
import model.domain.Artikel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArtikelExcelLoadSaveStrategy implements LoadSaveStrategy {
    private ExcelPlugin excelPlugin = new ExcelPlugin();
    private String file;


    public ArtikelExcelLoadSaveStrategy() {

    }

    @Override
    public ArrayList<Artikel> load() {
        ArrayList<ArrayList<String>> data = null;
        try {
            data = excelPlugin.read(new File(getFile()));
        } catch (IOException | BiffException e) {
            e.printStackTrace();
        }
        return ConversionToObjectList.convertToArtikelList(data);

    }



    @Override
    public void save(List list) {
        ArrayList<ArrayList<String>> uitvoer = new ArrayList<>();

        List<Artikel> artikelen = list;

        for(Artikel artkl: artikelen){
            ArrayList<String> artikelenStrings = new ArrayList<>();

            artikelenStrings.add(artkl.getArtikelId());
            artikelenStrings.add(artkl.getArtikelNaam());
            artikelenStrings.add(artkl.getCategorie());
            artikelenStrings.add(Double.toString(artkl.getPrijs()));
            artikelenStrings.add(Integer.toString(artkl.getVoorraad()));

            uitvoer.add(artikelenStrings);
        }
        try {
            excelPlugin.write(new File(getFile()), uitvoer);
        } catch (BiffException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        }
    }


    public String getFile() {
        return "code/artikel.xls";
    }

    public void setFile(String file) {
        this.file = file;
    }
}

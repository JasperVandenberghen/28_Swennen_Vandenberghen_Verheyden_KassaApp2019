package excel;

import jxl.read.biff.BiffException;
import jxl.write.WriteException;
import model.db.ConversionToObjectList;
import model.db.LoadSaveStrategy;
import model.domain.Artikel;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ArtikelExcelLoadSaveStrategy implements LoadSaveStrategy {
    private ExcelPlugin excelPlugin;
    private String file;


    public ArtikelExcelLoadSaveStrategy() {
        excelPlugin = new ExcelPlugin();
    }

    @Override
    public ArrayList<Artikel> load() throws IOException, BiffException {
        ArrayList<ArrayList<String>> data = excelPlugin.read(new File(getFile()));
        return ConversionToObjectList.convertToArtikelList(data);
    }



    @Override
    public void save(String file, List list) throws WriteException, IOException, BiffException {
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
        excelPlugin.write(new File(file), uitvoer);
    }


    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }
}

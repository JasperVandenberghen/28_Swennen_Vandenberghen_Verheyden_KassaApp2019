package model.domain;

public enum SaveEnum {
    EXCEL ("Excel", "excel.ArtikelExcelLoadSaveStrategy"),
    TXT ("Text", "db.ArtikelTekstLoadSave");


    private final String omschrijving;
    private final String klasseNaam;

    SaveEnum(String omschrijving, String klasseNaam) {
        this.omschrijving = omschrijving;
        this.klasseNaam = klasseNaam;
    }

    public String getOmschrijving() { return omschrijving; }
    public String getKlasseNaam() { return klasseNaam; }
}

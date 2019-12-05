package controller;

import model.db.ConversionToObjectList;
import model.domain.Artikel;
import model.domain.ArtikelContainer;
import model.domain.Observer;
import view.panels.KassaKassierPane;

import java.util.List;
import java.util.Map;

public class KassaKassierController implements Observer {
        private Map<String, Artikel> artikelMap;
        private List<ArtikelContainer> artikelenInKassa;
        private KassaKassierPane kassaKassierPane;
        private double totaal;

        public KassaKassierController(Map<String, Artikel> artikelMap) {
            this.artikelMap = artikelMap;
            artikelenInKassa = ConversionToObjectList.convertArtikelenMapToObservableArtikelenList(artikelMap);
        }

        public void setView(KassaKassierPane kassaKassierPane){
            this.kassaKassierPane = kassaKassierPane;
        }

        @Override
        public void update(String artikelId) {
            ArtikelContainer artikelContainer = new ArtikelContainer(artikelMap.get(artikelId));
            artikelenInKassa.add(artikelContainer);
            totaal += artikelContainer.getPrijs();
            this.kassaKassierPane.setTotaal(totaal);
        }
}

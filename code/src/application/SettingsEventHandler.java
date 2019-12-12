package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import model.db.LoadSaveStrategyFactory;
import model.domain.KortingsFactory;
import view.panels.KassaSettingsPane;

public class SettingsEventHandler implements EventHandler<ActionEvent>, ChangeListener<String> {
    private KassaSettingsPane kassaSettingsPane;
    private LoadSaveStrategyFactory loadSaveStrategy;
    private KortingsFactory kortingsFactory;


    @Override
    public void changed(ObservableValue ov, String string, String omschrijving) {
        loadSaveStrategy.getLoadSave(omschrijving);


    }

    @Override
    public void handle(ActionEvent event) {

    }
}

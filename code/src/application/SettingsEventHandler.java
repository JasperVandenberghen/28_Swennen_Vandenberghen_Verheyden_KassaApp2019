package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import db.LoadSaveStrategyFactory;
import model.domain.KortingsFactory;
import view.panels.SettingsPane;

public class SettingsEventHandler implements EventHandler<ActionEvent>, ChangeListener<String> {
    private SettingsPane settingsPane;
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

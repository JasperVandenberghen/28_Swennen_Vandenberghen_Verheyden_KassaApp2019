package application;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import view.panels.KassaKassierPane;

public class KortingHandler implements EventHandler<ActionEvent>, ChangeListener<String> {
    private KassaKassierPane kassaKassierPane;

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {

    }

    @Override
    public void handle(ActionEvent event) {

    }
}

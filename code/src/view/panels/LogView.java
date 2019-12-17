package view.panels;

import controller.LogController;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import model.domain.VerkoopLog;
import model.domain.VerkoopLog;

import java.util.List;

public class LogView extends GridPane {
    protected TableView<VerkoopLog> table;
    protected List<VerkoopLog> observableList;

    public LogView(LogController logController) {
        this.setPadding(new Insets(5, 5, 5, 5));
        this.setVgap(5);
        this.setHgap(5);

        this.table = new TableView<>();
        logController.setView(this);
    }

    public void setObservableList(List<VerkoopLog> observableList) {
        this.observableList = observableList;
        observableList.size();
        table.setItems((ObservableList<VerkoopLog>)observableList);
        TableColumn<VerkoopLog, String> datum = new TableColumn<VerkoopLog, String>("Datum");
        datum.setMinWidth(200);
        datum.setCellValueFactory(new PropertyValueFactory<VerkoopLog, String>("datum"));
        TableColumn<VerkoopLog, String> totaal = new TableColumn<VerkoopLog, String>("Totaal");
        totaal.setMinWidth(200);
        totaal.setCellValueFactory(new PropertyValueFactory<VerkoopLog, String>("totaal"));
        TableColumn<VerkoopLog, String> korting = new TableColumn<VerkoopLog, String>("Korting");
        korting.setMinWidth(40);
        korting.setCellValueFactory(new PropertyValueFactory<VerkoopLog, String>("korting"));
        TableColumn<VerkoopLog, String> eindTotaal = new TableColumn<VerkoopLog, String>("Eindtotaal");
        eindTotaal.setMinWidth(40);
        eindTotaal.setCellValueFactory(new PropertyValueFactory<VerkoopLog, String>("eindTotaal"));
        table.getColumns().addAll(datum, totaal, korting, eindTotaal);
        this.add(table, 0,0);
    }

}

package model.domain;


import javafx.collections.FXCollections;

import java.util.List;

public class LogHandler {
    private List<VerkoopLog> logBook;

    public LogHandler() {
        logBook = FXCollections.observableArrayList();
    }

    public void addLog(double totaal, double korting, double eindTotaal){
        logBook.add(new VerkoopLog(totaal, korting, eindTotaal));
    }

    public List<VerkoopLog> getLogBook() {
        return logBook;
    }
}

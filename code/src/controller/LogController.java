package controller;

import model.domain.LogHandler;
import view.panels.LogView;

public class LogController {
    LogHandler logHandler;
    LogView view;

    public LogController(LogHandler logHandler) {
        this.logHandler = logHandler;
    }

    public void setView(LogView view) {
        this.view = view;
        this.view.setObservableList(logHandler.getLogBook());
    }

    public LogView getView() {
        return view;
    }
}

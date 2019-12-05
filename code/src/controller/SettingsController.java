package controller;

import view.panels.KassaSettingsPane;

public class SettingsController {
    private KassaSettingsPane kassaSettingsPane;


    public void setView(KassaSettingsPane kassaSettingsPane){this.kassaSettingsPane = kassaSettingsPane;}

    public KassaSettingsPane getKassaSettingsPane() {
        return kassaSettingsPane;
    }

    public SettingsController() {
    }
}

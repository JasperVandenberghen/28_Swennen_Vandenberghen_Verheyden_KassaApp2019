package model.domain;


import jxl.write.DateTime;

public class VerkoopLogHandler {

    DateTime datum;
    double totaal;
    double korting;
    double eindTotaal;

    public VerkoopLogHandler(DateTime datum, double totaal, double korting, double eindTotaal) {
        this.datum = datum;
        this.totaal = totaal;
        this.korting = korting;
        this.eindTotaal = eindTotaal;
    }
}

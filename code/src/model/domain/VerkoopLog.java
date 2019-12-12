package model.domain;


import jxl.write.DateTime;

public class VerkoopLog {

    DateTime datum;
    double totaal;
    double korting;
    double eindTotaal;

    public VerkoopLog(DateTime datum, double totaal, double korting, double eindTotaal) {
        this.datum = datum;
        this.totaal = totaal;
        this.korting = korting;
        this.eindTotaal = eindTotaal;
    }
}

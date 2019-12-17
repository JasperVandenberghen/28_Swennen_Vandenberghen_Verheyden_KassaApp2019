package model.domain;



import java.time.LocalDateTime;

public class VerkoopLog {

    LocalDateTime datum;
    double totaal;
    double korting;
    double eindTotaal;

    public VerkoopLog(double totaal, double korting, double eindTotaal) {
        this.datum = LocalDateTime.now();
        this.totaal = totaal;
        this.korting = korting;
        this.eindTotaal = eindTotaal;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public double getTotaal() {
        return totaal;
    }

    public double getKorting() {
        return korting;
    }

    public double getEindTotaal() {
        return eindTotaal;
    }
}

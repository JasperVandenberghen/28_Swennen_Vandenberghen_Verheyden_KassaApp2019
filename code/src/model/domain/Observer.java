package model.domain;

public interface Observer {
    public void update(String totaal, String korting, String eindTotaal);
}

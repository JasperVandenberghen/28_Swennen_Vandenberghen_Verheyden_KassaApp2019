package model.domain;

public interface Observer {
    public void update(String totaal);
    public void setAfrekenInfo(String korting, String eindTotaal);
}

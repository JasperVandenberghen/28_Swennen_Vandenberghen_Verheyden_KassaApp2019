package model.domain;

public interface Observer {
    public void update(String artikelId);
    public void remove(String artikelId);
}

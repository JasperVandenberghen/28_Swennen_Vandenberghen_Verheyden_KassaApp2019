package model.domain;

public interface ObservableArtikelenInShop {
    public void registerObserver(ObserverArtikelenInShop o);
    public void removeObserver(ObserverArtikelenInShop o);
    public void notifyObserversArtikelenInShop();
}

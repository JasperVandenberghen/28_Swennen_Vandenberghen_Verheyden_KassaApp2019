package model.domain;

public interface ObservableButtonText {
    public void registerObserverButtonText(ObserverButtonText o);
    public void removeObserverButtonText(ObserverButtonText o);
    public void notifyObserversButtonText();
}

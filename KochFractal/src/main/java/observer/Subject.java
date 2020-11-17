package observer;

public interface Subject {
    void addListener(Listener listener);
    void notifyListeners(Object object);
}

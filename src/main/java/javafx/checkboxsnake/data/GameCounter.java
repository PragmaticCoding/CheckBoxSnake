package javafx.checkboxsnake.data;

public class GameCounter {

    private long counter = 1;

    public long getCount() {
        return counter;
    }

    public void resetCounter() {
        counter = 1;
    }

    public void incrementCount() {
        counter++;
    }
}

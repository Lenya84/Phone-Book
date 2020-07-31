package phonebook;

public class Timer {

    private long lStartTime;
    private long lEndTime;
    public long milliseconds;
    public long seconds;
    public long minutes;

    public void start() {
        lStartTime = System.currentTimeMillis();
    }

    public void stop() {
        lEndTime = System.currentTimeMillis();

        long output = lEndTime - lStartTime;

        milliseconds = output % 1000;
        seconds = (int) (output / 1000) % 60 ;
        minutes = (int) ((output / (1000*60)) % 60);
    }


}

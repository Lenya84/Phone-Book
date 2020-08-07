package phonebook;

public class Timer {

    private long lStartTime;
    private long lEndTime;
    public long output;
    public long milliseconds;
    public long seconds;
    public long minutes;

    public void start() {
        lStartTime = System.currentTimeMillis();
    }

    public void stop() {
        lEndTime = System.currentTimeMillis();

        output = lEndTime - lStartTime;

        milliseconds = output % 1000;
        seconds = (int) (output / 1000) % 60 ;
        minutes = (int) ((output / (1000*60)) % 60);
    }

    public String getTime() {
        return String.format("%d min. %d sec. %d ms.",
                minutes, seconds, milliseconds);
    }

    public static String getSumTime (Timer firstTimer, Timer secondTimer) {
        long output = firstTimer.output + secondTimer.output;

        long milliseconds = output % 1000;
        long seconds = (int) (output / 1000) % 60 ;
        long minutes = (int) ((output / (1000*60)) % 60);

        return String.format("%d min. %d sec. %d ms.",
                minutes, seconds, milliseconds);
    }


}

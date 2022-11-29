public class TimeCalculation {

    private int hour;
    private int minute;

    public TimeCalculation(int hour, int minute) {
        this.hour = (hour + 21) % 24;
        this.minute = (minute + 13) % 60;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }
}

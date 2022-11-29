public class TimeAndRequestID {

    private int hour;
    private int minute;
    private String requestId;

    public TimeAndRequestID(int hour, int minute, String requestId) {
        this.hour = hour;
        this.minute = minute;
        this.requestId = requestId;
    }

    public int getHour() {
        return hour;
    }

    public int getMinute() {
        return minute;
    }

    public String getRequestId() {
        return requestId;
    }
}

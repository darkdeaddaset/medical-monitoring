package liga.medical.medicalmonitoring.core.model;

public enum NameQueue {
    DAILY("DAILY_QUEUE"),
    ALERT("ALERT_QUEUE"),
    ERROR("ERROR_QUEUE");

    private final String str;
    NameQueue(String str) {
        this.str = str;
    }

    public java.lang.String getStr() {
        return str;
    }
}

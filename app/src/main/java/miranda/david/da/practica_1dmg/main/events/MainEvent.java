package miranda.david.da.practica_1dmg.main.events;

public class MainEvent {

    public final static int ON_LOG_OUT = 1;


    private int eventType;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

}

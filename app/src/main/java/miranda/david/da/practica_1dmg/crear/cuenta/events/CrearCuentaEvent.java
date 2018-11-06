package miranda.david.da.practica_1dmg.crear.cuenta.events;

public class CrearCuentaEvent {

    public final static int ON_CREATE_ACCOUNT_SUCESS = 1;

    public final static int ON_CREATE_ACCOUNT_ERROR = 2;

    private int eventType;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }
}

package miranda.david.da.practica_1dmg.main.events;

import miranda.david.da.practica_1dmg.usuario.Usuario;

public class MainEvent {

    public final static int ON_LOG_OUT = 1;
    public final static int ON_OBTENER_DATOS = 2;
    public final static int ON_OBTENER_DATOS_ERROR = 3;



    private int eventType;
    private Usuario usuario;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}

package miranda.david.da.practica_1dmg.main;

import android.util.Log;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import miranda.david.da.practica_1dmg.R;
import miranda.david.da.practica_1dmg.main.events.MainEvent;

public class MainPresenterImpl implements MainPresenter {

    private MainInteractor mainInteractor;
    private MainView mainView;
    private EventBus eventBus;

    private static final String TAG = "ValorDesLogueo";


    public MainPresenterImpl(MainView mainView){
        mainInteractor = new MainInteractorImpl();
        this.mainView = mainView;
        this.eventBus = EventBus.getDefault();
    }

    //Método que cierra la sesion actual
    @Override
    public void cerrarSesion() {
        mainInteractor.cerrarSesion();
    }

    //Método que obtiene el email del usuario para pedir los datos del mismo a la BD
    @Override
    public void obtenerUsuario(String email){
        mainInteractor.obtenerUsuario(email);
    }

    //Método que elimina al usuario de la BD
    @Override
    public void eliminarUsuario(String id){
        mainInteractor.eliminarUsuario(id);
    }

    //Método para actualizar los datos del usuario en la BD
    @Override
    public void actualizarDatos(String id, String email, String username){
        mainInteractor.actualizarDatos(id, email, username);
    }

    @Override
    public void onStart() {
        eventBus.register(this);
    }

    @Override
    public void onStop() {
        //mainView = null;
        eventBus.unregister(this);
    }

    //Método que ejecuta diferentes métodos según el estado del EventBus
    @Override
    @Subscribe
    public void onEventLoginThread(MainEvent event) {
        mainView.ocultarCargando();
        Log.d(TAG, "valor:" + event.toString());
        switch (event.getEventType()) {
            case MainEvent.ON_LOG_OUT:
                mainView.irALogin();
                break;
            case MainEvent.ON_OBTENER_DATOS:
                mainView.pintarUsuario(event.getUsuario());
                break;
            case MainEvent.ON_OBTENER_DATOS_ERROR:
                mainView.errorObtenerDatos();
                break;
            case MainEvent.ON_BORRAR_USUARIO_CORRECTO:
                mainView.eliminarUsuarioSatisfactorio();
                break;
            case MainEvent.ON_BORRAR_USUARIO_ERROR:
                mainView.eliminarUsuarioError();
                break;
            case MainEvent.ON_DATOS_ACTUALIZADOS_CORRECTO:
                mainView.mensajeActualizarDatos();
                mainView.pintarUsuario(event.getUsuario());
                break;
            case MainEvent.ON_DATOS_ACTUALIZADOS_ERROR:
                mainView.errorObtenerDatos();
                break;

        }
    }
}

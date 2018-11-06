package miranda.david.da.practica_1dmg.crear.cuenta;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import miranda.david.da.practica_1dmg.crear.cuenta.events.CrearCuentaEvent;


public class CrearCuentaPresenterImpl implements CrearCuentaPresenter {

    private CrearCuentaInteractor crearCuentaInteractor;
    private CrearCuentaView crearCuentaView;
    private EventBus eventBus;

    private static final String TAG = "ValorCrearCuenta";


    public CrearCuentaPresenterImpl (CrearCuentaView crearCuentaView){
        crearCuentaInteractor = new CrearCuentaInteractorImpl();
        this.crearCuentaView = crearCuentaView;
        this.eventBus = EventBus.getDefault();
    }

    @Override
    public void crearCuenta(String email, String password) {
        crearCuentaInteractor.crearCuenta(email, password);
    }

    @Override
    public void onStart() {
        eventBus.register(this);
    }

    @Override
    public void onStop() {
        //crearCuentaView = null;
        eventBus.unregister(this);
    }

    @Override
    @Subscribe
    public void onEventLoginThread(CrearCuentaEvent event) {
        crearCuentaView.ocultarCargando();
        Log.d(TAG, "valor:" + event.toString());
        switch (event.getEventType()) {
            case CrearCuentaEvent.ON_CREATE_ACCOUNT_SUCESS:
                crearCuentaView.irAMain();
                break;
            case CrearCuentaEvent.ON_CREATE_ACCOUNT_ERROR:
                crearCuentaView.mostrarErrorCrearCuenta();
                crearCuentaView.limpiarFormulario();
                break;
        }
    }


}

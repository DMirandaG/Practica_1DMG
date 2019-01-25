package miranda.david.da.practica_1dmg.login;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import miranda.david.da.practica_1dmg.login.events.LoginEvent;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginInteractor loginInteractor;
    private LoginView loginView;
    private EventBus eventBus;

    private static final String TAG = "ValorLogueo";



    public LoginPresenterImpl(LoginView loginView) {
        loginInteractor = new LoginInteractorImpl();
        this.loginView = loginView;
        this.eventBus = EventBus.getDefault();
    }

    //Envío de los datos para iniciar sesion
    @Override
    public void login(String email, String password) {
        loginInteractor.login(email, password);
    }

    //Inicio del bus de eventos
    @Override
    public void onStart() {
        eventBus.register(this);
    }

    @Override
    public void onStop() {
        //loginView = null;
        eventBus.unregister(this);
    }

    //Método que ejecuta diferentes métodos según el estado del EventBus
    @Override
    @Subscribe
    public void onEventLoginThread(LoginEvent event) {
        loginView.ocultarCargando();
        Log.d(TAG, "valor:" + event.toString());

        switch (event.getEventType()) {
            case LoginEvent.ON_LOG_IN_SUCCESS:
                loginView.irAMain();
                break;
            case LoginEvent.ON_LOG_IN_ERROR:
                loginView.mostrarErrorLogin();
                loginView.limpiarFormulario();
                break;
            case LoginEvent.ON_RESET_PASSWORD_SUCESS:
                //loginView.mostrarResetearContrasenaEnviada();
                break;
            case LoginEvent.ON_RESET_PASSWORD_ERROR:
                //loginView.mostrarErrorResetearContrasena();
                loginView.limpiarFormulario();
                break;
        }
    }
}

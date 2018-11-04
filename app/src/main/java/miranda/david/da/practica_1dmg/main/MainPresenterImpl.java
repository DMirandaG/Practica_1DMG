package miranda.david.da.practica_1dmg.main;

import android.util.Log;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import miranda.david.da.practica_1dmg.login.events.LoginEvent;

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

    @Override
    public void cerrarSesion() {
        mainInteractor.cerrarSesion();
    }

    @Override
    public void onStart() {
        eventBus.register(this);
    }

    @Override
    public void onStop() {
        mainView = null;
        eventBus.unregister(this);
    }


    @Override
    @Subscribe
    public void onEventLoginThread(LoginEvent event) {
        mainView.ocultarCargando();
        Log.d(TAG, "valor:" + event.toString());

        switch (event.getEventType()) {
            case LoginEvent.ON_LOG_OUT:
                mainView.irALogin();
                break;

        }
    }
}

package miranda.david.da.practica_1dmg.main;

import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;

import miranda.david.da.practica_1dmg.login.events.LoginEvent;

public class MainRepositoryImpl implements MainRepository {


    @Override
    public void cerrarSesion(){
        FirebaseAuth.getInstance().signOut();
        postEvent(LoginEvent.ON_LOG_OUT);
    }


    private void postEvent(int type) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        EventBus.getDefault().post(loginEvent);
    }
}

package miranda.david.da.practica_1dmg.main;

import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;

import miranda.david.da.practica_1dmg.main.events.MainEvent;

public class MainRepositoryImpl implements MainRepository {


    @Override
    public void cerrarSesion(){
        FirebaseAuth.getInstance().signOut();
        postEvent(MainEvent.ON_LOG_OUT);
    }



    private void postEvent(int type) {
        MainEvent mainEvent = new MainEvent();
        mainEvent.setEventType(type);
        EventBus.getDefault().post(mainEvent);
    }
}

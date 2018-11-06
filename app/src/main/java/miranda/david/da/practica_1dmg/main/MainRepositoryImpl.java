package miranda.david.da.practica_1dmg.main;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import org.greenrobot.eventbus.EventBus;

import miranda.david.da.practica_1dmg.main.events.MainEvent;

public class MainRepositoryImpl implements MainRepository {



    @Override
    public void cerrarSesion(){
        FirebaseAuth.getInstance().signOut();
        postEvent(MainEvent.ON_LOG_OUT);
    }

    @Override
    public void obtenerUsuario(String email){
        final DatabaseReference usuarioRef = FirebaseDatabase.getInstance().getReference().child("usuarios");
        Query query = usuarioRef.child("usuarios").orderByChild("email").equalTo(email);


    }

    @Override
    public void eliminarUsuario(String id){
        final DatabaseReference usuarioRef = FirebaseDatabase.getInstance().getReference().child("usuarios").child(id);
        usuarioRef.removeValue();

    }

    private void postEvent(int type) {
        MainEvent mainEvent = new MainEvent();
        mainEvent.setEventType(type);
        EventBus.getDefault().post(mainEvent);
    }
}

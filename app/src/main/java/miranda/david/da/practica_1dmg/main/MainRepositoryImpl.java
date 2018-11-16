package miranda.david.da.practica_1dmg.main;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import miranda.david.da.practica_1dmg.main.events.MainEvent;
import miranda.david.da.practica_1dmg.usuario.Usuario;

public class MainRepositoryImpl implements MainRepository {

    private static final String TAG = "BorrarCuenta";

    private FirebaseUser user;


    public MainRepositoryImpl() {
        user = FirebaseAuth.getInstance().getCurrentUser();

    }


    @Override
    public void cerrarSesion(){
        FirebaseAuth.getInstance().signOut();
        postEvent2(MainEvent.ON_LOG_OUT);
    }

    @Override
    public void obtenerUsuario(final String email){
        final DatabaseReference usuarioRef = FirebaseDatabase.getInstance().getReference();
        usuarioRef.child("usuarios").orderByChild("email").equalTo(email).addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot datas : dataSnapshot.getChildren()) {
                    Usuario usuario = new Usuario();
                    usuario.setEmail(datas.child("email").getValue().toString());
                    usuario.setUsername(datas.child("username").getValue().toString());
                    usuario.setFoto(datas.child("foto").getValue().toString());
                    usuario.setId(datas.child("id").getValue().toString());
                    postEvent(MainEvent.ON_OBTENER_DATOS, usuario);
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                    postEvent2(MainEvent.ON_OBTENER_DATOS_ERROR);
            }
        });
    }



    @Override
    public void eliminarUsuario(final String id){
        final DatabaseReference usuarioRef = FirebaseDatabase.getInstance().getReference().child("usuarios").child(id);
        usuarioRef.removeValue();
        user.delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "User account deleted.");
                            postEvent2(MainEvent.ON_BORRAR_USUARIO_CORRECTO);
                        }else{
                            postEvent2(MainEvent.ON_BORRAR_USUARIO_ERROR);
                        }
                    }
                });

    }

    private void postEvent(int type, Usuario usuario) {
        MainEvent mainEvent = new MainEvent();
        mainEvent.setEventType(type);
        mainEvent.setUsuario(usuario);
        EventBus.getDefault().post(mainEvent);
    }

    private void postEvent2(int type) {
        MainEvent mainEvent = new MainEvent();
        mainEvent.setEventType(type);
        EventBus.getDefault().post(mainEvent);
    }
}

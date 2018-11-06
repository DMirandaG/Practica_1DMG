package miranda.david.da.practica_1dmg.crear.cuenta;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.greenrobot.eventbus.EventBus;

import miranda.david.da.practica_1dmg.crear.cuenta.events.CrearCuentaEvent;
import miranda.david.da.practica_1dmg.usuario.Usuario;


public class CrearCuentaRepositoryImpl implements CrearCuentaRepository {

    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;

    public CrearCuentaRepositoryImpl() {
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void crearCuenta (final String email, final String password) {
        try{
            Log.d(TAG, "emailCreado:" + email);
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "createUserWithEmail:success");
                                //postEvent(CrearCuentaEvent.ON_CREATE_ACCOUNT_SUCESS);
                                login(email, password);
                            } else {
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                postEvent(CrearCuentaEvent.ON_CREATE_ACCOUNT_ERROR);
                            }
                        }
                    });
        } catch (Exception e) {
            postEvent(CrearCuentaEvent.ON_CREATE_ACCOUNT_ERROR);
        }
    }

    private void login(final String email, String password) {
        try{
            Log.d(TAG, "signIn:" + email);
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "signInWithEmail:success");
                                postEvent(CrearCuentaEvent.ON_CREATE_ACCOUNT_SUCESS);
                                crearUserDB(email);
                            } else {
                                Log.w(TAG, "signInWithEmail:failure");
                                postEvent(CrearCuentaEvent.ON_CREATE_ACCOUNT_ERROR);
                            }
                        }
                    });
        } catch (Exception e) {
            postEvent(CrearCuentaEvent.ON_CREATE_ACCOUNT_ERROR);
        }

    }

    private void crearUserDB(String email) {
        final DatabaseReference usuarioRef = FirebaseDatabase.getInstance().getReference().child("usuarios");
        String key = usuarioRef.push().getKey();
        Usuario usuario = new Usuario();
        usuario.setEmail(email);
        usuario.setId(key);
        usuarioRef.child(key).setValue(usuario);
        usuarioRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postEvent(CrearCuentaEvent.ON_CREATE_ACCOUNT_SUCESS);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                postEvent(CrearCuentaEvent.ON_CREATE_ACCOUNT_ERROR);
            }
        });
    }


    private void postEvent(int type) {
        CrearCuentaEvent crearCuentaEvent = new CrearCuentaEvent();
        crearCuentaEvent.setEventType(type);
        EventBus.getDefault().post(crearCuentaEvent);
    }

}

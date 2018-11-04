package miranda.david.da.practica_1dmg.crear.cuenta;

import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;

import miranda.david.da.practica_1dmg.login.events.LoginEvent;

public class CrearCuentaRepositoryImpl implements CrearCuentaRepository {

    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;

    public CrearCuentaRepositoryImpl() {
        mAuth = FirebaseAuth.getInstance();
    }


    @Override
    public void crearCuenta (String email, String password) {
        try{
            Log.d(TAG, "emailCreado:" + email);
            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Log.d(TAG, "createUserWithEmail:success");
                                postEvent(LoginEvent.ON_CREATE_ACCOUNT_SUCESS);
                            } else {
                                Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                postEvent(LoginEvent.ON_LOG_IN_SUCCESS);
                            }
                        }
                    });
        } catch (Exception e) {
            postEvent(LoginEvent.ON_CREATE_ACCOUNT_ERROR);
        }
    }


    private void postEvent(int type) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        EventBus.getDefault().post(loginEvent);
    }

}

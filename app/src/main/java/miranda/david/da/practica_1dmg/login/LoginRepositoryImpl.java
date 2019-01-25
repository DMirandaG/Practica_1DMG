package miranda.david.da.practica_1dmg.login;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.greenrobot.eventbus.EventBus;

import miranda.david.da.practica_1dmg.login.events.LoginEvent;

public class LoginRepositoryImpl implements LoginRepository {


    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;

    public LoginRepositoryImpl() {
        mAuth = FirebaseAuth.getInstance();
    }

    //Método para iniciar sesion apartir de los datos obtenidos
    @Override
    public void login(String email, String password) {
        try{
            Log.d(TAG, "signIn:" + email);
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            postEvent(LoginEvent.ON_LOG_IN_SUCCESS);
                        } else {
                            Log.w(TAG, "signInWithEmail:failure");
                            postEvent(LoginEvent.ON_LOG_IN_ERROR);
                        }
                    }
            });
        } catch (Exception e) {
            postEvent(LoginEvent.ON_LOG_IN_ERROR);
        }
    }

    //EventBus para el tratamiento de los eventos
    private void postEvent(int type) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        EventBus.getDefault().post(loginEvent);
    }



}

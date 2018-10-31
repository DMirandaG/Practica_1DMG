package miranda.david.da.practica_1dmg.login;


import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginRepositoryImpl implements LoginRepository {


    private static final String TAG = "EmailPassword";
    private FirebaseAuth mAuth;

    public LoginRepositoryImpl() {
        mAuth = FirebaseAuth.getInstance();
    }

    @Override
    public void login(String email, String password){
        Log.d(TAG, "signIn:" + email);
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()) {
                            Log.d(TAG, "signInWithEmail:success");
                            // TODO: 30/10/2018  
                            //listener.onSuccess();
                        }else {
                            Log.w(TAG, "signInWithEmail:failure");
                            // TODO: 30/10/2018  
                            //listener.onError();
                        }
                    }
                });
    }




}

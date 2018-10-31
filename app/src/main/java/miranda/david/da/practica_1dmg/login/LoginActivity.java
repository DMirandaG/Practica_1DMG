package miranda.david.da.practica_1dmg.login;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import miranda.david.da.practica_1dmg.R;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter loginPresenter;

    private Button botonLogin;
    private EditText entradaEmail;
    private EditText entradaPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        loginPresenter = new LoginPresenterImpl(this);
    }

    private void initView(){
        botonLogin = (Button) findViewById(R.id.botonLogin);
        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(entradaEmail.getText().toString(), entradaPassword.getText().toString());
            }
        });
        entradaEmail = (EditText) findViewById(R.id.entradaEmail);
        entradaPassword = (EditText) findViewById(R.id.entradaPassword);

    }

    @Override
    public void login(String email, String password) {
        loginPresenter.login(email, password);
    }
}


package miranda.david.da.practica_1dmg.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import miranda.david.da.practica_1dmg.R;
import miranda.david.da.practica_1dmg.crear.cuenta.CrearCuentaActivity;
import miranda.david.da.practica_1dmg.main.MainActivity;

public class LoginActivity extends AppCompatActivity implements LoginView {

    private LoginPresenter loginPresenter;

    private Button botonLogin;
    private EditText entradaEmail;
    private EditText entradaPassword;
    private ProgressBar pbCargando;
    private TextView linkRegistrarse;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        loginPresenter = new LoginPresenterImpl(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        loginPresenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        loginPresenter.onStop();
    }

    // Inicialización de los elementos de la pantalla
    private void initView(){
        botonLogin = (Button) findViewById(R.id.botonLogin);
        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarCargando();
                login(entradaEmail.getText().toString(), entradaPassword.getText().toString());
                //ocultarCargando();
            }
        });
        entradaEmail = (EditText) findViewById(R.id.entradaEmail);
        entradaPassword = (EditText) findViewById(R.id.entradaPassword);
        pbCargando = (ProgressBar) findViewById(R.id.pbCargando);
        linkRegistrarse = (TextView) findViewById(R.id.linkRegistrarse);
        linkRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irACrearCuenta();
            }
        });


    }

    //Obtención de los datos necesarios para iniciar sesion
    @Override
    public void login(String email, String password) {
        loginPresenter.login(email, password);
    }

    //Cambiar a pantalla Main
    @Override
    public void irAMain() {
        Toast.makeText(getApplicationContext(), getString(R.string.mensaje_satisfactorio_activity_login), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("EXTRA_EMAIL", entradaEmail.getText().toString());
        startActivity(intent);
        finish();
    }

    //Cambiar a pantalla de creacion de cuenta
    @Override
    public void irACrearCuenta() {
        Intent intent = new Intent(getApplicationContext(), CrearCuentaActivity.class);
        startActivity(intent);
        finish();
    }

    //Habilitar los elementos cuando sea oportuno
    @Override
    public void mostrarCargando() {
        pbCargando.setVisibility(View.VISIBLE);
        botonLogin.setClickable(false);
        botonLogin.setEnabled(false);
        entradaEmail.setClickable(false);
        entradaEmail.setEnabled(false);
        entradaPassword.setClickable(false);
        entradaPassword.setEnabled(false);
        linkRegistrarse.setClickable(false);
        linkRegistrarse.setEnabled(false);

    }

    //Deshabilitar los elementos cuando sea oportuno
    @Override
    public void ocultarCargando() {
        pbCargando.setVisibility(View.GONE);
        botonLogin.setClickable(true);
        botonLogin.setEnabled(true);
        entradaEmail.setClickable(true);
        entradaEmail.setEnabled(true);
        entradaPassword.setClickable(true);
        entradaPassword.setEnabled(true);
        linkRegistrarse.setClickable(true);
        linkRegistrarse.setEnabled(true);

    }

    //Muestra de error si el login no es satisfactorio
    @Override
    public void mostrarErrorLogin() {
        Toast.makeText(getApplicationContext(), getString(R.string.mensaje_error_activity_login), Toast.LENGTH_SHORT).show();
    }

    //Limpiar entrada de datos
    @Override
    public void limpiarFormulario() {
        entradaPassword.setText("");
        entradaEmail.setText("");
    }
}


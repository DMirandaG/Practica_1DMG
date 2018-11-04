package miranda.david.da.practica_1dmg.crear.cuenta;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.regex.Pattern;

import miranda.david.da.practica_1dmg.R;
import miranda.david.da.practica_1dmg.login.LoginActivity;
import miranda.david.da.practica_1dmg.main.MainActivity;

public class CrearCuentaActivity extends AppCompatActivity implements CrearCuentaView {

    private CrearCuentaPresenter crearCuentaPresenter;

    private EditText emailUsuarioNuevo;
    private EditText passwordUsuarioNueva;
    private EditText passwordUsuarioNuevaRepetida;
    private Button botonCrearCuenta;
    private Button botonAtras;
    private ProgressBar pbCargando;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_cuenta);
        initView();
        crearCuentaPresenter = new CrearCuentaPresenterImpl(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        crearCuentaPresenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        crearCuentaPresenter.onStop();
    }

    private void initView(){

        emailUsuarioNuevo = (EditText) findViewById(R.id.emailUsuarioNuevo);
        passwordUsuarioNueva = (EditText) findViewById(R.id.passwordUsuarioNueva);
        passwordUsuarioNuevaRepetida = (EditText) findViewById(R.id.passwordUsuarioNuevaRepetida);
        botonCrearCuenta = (Button) findViewById(R.id.botonCrearCuenta);
        botonCrearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailUsuarioNuevo.getText().toString();
                String password = passwordUsuarioNueva.getText().toString();
                String password2 = passwordUsuarioNuevaRepetida.getText().toString();

                if (!EMAIL_ADDRESS_PATTERN.matcher(email).matches()) {
                    Toast.makeText(getApplicationContext(), getString(R.string.mensaje_error_introduzca_correo), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password) || password.length() < 6) {
                    Toast.makeText(getApplicationContext(), getString(R.string.mensaje_error_introduzca_password), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(password2) || password2.length() < 6) {
                    Toast.makeText(getApplicationContext(), getString(R.string.mensaje_error_introduzca_password), Toast.LENGTH_SHORT).show();
                    return;
                }

                if (!password.equals(password2)) {
                    Toast.makeText(getApplicationContext(), getString(R.string.mensaje_error_introduzca_password_diferente), Toast.LENGTH_SHORT).show();
                    return;
                }
                mostrarCargando();
                crearCuenta(emailUsuarioNuevo.getText().toString(), passwordUsuarioNueva.getText().toString());
            }
        });
        botonAtras = (Button) findViewById(R.id.botonAtras);
        botonAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                irALogin();
            }
        });
        pbCargando = (ProgressBar) findViewById(R.id.pbCargando);

    }

    @Override
    public void irALogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void crearCuenta(String email, String password) {
        crearCuentaPresenter.crearCuenta(email, password);
    }

    @Override
    public void mostrarCargando() {
        pbCargando.setVisibility(View.VISIBLE);
        emailUsuarioNuevo.setClickable(false);
        emailUsuarioNuevo.setEnabled(false);
        passwordUsuarioNueva.setClickable(false);
        passwordUsuarioNueva.setEnabled(false);
        passwordUsuarioNuevaRepetida.setClickable(false);
        passwordUsuarioNuevaRepetida.setEnabled(false);
        botonCrearCuenta.setClickable(false);
        botonCrearCuenta.setEnabled(false);
        botonAtras.setClickable(false);
        botonAtras.setEnabled(false);

    }

    @Override
    public void ocultarCargando() {
        pbCargando.setVisibility(View.GONE);
        emailUsuarioNuevo.setClickable(true);
        emailUsuarioNuevo.setEnabled(true);
        passwordUsuarioNueva.setClickable(true);
        passwordUsuarioNueva.setEnabled(true);
        botonCrearCuenta.setClickable(true);
        botonCrearCuenta.setEnabled(true);
        passwordUsuarioNuevaRepetida.setClickable(true);
        passwordUsuarioNuevaRepetida.setEnabled(true);
        botonAtras.setClickable(true);
        botonAtras.setEnabled(true);

    }

    @Override
    public void irAMain() {
        Toast.makeText(getApplicationContext(), getString(R.string.mensaje_satisfactorio_crear_cuenta), Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.putExtra("EXTRA_EMAIL", emailUsuarioNuevo.getText().toString());
        startActivity(intent);
        finish();
    }

    @Override
    public void mostrarErrorCrearCuenta() {
        Toast.makeText(getApplicationContext(), getString(R.string.mensaje_error_crear_cuenta), Toast.LENGTH_SHORT).show();

    }

    @Override
    public void limpiarFormulario() {
        emailUsuarioNuevo.setText("");
        passwordUsuarioNueva.setText("");
        passwordUsuarioNuevaRepetida.setText("");
    }

    public final Pattern EMAIL_ADDRESS_PATTERN = Pattern.compile(
            "[a-zA-Z0-9+._%-+]{1,256}" +
                    "@" +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,64}" +
                    "(" +
                    "." +
                    "[a-zA-Z0-9][a-zA-Z0-9-]{0,25}" +
                    ")+"
    );

}

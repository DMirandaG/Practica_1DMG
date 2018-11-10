package miranda.david.da.practica_1dmg.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import miranda.david.da.practica_1dmg.R;
import miranda.david.da.practica_1dmg.login.LoginActivity;
import miranda.david.da.practica_1dmg.usuario.Usuario;

public class MainActivity extends AppCompatActivity implements MainView {

    private MainPresenter mainPresenter;

    private Button botonGuardar;
    private Button botonLogOut;
    private Button botonEliminar;
    private EditText emailUsuario;
    private EditText nombreUsuario;
    private TextView IDUsuario;
    private ProgressBar pbCargando;

    private static final String TAG = "IDUsuario";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainPresenter = new MainPresenterImpl(this);
        initView();
        mostrarEmail();
        obtenerUsuario(emailUsuario.getText().toString());
    }

    @Override
    public void onStart() {
        super.onStart();
        mainPresenter.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mainPresenter.onStop();
    }

    private void initView(){

        botonLogOut = (Button) findViewById(R.id.botonLogOut);
        botonLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarCargando();
                cerrarSesion();
            }
        });
        botonEliminar = (Button) findViewById(R.id.botonEliminar);
        botonEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        botonGuardar = (Button) findViewById(R.id.botonGuardar);
        botonGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        emailUsuario = (EditText) findViewById(R.id.emailUsuario);
        nombreUsuario = (EditText) findViewById(R.id.nombreUsuario);
        IDUsuario = (TextView) findViewById(R.id.IDUsuario);
        pbCargando = (ProgressBar) findViewById(R.id.pbCargando);

    }


    @Override
    public void cerrarSesion(){
        mainPresenter.cerrarSesion();
    }

    @Override
    public void irALogin() {
        Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void mostrarEmail() {
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String j =(String) extras.get("EXTRA_EMAIL");
        emailUsuario.setText(j);
    }

    @Override
    public void mostrarCargando() {
        pbCargando.setVisibility(View.VISIBLE);
        botonGuardar.setClickable(false);
        botonGuardar.setEnabled(false);
        botonLogOut.setClickable(false);
        botonLogOut.setEnabled(false);
        botonEliminar.setClickable(false);
        botonEliminar.setEnabled(false);
        emailUsuario.setClickable(false);
        emailUsuario.setEnabled(false);
        nombreUsuario.setClickable(false);
        nombreUsuario.setEnabled(false);
        IDUsuario.setClickable(false);
        IDUsuario.setEnabled(false);

    }

    @Override
    public void ocultarCargando() {
        pbCargando.setVisibility(View.GONE);
        botonGuardar.setClickable(true);
        botonGuardar.setEnabled(true);
        botonLogOut.setClickable(true);
        botonLogOut.setEnabled(true);
        botonEliminar.setClickable(true);
        botonEliminar.setEnabled(true);
        emailUsuario.setClickable(true);
        emailUsuario.setEnabled(true);
        nombreUsuario.setClickable(true);
        nombreUsuario.setEnabled(true);
        IDUsuario.setClickable(true);
        IDUsuario.setEnabled(true);

    }

    @Override
    public void obtenerUsuario(String email){
        mainPresenter.obtenerUsuario(email);
    }

    @Override
    public void pintarUsuario(Usuario usuario){
        emailUsuario.setText(usuario.getEmail());
        IDUsuario.setText(usuario.getId());
        nombreUsuario.setText(usuario.getUsername());
    }

    @Override
    public void errorObtenerDatos(){
        Toast.makeText(getApplicationContext(), getString(R.string.mensaje_error_obtener_datos_usuario), Toast.LENGTH_SHORT).show();


    }




}

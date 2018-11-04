package miranda.david.da.practica_1dmg.crear.cuenta;

import miranda.david.da.practica_1dmg.login.events.LoginEvent;

interface CrearCuentaPresenter {

    void crearCuenta (String email, String password);
    void onStart();
    void onStop();
    void onEventLoginThread(LoginEvent event);

}

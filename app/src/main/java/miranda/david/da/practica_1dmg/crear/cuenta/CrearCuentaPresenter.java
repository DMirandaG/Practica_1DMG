package miranda.david.da.practica_1dmg.crear.cuenta;

import miranda.david.da.practica_1dmg.crear.cuenta.events.CrearCuentaEvent;

interface CrearCuentaPresenter {

    void crearCuenta (String email, String password);
    void onStart();
    void onStop();
    void onEventLoginThread(CrearCuentaEvent event);

}

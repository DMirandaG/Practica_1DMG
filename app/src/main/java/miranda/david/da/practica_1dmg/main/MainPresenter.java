package miranda.david.da.practica_1dmg.main;

import miranda.david.da.practica_1dmg.login.events.LoginEvent;

interface MainPresenter {

    void cerrarSesion ();
    void onStart();
    void onStop();
    void onEventLoginThread(LoginEvent event);
}

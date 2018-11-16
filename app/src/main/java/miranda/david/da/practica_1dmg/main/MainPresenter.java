package miranda.david.da.practica_1dmg.main;


import miranda.david.da.practica_1dmg.main.events.MainEvent;

interface MainPresenter {

    void cerrarSesion ();
    void onStart();
    void onStop();
    void onEventLoginThread(MainEvent event);
    void obtenerUsuario(String email);
    void eliminarUsuario(String id);
}

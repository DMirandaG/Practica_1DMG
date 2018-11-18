package miranda.david.da.practica_1dmg.main;

interface MainInteractor {

    void cerrarSesion();
    void obtenerUsuario(String email);
    void eliminarUsuario(String id);
    void actualizarDatos(String id, String email, String username);
}

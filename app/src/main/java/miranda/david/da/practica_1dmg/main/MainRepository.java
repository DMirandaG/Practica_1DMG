package miranda.david.da.practica_1dmg.main;

interface MainRepository {

    void cerrarSesion();
    void obtenerUsuario(final String email);
    void eliminarUsuario(String id);
}

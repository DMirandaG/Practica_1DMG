package miranda.david.da.practica_1dmg.main;

import miranda.david.da.practica_1dmg.usuario.Usuario;

interface MainView {

    void irALogin();
    void cerrarSesion();
    void mostrarEmail();
    void mostrarCargando();
    void ocultarCargando();
    void obtenerUsuario(String email);
    void pintarUsuario(Usuario usuario);
    void errorObtenerDatos();
}

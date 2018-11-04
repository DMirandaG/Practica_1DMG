package miranda.david.da.practica_1dmg.login;

interface LoginView {

    void login (String email, String password);
    void irAMain();
    void irACrearCuenta();
    void mostrarCargando();
    void ocultarCargando();
    void mostrarErrorLogin();
    void limpiarFormulario();
}

package miranda.david.da.practica_1dmg.crear.cuenta;

interface CrearCuentaView {

    void irALogin();
    void crearCuenta(String email, String password);
    void mostrarCargando();
    void ocultarCargando();
    void irAMain();
    void mostrarErrorCrearCuenta();
    void limpiarFormulario();
}

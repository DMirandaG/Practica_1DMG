package miranda.david.da.practica_1dmg.crear.cuenta;

public class CrearCuentaInteractorImpl implements CrearCuentaInteractor {

    private CrearCuentaRepository crearCuentaRepository;

    public CrearCuentaInteractorImpl(){
        crearCuentaRepository = new CrearCuentaRepositoryImpl();
    }


    // Envío de los datos para crear la cuenta
    @Override
    public void crearCuenta(String email, String password){
        crearCuentaRepository.crearCuenta(email, password);
    }

}

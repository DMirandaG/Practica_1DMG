package miranda.david.da.practica_1dmg.main;

public class MainInteractorImpl implements MainInteractor {

    private MainRepository mainRepository;

    public MainInteractorImpl(){
        mainRepository =  new MainRepositoryImpl();
    }


    //Método que cierra la sesion actual
    @Override
    public void cerrarSesion() {
        mainRepository.cerrarSesion();
    }

    //Método que obtiene el email del usuario para pedir los datos del mismo a la BD
    @Override
    public void obtenerUsuario(String email){
        mainRepository.obtenerUsuario(email);
    }

    //Método que elimina al usuario de la BD
    @Override
    public void eliminarUsuario(String id){
        mainRepository.eliminarUsuario(id);
    }


    //Método para actualizar los datos del usuario en la BD
    @Override
    public void actualizarDatos(String id, String email, String username){
        mainRepository.actualizarDatos(id, email, username);
    }
}

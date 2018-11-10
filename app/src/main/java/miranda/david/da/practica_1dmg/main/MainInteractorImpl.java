package miranda.david.da.practica_1dmg.main;

public class MainInteractorImpl implements MainInteractor {

    private MainRepository mainRepository;

    public MainInteractorImpl(){
        mainRepository =  new MainRepositoryImpl();
    }


    @Override
    public void cerrarSesion() {
        mainRepository.cerrarSesion();
    }

    @Override
    public void obtenerUsuario(String email){
        mainRepository.obtenerUsuario(email);
    }
}

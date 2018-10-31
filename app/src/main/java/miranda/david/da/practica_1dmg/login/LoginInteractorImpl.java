package miranda.david.da.practica_1dmg.login;

public class LoginInteractorImpl implements LoginInteractor {

    private LoginRepository loginRepository;

    public LoginInteractorImpl(){
        loginRepository =  new LoginRepositoryImpl();
    }

    @Override
    public void login(String email, String password) {
        loginRepository.login(email, password);
    }
}

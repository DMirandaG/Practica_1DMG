package miranda.david.da.practica_1dmg.login;

public class LoginPresenterImpl implements LoginPresenter {

    private LoginInteractor loginInteractor;
    private LoginView loginView;

    public LoginPresenterImpl(LoginView loginView) {
        loginInteractor = new LoginInteractorImpl();
    }

    @Override
    public void login(String email, String password) {
        loginInteractor.login(email, password);
    }
}

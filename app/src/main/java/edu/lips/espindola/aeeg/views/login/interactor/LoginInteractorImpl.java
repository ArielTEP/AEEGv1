package edu.lips.espindola.aeeg.views.login.interactor;

import android.util.Log;

import edu.lips.espindola.aeeg.domain.ConnectionTask;
import edu.lips.espindola.aeeg.views.login.repository.LoginRepository;
import edu.lips.espindola.aeeg.views.login.repository.LoginRepositoryImpl;

/**
 * Created by Ariel on 9/20/16.
 */
public class LoginInteractorImpl implements LoginInteractor{

    LoginRepository repository;

    public LoginInteractorImpl(){
        this.repository = new LoginRepositoryImpl();
    }

    @Override
    public void checkSession() {
        repository.checkSession();
    }

    @Override
    public void doSignUp(String name, String lastName, String email, String password, String userType, int college) {
        repository.signUp(name,lastName,email,password,userType,college);
    }

    @Override
    public void doSignIn(final String email, final String password) {
        repository.signIn(email, password);
    }
}

package edu.lips.espindola.aeeg.views.login.interactor;

/**
 * Created by Ariel on 9/20/16.
 */
public interface LoginInteractor {
    void checkSession(); // si existe la sesion
    void doSignUp(String name,String lastName,
                  String email, String password,
                  String userType, int college);
    void doSignIn(String email, String password);
}

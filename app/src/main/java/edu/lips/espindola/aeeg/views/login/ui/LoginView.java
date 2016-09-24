package edu.lips.espindola.aeeg.views.login.ui;

/**
 * Created by Ariel on 9/20/16.
 */
public interface LoginView {
    void enableInputs();
    void disableInputs();
    void showProgressBar();
    void hideProgressBar();

    void handleSignIn();
    void handleSignUp();

    void navigateToMainScreen();
    void navigateToSignupScreen();
    void loginError(String error);

    void newUserSuccess();
    void newUserError(String error);

    void showSnackBarMessage(String response);
}

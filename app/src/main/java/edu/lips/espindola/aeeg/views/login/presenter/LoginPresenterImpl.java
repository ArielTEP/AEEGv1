package edu.lips.espindola.aeeg.views.login.presenter;

import android.util.Log;

import org.greenrobot.eventbus.Subscribe;

import edu.lips.espindola.aeeg.addonlibs.EventBus;
import edu.lips.espindola.aeeg.addonlibs.GreenRobotEventBus;
import edu.lips.espindola.aeeg.views.login.events.LoginEvent;
import edu.lips.espindola.aeeg.views.login.interactor.LoginInteractor;
import edu.lips.espindola.aeeg.views.login.interactor.LoginInteractorImpl;
import edu.lips.espindola.aeeg.views.login.ui.LoginView;

/**
 * Created by Ariel on 9/20/16.
 */
public class LoginPresenterImpl implements LoginPresenter{
    private EventBus eventBus;
    private LoginView loginView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(LoginView loginView) {
        this.loginView = loginView;
        this.loginInteractor = new LoginInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }


    private void onFailedToRecoverSession() {
        if (loginView != null){
            loginView.hideProgressBar();
            loginView.enableInputs();
        }
        Log.e("LoginPresenterImpl","onFailedToRecoverSession");
    }

    public void onSignInSuccess(){
        if (loginView!=null){
            loginView.navigateToMainScreen();
        }

    }

    public void onSignUpSuccess(){
        if (loginView != null){
            loginView.newUserSuccess();
        }
    }

    public void onSignInError(String error){
        Log.e("LoginPresenterImp",error);
        if (loginView != null){
            loginView.hideProgressBar();
            loginView.enableInputs();
            loginView.loginError(error);
        }
    }

    public void onSignUpError(String error){
        if (loginView != null){
            loginView.hideProgressBar();
            loginView.enableInputs();
            loginView.newUserError(error);
        }
    }

    public void onTestingResponse(String response){
        if (loginView != null){
            loginView.showSnackBarMessage(response);
        }
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        loginView = null;   // se destruye objeto, aliminando memory leak
        eventBus.unregister(this);
    }

    @Override
    public void checkForAuthenticatedUser() {
        // usuario autenticado
        //verificar primero que exista la vista con null
        if (loginView != null){
            //loginView.disableInputs();
            //loginView.showProgressBar();
        }
        loginInteractor.checkSession(); // revisar sesión de usuario
    }

    @Override
    public void validateLogin(String email, String password) {
        if (loginView != null){
            loginView.disableInputs();
            loginView.showProgressBar();
        }
        loginInteractor.doSignIn(email,password);
    }

    @Override
    public void registerNewUser(String name, String lastName, String email, String password, String userType, int college) {
        if (loginView != null){
            loginView.disableInputs();
            loginView.showProgressBar();
        }
        loginInteractor.doSignUp(name,lastName,email,password,userType,college);
    }

    @Override
    @Subscribe
    public void onEventMainThread(LoginEvent event) { //metodo que esta a la escucha
        Log.e("LoginPresenter","onEventMainThread");
        switch (event.getEventType()){
            case LoginEvent.onSignInSuccess:
                onSignInSuccess();
                break;
            case LoginEvent.onSignUpSuccess:
                onSignUpSuccess();
                break;
            case LoginEvent.onSignInError:
                onSignInError(event.getMsgError());
                break;
            case LoginEvent.onSignUpError:
                onSignUpError(event.getMsgError());
                break;
            case LoginEvent.onFailedToRecoverSession:
                onFailedToRecoverSession();
                break;
            case 17:
                onTestingResponse(event.getResponse());
                break;
        }
    }
}

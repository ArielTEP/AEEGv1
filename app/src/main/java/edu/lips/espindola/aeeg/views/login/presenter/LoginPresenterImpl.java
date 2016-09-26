package edu.lips.espindola.aeeg.views.login.presenter;

import android.util.Log;

import org.greenrobot.eventbus.Subscribe;

import edu.lips.espindola.aeeg.addonlibs.EventBus;
import edu.lips.espindola.aeeg.addonlibs.GreenRobotEventBus;
import edu.lips.espindola.aeeg.views.login.events.LoginEvent;
import edu.lips.espindola.aeeg.views.login.interactor.LoginInteractor;
import edu.lips.espindola.aeeg.views.login.interactor.LoginInteractorImpl;
import edu.lips.espindola.aeeg.views.login.ui.SignInView;

/**
 * Created by Ariel on 9/20/16.
 */
public class LoginPresenterImpl implements LoginPresenter{
    private EventBus eventBus;
    private SignInView signInView;
    private LoginInteractor loginInteractor;

    public LoginPresenterImpl(SignInView signInView) {
        this.signInView = signInView;
        this.loginInteractor = new LoginInteractorImpl();
        this.eventBus = GreenRobotEventBus.getInstance();
    }


    private void onFailedToRecoverSession() {
        if (signInView != null){
            signInView.hideProgressBar();
            signInView.enableInputs();
        }
        Log.e("LoginPresenterImpl","onFailedToRecoverSession");
    }

    public void onSignInSuccess(){
        if (signInView !=null){
            signInView.showSnackBarMessage("¡Inicio de sesión exitoso!");
            signInView.navigateToMainScreen();
        }

    }

    public void onSignUpSuccess(){
        if (signInView != null){
            signInView.newUserSuccess();
        }
    }

    public void onSignInError(String error){
        Log.e("LoginPresenterImp",error);
        if (signInView != null){
            signInView.hideProgressBar();
            signInView.enableInputs();
            signInView.loginError(error);
        }
    }

    public void onSignUpError(String error){
        if (signInView != null){
            signInView.hideProgressBar();
            signInView.enableInputs();
            signInView.newUserError(error);
        }
    }

    public void onTestingResponse(String response){
        if (signInView != null){
            signInView.showSnackBarMessage(response);
        }
    }

    @Override
    public void onCreate() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        signInView = null;   // se destruye objeto, aliminando memory leak
        eventBus.unregister(this);
    }

    @Override
    public void checkForAuthenticatedUser() {
        // usuario autenticado
        //verificar primero que exista la vista con null
        if (signInView != null){
            //signInView.disableInputs();
            //signInView.showProgressBar();
        }
        loginInteractor.checkSession(); // revisar sesión de usuario
    }

    @Override
    public void validateLogin(String email, String password) {
        if (signInView != null){
            signInView.disableInputs();
            signInView.showProgressBar();
        }
        loginInteractor.doSignIn(email,password);
    }

    @Override
    public void registerNewUser(String name, String lastName, String email, String password, String userType, int college) {
        if (signInView != null){
            signInView.disableInputs();
            signInView.showProgressBar();
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

package edu.lips.espindola.aeeg.views.login.presenter;

import edu.lips.espindola.aeeg.views.login.events.LoginEvent;

/**
 * Created by Ariel on 9/20/16.
 */
public interface LoginPresenter {
    void onCreate();
    void onDestroy(); //Borrar memoria
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void registerNewUser(String name,String lastName,
                         String email, String password,
                        String userType, int college );
    void onEventMainThread(LoginEvent event); //metodo @Subscriber
}

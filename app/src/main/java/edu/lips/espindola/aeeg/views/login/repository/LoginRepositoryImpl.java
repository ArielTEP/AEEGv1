package edu.lips.espindola.aeeg.views.login.repository;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import edu.lips.espindola.aeeg.addonlibs.EventBus;
import edu.lips.espindola.aeeg.addonlibs.GreenRobotEventBus;
import edu.lips.espindola.aeeg.domain.ConnectionHelper;
import edu.lips.espindola.aeeg.domain.ConnectionTask;
import edu.lips.espindola.aeeg.entities.SoapMessage;
import edu.lips.espindola.aeeg.views.login.events.LoginEvent;

/**
 * Created by Ariel on 9/20/16.
 */
public class LoginRepositoryImpl implements LoginRepository {

    ConnectionTask webService;

    @Override
    public void signUp(String name, String lastName, String email, String password,
                       String userType, int college) {

    }

    @Override
    public void signIn(String email, String password, ConnectionTask.AsyncResponse asyncResponse) {
        Log.e("LoginRepositoryImpl","signin");
        if (email!= null)
            Log.e("LoginRepositoryImpl",email);
        if(password!=null)
            Log.e("LoginRepositoryImpl",password);

        List<String> params = new ArrayList<>();
        List<String> values = new ArrayList<>();

        params.add("correo");
        params.add("pass");

        values.add(email.trim());
        values.add(password.trim());

        SoapMessage message = new SoapMessage();
        message.setSoapAction("http://WS.appEEG.com/WSappEEG/validaUsuario");
        message.setMethodName("validaUsuario");
        message.setParams(params);
        message.setValues(values);

        webService = new ConnectionTask(asyncResponse);
        webService.execute(message);

    }

    @Override
    public void signIn(String email, String password) {
        Log.e("LoginRepositoryImpl","signin");
        if (email!= null)
            Log.e("LoginRepositoryImpl",email);
        if(password!=null)
            Log.e("LoginRepositoryImpl",password);

        List<String> params = new ArrayList<>();
        List<String> values = new ArrayList<>();

        params.add("correo");
        params.add("pass");

        values.add(email.trim());
        values.add(password.trim());

        SoapMessage message = new SoapMessage();
        message.setSoapAction("http://WS.appEEG.com/WSappEEG/validaUsuario");
        message.setMethodName("validaUsuario");
        message.setParams(params);
        message.setValues(values);

        webService = new ConnectionTask(new ConnectionTask.AsyncResponse() {
            @Override
            public void processFinish(String output) {
                postEvent(17,output);
            }
        });
        webService.execute(message);
    }

    @Override
    public void checkSession() {
        Log.e("LoginRepositoryImpl","checkSession");
    }

    private void postEvent(int type, String response){
        Log.e("LoginRepository","postEvent()");
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if (response!=null){
            loginEvent.setResponse(response);
        }

        EventBus event = GreenRobotEventBus.getInstance();
        event.post(loginEvent);
    }
}

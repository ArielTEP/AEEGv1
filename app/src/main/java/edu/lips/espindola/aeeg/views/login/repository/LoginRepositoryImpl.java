package edu.lips.espindola.aeeg.views.login.repository;

import android.util.Log;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import edu.lips.espindola.aeeg.addonlibs.EventBus;
import edu.lips.espindola.aeeg.addonlibs.GreenRobotEventBus;
import edu.lips.espindola.aeeg.domain.ConnectionTask;
import edu.lips.espindola.aeeg.entities.SoapMessage;
import edu.lips.espindola.aeeg.entities.User;
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
                Log.e("webService","signIn-Response");
                User user = null;

                // aqui va toda la lógico de conversión de objetos json-java
                // validación exitósa o fallida del inicio de sesión
                // si es exitosa, se llama el evento de éxito
                // si es fallida, se llama el evento de fallo, con el respectivo error

                try{
                    user = getUserFromJSON(output);
                    postEvent(LoginEvent.onSignInSuccess);
                    // enviar objeto del usuario a la clase Home
                }catch (Exception ex){
                    postEvent(LoginEvent.onSignInError,ex.getMessage());
                }

            }
        });
        webService.execute(message);
    }

    private User getUserFromJSON(String jsonSequence) throws Exception{
        User user = new User();
        //BeanGroup group = new BeanGroup();

        JSONObject root = new JSONObject(jsonSequence);
        int id = root.getInt("id");
        int idCollege = root.getInt("idescuela");

        String name = root.getString("nombre");
        String lastname = root.getString("apellidos");
        String email = root.getString("correo");
        String password = root.getString("pass");
        String userType = root.getString("tipo");
        String collegeName = root.getString("escuela");

        JSONObject groupRef = root.getJSONObject("grupo");

        int idGroup = groupRef.getInt("id");
        String groupName = groupRef.getString("nombre");
        String lecturerName = groupRef.getString("nombreProfe");
        String startDate = groupRef.getString("fechaCreacion");
        boolean status = groupRef.getBoolean("status");

        //user.setAllData();
        //user.setBeanGroup(group);
        Log.e("getUserFromJSON",name + "-" +groupName);
        return user;
    }

    @Override
    public void checkSession() {
        Log.e("LoginRepositoryImpl","checkSession");
    }

    private void postEvent(int type, String errorMessage){
        Log.e("LoginRepository","postEvent()");
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if (errorMessage!=null){
            loginEvent.setMsgError(errorMessage);
        }
        EventBus event = GreenRobotEventBus.getInstance();
        event.post(loginEvent);

    }

    private void postEvent(int type){
        postEvent(type,null);
    }


}

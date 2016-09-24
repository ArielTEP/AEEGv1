package edu.lips.espindola.aeeg.views.login.events;

/**
 * Created by Ariel on 9/20/16.
 */
public class LoginEvent {
    public final static int onSignInError = 0;
    public final static int onSignInSuccess = 1;
    public final static int onSignUpError = 2;
    public final static int onSignUpSuccess = 3;
    public final static int onFailedToRecoverSession = 4;

    private int eventType;
    private String msgError;
    private String response;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getMsgError() {
        return msgError;
    }

    public void setMsgError(String msgError) {
        this.msgError = msgError;
    }
}

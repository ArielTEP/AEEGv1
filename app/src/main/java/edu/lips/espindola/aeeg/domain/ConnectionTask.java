package edu.lips.espindola.aeeg.domain;

import android.os.AsyncTask;
import android.util.Log;

import edu.lips.espindola.aeeg.addonlibs.EventBus;
import edu.lips.espindola.aeeg.addonlibs.GreenRobotEventBus;
import edu.lips.espindola.aeeg.entities.SoapMessage;
import edu.lips.espindola.aeeg.views.login.events.LoginEvent;

/**
 * Created by Ariel on 9/20/16.
 */
public class ConnectionTask extends AsyncTask<SoapMessage,Void,String> {

    ConnectionHelper helper;

    public interface AsyncResponse {
        void processFinish(String output);
    }

    public ConnectionTask(AsyncResponse asyncResponse) {
        this.helper = ConnectionHelperImpl.getWebServiceReference();
        this.asyncResponse = asyncResponse;
    }

    public AsyncResponse asyncResponse;

    @Override
    protected String doInBackground(SoapMessage... soapMessages) {
        SoapMessage soapMessage = soapMessages[0];
        String response;
        try {
            response = helper.send(soapMessage);
        } catch (Exception ex) {
            response = ex.getMessage();
        }
        return response;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String response) {
        super.onPostExecute(response);
        asyncResponse.processFinish(response);
        Log.e("Fin de processFinish","Ok");
        Log.e("onPostExecute",response);
        //postEvent(17,response);
        // esto se va al hilo principal
        // aqui actuaría EventBus
    }
}

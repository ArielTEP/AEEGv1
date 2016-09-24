package edu.lips.espindola.aeeg.domain;


import edu.lips.espindola.aeeg.entities.SoapMessage;

/**
 * Created by Ariel on 9/19/16.
 */
public class ConnectionHelperImpl extends WebService implements ConnectionHelper{

    public static class WebServiceReference{
        public static final ConnectionHelperImpl INSTANCE = new ConnectionHelperImpl();
    }

    public static ConnectionHelperImpl getWebServiceReference(){
        return WebServiceReference.INSTANCE;
    }

    @Override
    public String send(SoapMessage soapMessage) throws Exception {
        super.setupDefaultWebService(); // se configura la url que pretende utilizarse
        return makeSoapCall(soapMessage); // hace la llamada al web service y recibe respuesta
    }
}

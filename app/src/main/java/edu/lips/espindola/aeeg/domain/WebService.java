package edu.lips.espindola.aeeg.domain;

import android.util.Log;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.List;

import edu.lips.espindola.aeeg.entities.SoapMessage;
import edu.lips.espindola.aeeg.entities.WebServiceDispatcher;

/**
 * Created by Ariel on 9/19/16.
 */
public class WebService {
    protected String namespace;
    protected String URL;


    private void setService(String socketAddress, String webProject,
                        String serviceName, String namespace){
        this.namespace = namespace;
        this.URL = "http://"+ socketAddress + "/" + webProject + "/" + serviceName;
    }

    private void setDefaultDefaultService(){
        this.namespace = "http://WS.appEEG.com/";
        this.URL = "http://192.168.66.7:8080/WSAprendiendoEEG/WSappEEG";
    }

    private void setupWebService(WebServiceDispatcher dispatcher, boolean _default){
        if (_default)
            setDefaultDefaultService();
        else{
            setService( dispatcher.getSocketAddress(),
                        dispatcher.getWebProject(),
                        dispatcher.getServiceName(),
                        dispatcher.getNamespace());
        }
    }

    public void setupDefaultWebService(){
        setupWebService(null,true);
    }

    public void setupSpecificWebService(WebServiceDispatcher dispatcher){
        setupWebService(dispatcher,false);
    }


    private SoapObject setupRequestMessage(SoapMessage msg) throws Exception {
        SoapObject requestMsg = new SoapObject(namespace,msg.getMethodName());
        List<String> params = msg.getParams();
        List<String> values = msg.getValues();
        Log.e("WebService", "setupRequestMessage, params: " + params.size());
        if (!params.isEmpty()){
            for (int i = 0; i < params.size() ; i++)
                requestMsg.addProperty(params.get(i),values.get(i));
        }
        return requestMsg;
    }

    private SoapSerializationEnvelope setupEnvelope(SoapObject requestMsg){
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        Log.e("WebService", "SoapSerializationEnvelope-Ok");
        // Establecer parametros y colocar en la bandeja de salida el mensaje SOAP
        envelope.dotNet = false;
        envelope.setOutputSoapObject(requestMsg);
        Log.e("WebService", "setOutputSoapObject-Ok");
        return envelope;
    }


    public String makeSoapCall(SoapMessage msg) throws Exception {
        String response;
        //  Preparar mensaje de solicitud SOAP
        SoapObject requestMsg = setupRequestMessage(msg);

        // Configurar paquete o envoltura del mensaje
        SoapSerializationEnvelope envelope = setupEnvelope(requestMsg);

        // Capa de transporte HTTP basada en Java Standard Edition
        HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);
        Log.e("WebService", "androidHttpTransport-Ok");

        //  Se coloca la acción SOAP como cabecera del mensaje y se envía
        androidHttpTransport.call(msg.getSoapAction(), envelope);
        Log.e("WebService", "Call-Ok");

        // Espera la respuesta de la llamada
        response = envelope.getResponse().toString();
        if (response != null)
            Log.e("WebService", "Response: " + response);
        else
            Log.e("WebService", "Response: Falló");

        return response;
    }

}

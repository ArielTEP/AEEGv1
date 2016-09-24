package edu.lips.espindola.aeeg.domain;


import edu.lips.espindola.aeeg.entities.SoapMessage;

/**
 * Created by Ariel on 9/19/16.
 */
public interface ConnectionHelper {
    String send(SoapMessage soapMessage) throws Exception;
}

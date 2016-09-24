package edu.lips.espindola.aeeg.entities;

import java.util.List;

/**
 * Created by Ariel on 9/19/16.
 */
public class SoapMessage {
    private String soapAction;
    private String methodName;
    private List<String> params;
    private List<String> values;

    public String getSoapAction() {
        return soapAction;
    }

    public void setSoapAction(String soapAction) {
        this.soapAction = soapAction;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public List<String> getParams() {
        return params;
    }

    public void setParams(List<String> params) {
        this.params = params;
    }

    public List<String> getValues() {
        return values;
    }

    public void setValues(List<String> values) {
        this.values = values;
    }
}

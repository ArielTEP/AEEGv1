package edu.lips.espindola.aeeg.entities;

/**
 * Created by Ariel on 9/19/16.
 */
public class WebServiceDispatcher {
    private String socketAddress;
    private String webProject;
    private String serviceName;
    private String namespace;

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public String getSocketAddress() {
        return socketAddress;
    }

    public void setSocketAddress(String socketAddress) {
        this.socketAddress = socketAddress;
    }

    public String getWebProject() {
        return webProject;
    }

    public void setWebProject(String webProject) {
        this.webProject = webProject;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}

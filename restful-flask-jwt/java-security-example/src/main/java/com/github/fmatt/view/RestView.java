package com.github.fmatt.view;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.JsonObject;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

@Named
@ViewScoped
public class RestView implements Serializable {

    @Inject
    private SessionDataView sessionDataView;

    @Inject
    private AuthView authView;

    private Logger logger = Logger.getLogger("RestView");

    private String result = "";

    private Response accessApi(Client client) {
        try {
			String url = "http://localhost:5000/restricted";
			return client.target(url)
                .request()
                .header("Authorization", "Bearer " +  sessionDataView.getToken())
                .get();
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
			throw new RuntimeException("An error ocurred while accessing the API.");
        } 
    }

    @PostConstruct
    public void init() {
		try {
            Client client = ClientBuilder.newClient();
            Response response = accessApi(client);
            if (response.getStatus() == 401) {
                // Refresh token
                logger.info("Token expired, refreshing...");
                authView.createToken();
                response = accessApi(client);
            } else if (response.getStatus() != 200) {
                FacesContext.getCurrentInstance().addMessage(null, 
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error accessing API.", null));
                return;
            }

            this.result = response.readEntity(JsonObject.class).getString("message");
            response.close();
            client.close();
		} catch (ProcessingException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
			throw new RuntimeException("API currently unavailable.");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new RuntimeException("An error ocurred while fetching data.");
		} 
	}

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
}

package com.github.fmatt.view;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
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

    private Logger logger = Logger.getLogger("RestView");

    private String result = "";

    @PostConstruct
    public void init() {
		try {
			Client client = ClientBuilder.newClient();
			String url = "http://localhost:5000/restricted";
			Response response = client
                .target(url)
                .request()
                .header("Authorization", "Bearer " +  sessionDataView.getToken())
                .get();

            if (response.getStatus() == 200) {
                this.result = response.readEntity(JsonObject.class).getString("message");
            }

			response.close();
			client.close();
		} catch (ProcessingException e) {
			throw new RuntimeException("API temporariamente indisponível.");
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			throw new RuntimeException("Ocorreu um erro ao buscar os dados.");
		}
	}

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
    
}

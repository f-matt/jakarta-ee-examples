package com.github.fmatt.view;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.json.JsonArray;
import javax.ws.rs.ProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.fmatt.dto.ItemDto;

@Named
@ViewScoped
public class RestView implements Serializable {

    private Logger logger = Logger.getLogger("RestView");

    private String result = "";

    @PostConstruct
    public void init() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();
		try {
            Client client = ClientBuilder.newClient();
            String url = "http://localhost:5000/items";
			Response response = client.target(url)
                .request()
                .get();

            JsonArray jsonArray = response.readEntity(JsonArray.class);
            logger.info(jsonArray.toString());
            // this.result = response.readEntity(JsonObject.class).getString("message");
            ItemDto[] items = objectMapper.readValue(jsonArray.toString(), ItemDto[].class);
            List<ItemDto> itemsList = Arrays.asList(items);
            
            for (ItemDto dto : itemsList) {
                logger.info(String.valueOf(dto.getId()));
            }

            this.result = "ok";
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

package com.github.fmatt.service;

import com.github.fmatt.util.UsernamePasswordDto;

import jakarta.ejb.Stateless;
import jakarta.json.JsonObject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Stateless
public class SupersetService {

    public String getAccessToken() {
		Client client = null;

        UsernamePasswordDto usernamePasswordDto = new UsernamePasswordDto();
        usernamePasswordDto.setUsername("admin");
        usernamePasswordDto.setPassword("admin");

		try {
			client = ClientBuilder.newClient();
			String url = "http://localhost:8088/api/v1/security/login";
			WebTarget webTarget = client.target(url);

			Response response = webTarget.request().post(Entity.entity(usernamePasswordDto, MediaType.APPLICATION_JSON));
            System.out.println(response.getStatus());

			JsonObject jsonObject = response.readEntity(JsonObject.class);

			response.close();

			return jsonObject.getString("access_token", null);
		} catch (Exception e) {
            e.printStackTrace();
		} finally {
			if (client != null)
				client.close();
		}

        return null;
    }
    
}

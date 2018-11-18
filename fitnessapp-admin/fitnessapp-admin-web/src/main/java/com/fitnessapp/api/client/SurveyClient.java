/*
 * Copyright (C) 2018 Naluem
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.fitnessapp.api.client;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
/**
 * Jersey REST client generated for REST resource:SurveyFacadeREST [survey]<br>
 * USAGE:
 * <pre>
 *        SurveyClient client = new SurveyClient();
 *        Object response = client.XXX(...);
 *        // do whatever with response
 *        client.close();
 * </pre>
 *
 * @author Naluem
 */
public class SurveyClient {

	private WebTarget webTarget;
	private Client client;
	private static final String BASE_URI = "http://localhost:8080/fitness-app-api-web/api";

	public SurveyClient() {
		client = javax.ws.rs.client.ClientBuilder.newClient();
		webTarget = client.target(BASE_URI).path("survey");
	}

	public SurveyClient(String username, String password) {
		this();
		setUsernamePassword(username, password);
	}

	public String countREST() throws ClientErrorException {
		WebTarget resource = webTarget;
		resource = resource.path("count");
		return resource.request(javax.ws.rs.core.MediaType.TEXT_PLAIN).get(String.class);
	}

	public void edit(Object requestEntity, String id) throws ClientErrorException {
		webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request(javax.ws.rs.core.MediaType.APPLICATION_JSON).put(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
	}

	public <T> T find(Class<T> responseType, String id) throws ClientErrorException {
		WebTarget resource = webTarget;
		resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
		return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
	}

	public <T> T findRange(Class<T> responseType, String from, String to) throws ClientErrorException {
		WebTarget resource = webTarget;
		resource = resource.path(java.text.MessageFormat.format("{0}/{1}", new Object[]{from, to}));
		return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
	}

	public void create(Object requestEntity) throws ClientErrorException {
		webTarget.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).post(javax.ws.rs.client.Entity.entity(requestEntity, javax.ws.rs.core.MediaType.APPLICATION_JSON));
	}

	public <T> T findAll(Class<T> responseType) throws ClientErrorException {
		WebTarget resource = webTarget;
		return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(responseType);
	}

	public void remove(String id) throws ClientErrorException {
		webTarget.path(java.text.MessageFormat.format("{0}", new Object[]{id})).request().delete();
	}

	public void close() {
		client.close();
	}

	public final void setUsernamePassword(String username, String password) {
		//webTarget.register(new org.glassfish.jersey.client.filter.HttpBasicAuthFilter(username, password));
		client.register(new Authenticator(username, password));

	}

}

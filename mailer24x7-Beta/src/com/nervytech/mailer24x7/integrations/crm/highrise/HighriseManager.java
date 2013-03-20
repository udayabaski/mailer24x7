package com.nervytech.mailer24x7.integrations.crm.highrise;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

/**
 * 
 * @author thiagofa
 *
 */
public abstract class HighriseManager {

	private WebResource webResource;
	private String authorization;
	
	public HighriseManager(WebResource webResource, String authorization) {
		super();
		this.webResource = webResource;
		this.authorization = authorization;
	}
	
        protected <T> T show(Class<T> entity, String path) {
            T result = null;
            ClientResponse response = this.get(path, null);
            
            String s = response.getEntity(String.class);
            
		if (response.getStatus() == ClientResponse.Status.OK.getStatusCode() 
				|| response.getStatus() == ClientResponse.Status.BAD_REQUEST.getStatusCode()) {
			try {
				JAXBContext jc = JAXBContext.newInstance(entity, Empty.class, Errors.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				Object obj = unmarshaller.unmarshal(new StringReader(s));
				if (obj instanceof Errors) {
					Errors errors = (Errors) obj;
					throw new HighriseException(response.getStatus(), errors.getError());
				} else if (!(obj instanceof Empty)) {
					result = (T) obj;
				}
			} catch (JAXBException e) {
				throw new HighriseException("Error unmarshalling Highrise return.", e);
			}
		} else {
			throw new HighriseException(response.getStatus(), "Highrise return not expected (" 
					+ ClientResponse.Status.fromStatusCode(response.getStatus()) + ").");
		}
		
		return result;            
        }
        
	protected <T> void update(T entity, String path) {
		ClientResponse response = this.put(path, entity);
		
		String s = response.getEntity(String.class);
		
		if (response.getStatus() == ClientResponse.Status.BAD_REQUEST.getStatusCode()) {
			try {
				JAXBContext jc = JAXBContext.newInstance(Errors.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				Object obj = unmarshaller.unmarshal(new StringReader(s));
				if (obj instanceof Errors) {
					Errors errors = (Errors) obj;
					throw new HighriseException(response.getStatus(), errors.getError());
				}
			} catch (JAXBException e) {
				throw new HighriseException("Error unmarshalling Highrise return.", e);
			}
		} else if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()) {
			throw new HighriseException(response.getStatus(), "Highrise return not expected (" 
					+ ClientResponse.Status.fromStatusCode(response.getStatus()) + ").");
		}
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T create(T entity, String path) {
		T result = null;
		ClientResponse response = this.post(path, entity);
		
		String s = response.getEntity(String.class);
		
		if (response.getStatus() == ClientResponse.Status.CREATED.getStatusCode() 
				|| response.getStatus() == ClientResponse.Status.BAD_REQUEST.getStatusCode()) {
			try {
				JAXBContext jc = JAXBContext.newInstance(entity.getClass(), Empty.class, Errors.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				Object obj = unmarshaller.unmarshal(new StringReader(s));
				if (obj instanceof Errors) {
					Errors errors = (Errors) obj;
					throw new HighriseException(response.getStatus(), errors.getError());
				} else if (!(obj instanceof Empty)) {
					result = (T) obj;
				}
			} catch (JAXBException e) {
				throw new HighriseException("Error unmarshalling Highrise return.", e);
			}
		} else {
			throw new HighriseException(response.getStatus(), "Highrise return not expected (" 
					+ ClientResponse.Status.fromStatusCode(response.getStatus()) + ").");
		}
		
		return result;
	}
        
        @SuppressWarnings("unchecked")
	protected void remove(String path) {
		ClientResponse response = this.delete(path);
                if (response.getStatus() != ClientResponse.Status.OK.getStatusCode()){
                    throw  new HighriseException(response.getStatus(), "Highrise return not expected (" 
					+ ClientResponse.Status.fromStatusCode(response.getStatus()) + ").");
                }
        }
	
	@SuppressWarnings("unchecked")
	protected <T, W extends ListWrapper<T>> List<T> getAsList(Class<T> objectType, Class<W> listWrapType, String path, MultivaluedMap<String, String> params) {
		List<T> result = new ArrayList<T>();
		
		ClientResponse response = this.get(path, params);
		
		if (response.getStatus() == ClientResponse.Status.OK.getStatusCode()) {
			try {
				String s = response.getEntity(String.class);
				//System.out.println(s);
				
				JAXBContext jc = JAXBContext.newInstance(listWrapType, Empty.class);
				Unmarshaller unmarshaller = jc.createUnmarshaller();
				Object obj = unmarshaller.unmarshal(new StringReader(s));
				if (!(obj instanceof Empty)) {
					result = ((W) obj).getObjects();
				}
			} catch (JAXBException e) {
				throw new HighriseException("Error unmarshalling Highrise return as list.", e);
			}
		} else {
			throw new HighriseException(response.getStatus(), "Highrise return not expected (" 
					+ ClientResponse.Status.fromStatusCode(response.getStatus()) + ").");
		}
		
		return result;
	}
	
	private WebResource.Builder getBuilder(String path, MultivaluedMap<String, String> params) {
		if (params == null) {
			params = new MultivaluedMapImpl();
		}
		
		return webResource.path(path)
				.queryParams(params)
				.accept(MediaType.APPLICATION_XML)
				.header("Authorization", authorization);
	}
	
	private ClientResponse get(String path, MultivaluedMap<String, String> params) {
		WebResource.Builder builder = this.getBuilder(path, params);
		return builder.get(ClientResponse.class);
	}
	
	protected ClientResponse post(String path, Object entity) {
		WebResource.Builder builder = this.getBuilder(path, null);
		return builder.entity(entity).post(ClientResponse.class);
	}
	
	private ClientResponse put(String path, Object entity) {
		WebResource.Builder builder = this.getBuilder(path, null);
		return builder.entity(entity).put(ClientResponse.class);
	}
        
        private ClientResponse delete(String path) {
		WebResource.Builder builder = this.getBuilder(path, null);
                return builder.delete(ClientResponse.class);
        }
	
}
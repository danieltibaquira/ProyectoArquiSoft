import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.sun.jersey.api.client.config.ClientConfig;

public class RestClient {
	private static final String URI_VERIFICAR 
    = "http://localhost:5000/api/mastercard/validar";
	
	private static final String URI_COMPRAR 
    = "http://localhost:5000/api/mastercard/comprar";

  private Client client = ClientBuilder.newClient().property(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true);

  public Response comprar(VerificarTarjeta emp) {
	    return client
	      .target(URI_COMPRAR)
	      .request(MediaType.APPLICATION_JSON).property(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true)
	      .put(Entity.entity(emp, MediaType.APPLICATION_JSON)
	    		  );
	}
  
 
  public Response verificarTarjeta(VerificarTarjeta emp) {
	    return client
	      .target(URI_VERIFICAR)
	      .request(MediaType.APPLICATION_JSON).property(ClientConfig.PROPERTY_FOLLOW_REDIRECTS, true)
	      .post(Entity.entity(emp, MediaType.APPLICATION_JSON)
	    		  );
	}
}

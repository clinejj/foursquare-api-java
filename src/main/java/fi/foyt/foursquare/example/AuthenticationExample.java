package fi.foyt.foursquare.example;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;

/**
 * Basic example of how to do authentication
 * @author rmangi
 *
 */
public class AuthenticationExample {
	  public void authenticationRequest(HttpServletRequest request, HttpServletResponse response) {
		    FoursquareApi foursquareApi = new FoursquareApi("Client ID", "Client Secret", "Callback URL");
		    try {
		      // First we need to redirect our user to authentication page. 
		      response.sendRedirect(foursquareApi.getAuthenticationUrl());
		    } catch (IOException e) {
		      // TODO: Error handling
		    }
		  }
		  
		  public void handleCallback(HttpServletRequest request, HttpServletResponse response) {
		    // After user has logged in and confirmed that our program may access user's Foursquare account
		    // Foursquare redirects user back to callback url. 
		    FoursquareApi foursquareApi = new FoursquareApi("Client ID", "Client Secret", "Callback URL");
		    // Callback url contains authorization code 
		    String code = request.getParameter("code");
		    try {
		      // finally we need to authenticate that authorization code 
		      foursquareApi.authenticateCode(code);
		      // ... and voilà we have a authenticated Foursquare client
		    } catch (FoursquareApiException e) {
		     // TODO: Error handling
		    }
		  }
}

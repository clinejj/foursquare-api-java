package fi.foyt.foursquare.example;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

/**
 * Basic search example
 * @TODO - more examples please :)
 * @author rmangi
 *
 */
public class BasicExample {

	  public static void main(String[] args) {
	    String ll = args.length > 0 ? args[0] : "44.3,37.2";
	    try {
	      (new BasicExample()).searchVenues(ll);
	    } catch (FoursquareApiException e) {
	      // TODO: Error handling
	    }
	  }

	  public void searchVenues(String ll) throws FoursquareApiException {
	    // First we need a initialize FoursquareApi. 
	    FoursquareApi foursquareApi = new FoursquareApi("Client ID", "Client Secret", "Callback URL");
	    
	    // After client has been initialized we can make queries.
	    Result<VenuesSearchResult> result = foursquareApi.venuesSearch(ll, null, null, null, null, null, null, null, null, null, null, null, null);
	    
	    if (result.getMeta().getCode() == 200) {
	      // if query was ok we can finally we do something with the data
	      for (CompactVenue venue : result.getResult().getVenues()) {
	        // TODO: Do something we the data
	        System.out.println(venue.getName());
	      }
	    } else {
	      // TODO: Proper error handling
	      System.out.println("Error occured: ");
	      System.out.println("  code: " + result.getMeta().getCode());
	      System.out.println("  type: " + result.getMeta().getErrorType());
	      System.out.println("  detail: " + result.getMeta().getErrorDetail()); 
	    }
	  }
}

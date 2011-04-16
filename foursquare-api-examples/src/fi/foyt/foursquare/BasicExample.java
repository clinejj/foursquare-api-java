package fi.foyt.foursquare;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.entities.VenueGroup;

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
    VenueGroup[] venueGroups = foursquareApi.venuesSearch(ll, null, null, null, null, null, null);
    
    // Finally we do something with the data
    for (VenueGroup venueGroup : venueGroups) {
      // TODO: Do something we the data
      System.out.println(venueGroup.getName());
    }
  }
}

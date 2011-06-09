package fi.foyt.foursquare.api.tests;

import fi.foyt.foursquare.api.FoursquareApi;

public class TestUtils {

  public static FoursquareApi getAnonymousFoursquareApi() {
    FoursquareApi foursquareApi = new FoursquareApi(CLIENT_ID, CLIENT_SECRET, REDIRECT_URL, new TestIO());
    foursquareApi.setSkipNonExistingFields(false);
    return foursquareApi;
  }
  
  public static FoursquareApi getAuthenticatedFoursquareApi() {
    FoursquareApi foursquareApi = new FoursquareApi(CLIENT_ID, CLIENT_SECRET, REDIRECT_URL, OAUTH, new TestIO());
    foursquareApi.setSkipNonExistingFields(false);
    return foursquareApi;
  }
  
  private final static String CLIENT_ID = "FAKE_CLIENT_ID";
  private final static String CLIENT_SECRET = "FAKE_CLIENT_SECRET";
  private final static String REDIRECT_URL = "FAKE_REDIRECT_URL";
  private final static String OAUTH = "FAKE_OAUTH";
}

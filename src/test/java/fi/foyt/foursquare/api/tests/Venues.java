package fi.foyt.foursquare.api.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.Category;
import fi.foyt.foursquare.api.entities.CheckinGroup;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.CompleteVenue;
import fi.foyt.foursquare.api.entities.LinkGroup;
import fi.foyt.foursquare.api.entities.PhotoGroup;
import fi.foyt.foursquare.api.entities.Recommended;
import fi.foyt.foursquare.api.entities.TipGroup;
import fi.foyt.foursquare.api.entities.Todo;
import fi.foyt.foursquare.api.entities.VenueGroup;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;

public class Venues {

  @Test
  public final void testVenue() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAnonymousFoursquareApi();
    
    Result<CompleteVenue> result = foursquareApi.venue("5104");

    assertEquals("40a55d80f964a52020f31ee3", result.getResult().getId());
    assertEquals("Clinton St. Baking Co. & Restaurant", result.getResult().getName());
    assertEquals(new Double(40.721294), result.getResult().getLocation().getLat());
    assertEquals(new Double(-73.983994), result.getResult().getLocation().getLng());
    assertEquals("United States", result.getResult().getLocation().getCountry());
    assertEquals("at E Houston St", result.getResult().getLocation().getCrossStreet());
    assertEquals("4bf58dd8d48988d143941735", result.getResult().getCategories()[0].getId());
    assertEquals(false, result.getResult().getVerified());
    assertEquals(new Integer(14765), result.getResult().getStats().getCheckinsCount());
    assertEquals(new Integer(10752), result.getResult().getStats().getUsersCount());
    assertEquals("http://www.clintonstreetbaking.com", result.getResult().getUrl());
    assertEquals(new Long(5), result.getResult().getHereNow().getCount());
    assertEquals("475129", result.getResult().getMayor().getUser().getId());
    assertEquals(new Long(372), result.getResult().getTips().getCount());
    assertEquals(new Long(372), result.getResult().getTips().getGroups()[0].getCount());
    assertEquals("4a7a511970c603bbd64e8eb4", result.getResult().getTips().getGroups()[0].getItems()[0].getId());
    assertTrue(result.getResult().getCategories()[0].getPrimary());
    assertArrayEquals(new String[] {"bakery", "baking", "breakfast", "brunch", "fried chicken", "pancakes", "scones", "southern"}, result.getResult().getTags());
    assertEquals(null, result.getResult().getSpecialsNearby());
    assertEquals("http://4sq.com/1UrXyu", result.getResult().getShortUrl());
    assertEquals("America/New_York", result.getResult().getTimeZone());
    assertEquals(null, result.getResult().getBeenHere());
    assertEquals(new Long(996), result.getResult().getPhotos().getCount());
    assertEquals(null, result.getResult().getDescription());
  }

  @Test
  public final void testVenueSpecials() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAnonymousFoursquareApi();
    
    Result<CompleteVenue> result = foursquareApi.venue("4cb38bf20cdc721ea943234f");
  
    assertEquals(new Integer(200), result.getMeta().getCode());
    assertEquals("4cb38bf20cdc721ea943234f", result.getResult().getId());
    assertEquals("4da37ddb15ad530c110a9d52", result.getResult().getSpecials().getItems()[0].getId());
    assertEquals("count", result.getResult().getSpecials().getItems()[0].getType());
    assertEquals("Want 20% off? Check in at RadioShack for the first time & get 20% off your qualifying purchase. Ends 6/30/11. Exclusions apply. Ask associate for details.", result.getResult().getSpecials().getItems()[0].getMessage());
    assertEquals("on your 1st check-in", result.getResult().getSpecials().getItems()[0].getDescription());
    assertEquals(false, result.getResult().getSpecials().getItems()[0].getUnlocked());
    assertEquals("newbie", result.getResult().getSpecials().getItems()[0].getIcon());
    assertEquals("Newbie Special", result.getResult().getSpecials().getItems()[0].getTitle());
    assertEquals("locked", result.getResult().getSpecials().getItems()[0].getState());
    assertEquals("foursquare", result.getResult().getSpecials().getItems()[0].getProvider());
    assertEquals("standard", result.getResult().getSpecials().getItems()[0].getRedemption());
  }
  
  @Test
  public final void testVenuesAdd() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    
    String name = "Apuvälineyksikkö / Moision toimipiste";
    String address = "Moisiontie 11 b";
    String city = "Mikkeli";
    String state = "Etelä-Savo";
    String zip = "50520";
    String phone = "0443516511";
    String primaryCategoryId = "4bf58dd8d48988d104941735";
    Double lat = 61.677701;
    Double lng = 27.272585;
    String ll = lat + "," + lng;
    
    Result<CompleteVenue> completeVenue = foursquareApi.venuesAdd(name, address, null, city, state, zip, phone, ll, primaryCategoryId);
    
    assertEquals(name, completeVenue.getResult().getName());
    assertEquals(address, completeVenue.getResult().getLocation().getAddress());
    assertEquals(city, completeVenue.getResult().getLocation().getCity());
    assertEquals(state, completeVenue.getResult().getLocation().getState());
    assertEquals(zip, completeVenue.getResult().getLocation().getPostalCode());
    assertEquals(phone, completeVenue.getResult().getContact().getPhone());
    assertEquals(lat, completeVenue.getResult().getLocation().getLat());
    assertEquals(lng, completeVenue.getResult().getLocation().getLng());
    assertEquals(new Double(0.0), completeVenue.getResult().getLocation().getDistance());
    
  
    assertEquals("checkin", completeVenue.getResult().getPhotos().getGroups()[0].getType());
    assertEquals("friends' checkin photos", completeVenue.getResult().getPhotos().getGroups()[0].getName());
    assertEquals(new Long(0), completeVenue.getResult().getPhotos().getGroups()[0].getCount());
  }
  
  @Test
  public final void testVenuesCategories() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAnonymousFoursquareApi();
    
    Result<Category[]> result = foursquareApi.venuesCategories();
    assertEquals("Arts & Entertainment", result.getResult()[0].getName());
    assertEquals("Arts & Entertainment", result.getResult()[0].getPluralName());
    assertEquals("https://foursquare.com/img/categories/arts_entertainment/default.png", result.getResult()[0].getIcon());
    assertEquals("4bf58dd8d48988d1e1931735", result.getResult()[0].getCategories()[0].getId());
    assertEquals("Arcade", result.getResult()[0].getCategories()[0].getName());
    assertEquals("Arcades", result.getResult()[0].getCategories()[0].getPluralName());
    assertEquals("https://foursquare.com/img/categories/arts_entertainment/arcade.png", result.getResult()[0].getCategories()[0].getIcon());
    assertEquals(0, result.getResult()[0].getCategories()[0].getCategories().length);
    
  }
  
  @Test
  public final void testVenuesSearch() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAnonymousFoursquareApi();
  
    Result<VenuesSearchResult> result = foursquareApi.venuesSearch("40.7,-74", null, null, null, null, null, null, null, null, null, null, null, null);
    assertEquals(new Integer(200), result.getMeta().getCode());
  
    VenueGroup trendingGroup = result.getResult().getGroups()[0];
    
    assertEquals("trending", trendingGroup.getType());
    assertEquals("Trending Now", trendingGroup.getName());
    assertEquals("4116be80f964a520fc0b1fe3", trendingGroup.getItems()[0].getId());
    assertEquals("Ulysses Folk House", trendingGroup.getItems()[0].getName());
    assertEquals("2124820400", trendingGroup.getItems()[0].getContact().getPhone());
    assertEquals("ulyssesfolkhous", trendingGroup.getItems()[0].getContact().getTwitter());
    assertEquals(new Double(40.70443641202592), trendingGroup.getItems()[0].getLocation().getLat());
    assertEquals(new Double(-74.01012361049652), trendingGroup.getItems()[0].getLocation().getLng());
    assertEquals("4bf58dd8d48988d116941735", trendingGroup.getItems()[0].getCategories()[0].getId());
    assertEquals(true, trendingGroup.getItems()[0].getVerified());
    assertEquals(new Integer(5656), trendingGroup.getItems()[0].getStats().getCheckinsCount());
    assertEquals(new Integer(3453), trendingGroup.getItems()[0].getStats().getUsersCount());
    // assertEquals(new Long(0), trendingGroup.getItems()[0].getTodos().getCount());
    assertEquals(new Long(15), trendingGroup.getItems()[0].getHereNow().getCount());
    
    VenueGroup nearbyGroup = result.getResult().getGroups()[1];
    assertEquals("nearby", nearbyGroup.getType());
    assertEquals("Nearby", nearbyGroup.getName());
    assertEquals("4b81ea40f964a520e0c330e3", nearbyGroup.getItems()[0].getId());
    assertEquals("Brooklyn Bridge Park - Pier 1", nearbyGroup.getItems()[0].getName());
    assertEquals(new Double(40.701984159668676), nearbyGroup.getItems()[0].getLocation().getLat());
    assertEquals(new Double(-73.9969539642334), nearbyGroup.getItems()[0].getLocation().getLng());
    assertEquals("4bf58dd8d48988d163941735", nearbyGroup.getItems()[0].getCategories()[0].getId());
    assertEquals(false, nearbyGroup.getItems()[0].getVerified());
    assertEquals(new Integer(4232), nearbyGroup.getItems()[0].getStats().getCheckinsCount());
    assertEquals(new Integer(2707), nearbyGroup.getItems()[0].getStats().getUsersCount());
    // assertEquals(new Long(0), nearbyGroup.getItems()[0].getTodos().getCount());
    assertEquals(new Long(0), nearbyGroup.getItems()[0].getHereNow().getCount());
  }
  
  @Test
  public final void testVenuesSearch20110615() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAnonymousFoursquareApi();
  
    Result<VenuesSearchResult> result = foursquareApi.venuesSearch("40.7,-74.0", null, null, null, null, null, null, null, null, null, null, null, null);
    assertEquals(new Integer(200), result.getMeta().getCode());
    
    assertNull(result.getResult().getGroups());
    
    CompactVenue venue = result.getResult().getVenues()[0];
    
    assertEquals("4b81ea40f964a520e0c330e3", venue.getId());
    assertEquals("Brooklyn Bridge Park - Pier 1", venue.getName());
    assertEquals(new Double(40.701984159668676), venue.getLocation().getLat());
    assertEquals(new Double(-73.9969539642334), venue.getLocation().getLng());
    assertEquals("4bf58dd8d48988d163941735", venue.getCategories()[0].getId());
    assertEquals(false, venue.getVerified());
    assertEquals(new Integer(4532), venue.getStats().getCheckinsCount());
    assertEquals(new Integer(2997), venue.getStats().getUsersCount());
    assertEquals(new Long(3), venue.getHereNow().getCount());
  }
  
  @Test
  public final void testVenuesTrending() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAnonymousFoursquareApi();
    
    Result<CompactVenue[]> result = foursquareApi.venuesTrending("40.7,-74", null, null);
    
    assertEquals("4116be80f964a520fc0b1fe3", result.getResult()[0].getId());
    assertEquals("Ulysses Folk House", result.getResult()[0].getName());
    assertEquals("2124820400", result.getResult()[0].getContact().getPhone());
    assertEquals("ulyssesfolkhous", result.getResult()[0].getContact().getTwitter());
    assertEquals(new Double(40.70443641202592), result.getResult()[0].getLocation().getLat());
    assertEquals(new Double(-74.01012361049652), result.getResult()[0].getLocation().getLng());
    assertEquals("4bf58dd8d48988d116941735", result.getResult()[0].getCategories()[0].getId());
    assertEquals(true, result.getResult()[0].getVerified());
    assertEquals(new Integer(5656), result.getResult()[0].getStats().getCheckinsCount());
    assertEquals(new Integer(3453), result.getResult()[0].getStats().getUsersCount());
    // assertEquals(new Long(0), result.getResult()[0].getTodos().getCount());
    assertEquals(new Long(12), result.getResult()[0].getHereNow().getCount());
  }
  
  @Test
  public final void testVenuesProposeEdit() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<Object> result = foursquareApi.venuesProposeEdit("4de88f43d22d09215a1f73e1", "Apuvälineyksikkö / Moision toimipiste", "Moisiontie 11 b", null, "Mikkeli", "Etelä-Savo", "50520", "0443516511", "61.677701,27.272585", "4bf58dd8d48988d104941735");
  
    assertEquals(new Integer(200), result.getMeta().getCode());
  }
  
  @Test
  public final void testVenuesEdit() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<Object> result = foursquareApi.venuesEdit("4de88f43d22d09215a1f73e1", "Apuvälineyksikkö / Moision toimipiste", "Moisiontie 11 b", null, "Mikkeli", "Etelä-Savo", "50520", "0443516511", "61.677701,27.272585", "4bf58dd8d48988d104941735", null, null, null);
  
    assertEquals(new Integer(200), result.getMeta().getCode());
  }
  
  @Test
  public final void testVenuesExplore() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAnonymousFoursquareApi();
    Result<Recommended> result = foursquareApi.venuesExplore("40.7,-74", null, null, null, null, null, null, null, null);
    
    assertEquals(new Integer(200), result.getMeta().getCode());
    
    assertEquals(new Long(30), result.getResult().getKeywords().getCount());
    assertEquals("Park", result.getResult().getKeywords().getItems()[0].getDisplayName());
    assertEquals("Park", result.getResult().getKeywords().getItems()[0].getKeyword());
    assertEquals("recommended", result.getResult().getGroups()[0].getType());
    assertEquals("Recommended Places", result.getResult().getGroups()[0].getName());
    assertEquals(new Long(1), result.getResult().getGroups()[0].getItems()[0].getReasons().getCount());
    assertEquals("general", result.getResult().getGroups()[0].getItems()[0].getReasons().getItems()[0].getType());
    assertEquals("This place is popular on foursquare.", result.getResult().getGroups()[0].getItems()[0].getReasons().getItems()[0].getMessage());
    assertEquals("4a9ff488f964a520b73d20e3", result.getResult().getGroups()[0].getItems()[0].getVenue().getId());
    assertEquals("4abe660770c603bb50838eb4", result.getResult().getGroups()[0].getItems()[0].getTips()[0].getId());
  }
  
  @Test
  public final void testVenuesExploreWarning() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAnonymousFoursquareApi();
    Result<Recommended> result = foursquareApi.venuesExplore("10.7,-74", null, null, null, null, null, null, null, null);
    assertEquals(new Integer(200), result.getMeta().getCode());
    assertEquals("There aren't a lot of results near you. Try expanding the search area by tapping the settings button on the top left of the screen.", result.getResult().getWarning().getText());
  }
  
  @Test
  public final void testVenuesHereNow() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAnonymousFoursquareApi();
    Result<CheckinGroup> result = foursquareApi.venuesHereNow("5104", null, null, null);
    assertEquals(new Integer(200), result.getMeta().getCode());
    assertEquals(new Long(1), result.getResult().getCount());
    assertEquals("4df0c05b1f6ea135c65c307f", result.getResult().getItems()[0].getId());
    assertEquals(new Long(1307623515), result.getResult().getItems()[0].getCreatedAt());
    assertEquals("checkin", result.getResult().getItems()[0].getType());
    // assertEquals("America/New_York", result.getResult().getItems()[0].getTimeZone());
    assertEquals("2863506", result.getResult().getItems()[0].getUser().getId());
  }
  
  @Test
  public final void testVenuesLinks() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAnonymousFoursquareApi();
    Result<LinkGroup> result = foursquareApi.venuesLinks("3fd66200f964a52074e31ee3");
    assertEquals(new Integer(200), result.getMeta().getCode());
    assertEquals(new Long(5), result.getResult().getCount());
    assertEquals("nyt", result.getResult().getItems()[0].getProvider().getId());
    assertEquals("1002207971611", result.getResult().getItems()[0].getLinkedId());
    assertEquals("http://www.nytimes.com/restaurants/1002207971611/db-bistro-moderne/details.html", result.getResult().getItems()[0].getUrl());
  }
  
  @Test
  public final void testVenuesFlag() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<Object> result = foursquareApi.venuesFlag("4beb18a36295c9b669478708", "closed", null);
    assertEquals(new Integer(200), result.getMeta().getCode());
  }
  
  @Test
  public final void testVenuesTips() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<TipGroup> result = foursquareApi.venuesTips("5104", "recent", null, null);
    assertEquals(new Integer(200), result.getMeta().getCode());
    
    assertEquals(new Long(103), result.getResult().getCount());
    assertEquals("4def98fefa76c1ceac95dec0", result.getResult().getItems()[0].getId());
    assertEquals(new Long(1307547902), result.getResult().getItems()[0].getCreatedAt());
    assertEquals("blueberry pancakes + sugar cured bacon + scrambled eggs = a plate full of goodness", result.getResult().getItems()[0].getText());
    assertEquals(new Long(0), result.getResult().getItems()[0].getTodo().getCount());
    assertEquals(new Long(1), result.getResult().getItems()[0].getDone().getCount());
    assertEquals("1537499", result.getResult().getItems()[0].getUser().getId());
    assertEquals("https://playfoursquare.s3.amazonaws.com/pix/APSJTPELZ5LGL0AYHGKVQEECS5QSXGCQYULHAXBLR5ZTDNS2.jpg", result.getResult().getItems()[10].getPhotoURL());
    assertEquals("https://playfoursquare.s3.amazonaws.com/pix/APSJTPELZ5LGL0AYHGKVQEECS5QSXGCQYULHAXBLR5ZTDNS2.jpg", result.getResult().getItems()[10].getPhoto().getUrl());
  }
  
  @Test
  public final void testVenuesMarkTodo() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<Todo> result = foursquareApi.venuesMarkTodo("4b81ea40f964a520e0c330e3", null);
    
    assertEquals(new Integer(200), result.getMeta().getCode());
    assertEquals("4df4488122718759f822fe44", result.getResult().getId());  
    assertEquals(new Long(1307854977), result.getResult().getCreatedAt());
    assertEquals("4d13a1edf898b1f73ac3e181", result.getResult().getTip().getId());
    assertEquals(new Long(1293132269), result.getResult().getTip().getCreatedAt());
    assertEquals("", result.getResult().getTip().getText());
    assertEquals("todo", result.getResult().getTip().getStatus());
    assertEquals(new Long(6), result.getResult().getTip().getTodo().getCount());
    assertEquals(new Long(3), result.getResult().getTip().getDone().getCount());
    assertEquals("4b81ea40f964a520e0c330e3", result.getResult().getTip().getVenue().getId());
  }
  
  @Test
  public final void testVenuesPhotos() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<PhotoGroup> result = foursquareApi.venuesPhotos("43695300f964a5208c291fe3", "venue", null, null);
    
    assertEquals(new Integer(200), result.getMeta().getCode());
    assertEquals(new Long(28), result.getResult().getCount());
    assertEquals("4ddf19dea12d19527932b4eb", result.getResult().getItems()[0].getId());
    assertEquals(new Long(1306466782), result.getResult().getItems()[0].getCreatedAt());
    assertEquals("https://playfoursquare.s3.amazonaws.com/pix/ELYWFCR5XDLEKDHDCVBCIZZJFJREEQQPOF4B0Z2MPQ433HSN.jpg", result.getResult().getItems()[0].getUrl());
    assertEquals(new Long(4), result.getResult().getItems()[0].getSizes().getCount());
    assertEquals(new Integer(720), result.getResult().getItems()[0].getSizes().getItems()[0].getHeight());
    assertEquals(new Integer(537), result.getResult().getItems()[0].getSizes().getItems()[0].getWidth());
    assertEquals("https://playfoursquare.s3.amazonaws.com/pix/ELYWFCR5XDLEKDHDCVBCIZZJFJREEQQPOF4B0Z2MPQ433HSN.jpg", result.getResult().getItems()[0].getSizes().getItems()[0].getUrl());
    assertEquals("foursquare for iPhone", result.getResult().getItems()[0].getSource().getName());
    assertEquals("https://foursquare.com/download/#/iphone", result.getResult().getItems()[0].getSource().getUrl());
    assertEquals("2910458", result.getResult().getItems()[0].getUser().getId());
  }
}

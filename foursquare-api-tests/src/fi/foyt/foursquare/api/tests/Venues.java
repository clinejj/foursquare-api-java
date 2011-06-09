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
import fi.foyt.foursquare.api.entities.Recommended;
import fi.foyt.foursquare.api.entities.VenueGroup;

public class Venues {

  @Test
  public final void testVenue() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAnonymousFoursquareApi();
    
    Result<CompleteVenue> result = foursquareApi.venue("5104");
    
    assertEquals("40a55d80f964a52020f31ee3", result.getResult().getId());
    assertEquals("Clinton Street Baking Co", result.getResult().getName());
    assertEquals(new Double(40.721096), result.getResult().getLocation().getLat());
    assertEquals(new Double(-73.983937), result.getResult().getLocation().getLng());
    assertEquals("4bf58dd8d48988d143941735", result.getResult().getCategories()[0].getId());
    assertEquals(false, result.getResult().getVerified());
    assertEquals(new Integer(3072), result.getResult().getStats().getCheckinsCount());
    assertEquals(new Integer(2256), result.getResult().getStats().getUsersCount());
    assertEquals("http://www.clintonstreetbaking.com/", result.getResult().getUrl());
    assertEquals(new Long(0), result.getResult().getHereNow().getCount());
    assertEquals("57870", result.getResult().getMayor().getUser().getId());
    assertEquals(new Long(101), result.getResult().getTips().getCount());
    assertEquals(new Long(101), result.getResult().getTips().getGroups()[0].getCount());
    assertEquals("4c34ccbb6f1fef3bb7eeec3d", result.getResult().getTips().getGroups()[0].getItems()[0].getId());
  }

  @Test
  public final void testVenuesAdd() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthorizedFoursquareApi();
    
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
  
    Result<VenueGroup[]> result = foursquareApi.venuesSearch("40.7,-74", null, null, null, null, null, null);
    VenueGroup trendingGroup = result.getResult()[0];
    
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
    assertEquals(new Long(0), trendingGroup.getItems()[0].getTodos().getCount());
    assertEquals(new Long(15), trendingGroup.getItems()[0].getHereNow().getCount());
    
    VenueGroup nearbyGroup = result.getResult()[1];
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
    assertEquals(new Long(0), nearbyGroup.getItems()[0].getTodos().getCount());
    assertEquals(new Long(0), nearbyGroup.getItems()[0].getHereNow().getCount());
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
    assertEquals(new Long(0), result.getResult()[0].getTodos().getCount());
    assertEquals(new Long(12), result.getResult()[0].getHereNow().getCount());
  }
  
  @Test
  public final void testVenuesProposeEdit() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthorizedFoursquareApi();
    Result<Object> result = foursquareApi.venuesProposeEdit("4de88f43d22d09215a1f73e1", "Apuvälineyksikkö / Moision toimipiste", "Moisiontie 11 b", null, "Mikkeli", "Etelä-Savo", "50520", "0443516511", "61.677701,27.272585", "4bf58dd8d48988d104941735");
  
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
    assertEquals("America/New_York", result.getResult().getItems()[0].getTimeZone());
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

  
}

package fi.foyt.foursquare.api.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.CompleteSpecial;
import fi.foyt.foursquare.api.entities.SpecialGroup;

public class Specials {

  @Test
  public final void testSpecial() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAnonymousFoursquareApi();
    CompleteSpecial completeSpecial = foursquareApi.special("4da37ddb15ad530c110a9d52", "4cb38bf20cdc721ea943234f").getResult();
  
    assertEquals("4da37ddb15ad530c110a9d52", completeSpecial.getId());
    assertEquals("count", completeSpecial.getType());
    assertEquals("Want 20% off? Check in at RadioShack for the first time & get 20% off your qualifying purchase. Ends 6/30/11. Exclusions apply. Ask associate for details.", completeSpecial.getMessage());
    assertEquals("on your 1st check-in", completeSpecial.getDescription());
    assertEquals(false, completeSpecial.getUnlocked());
    assertEquals("newbie", completeSpecial.getIcon());
    assertEquals("Newbie Special", completeSpecial.getTitle());
    assertEquals("locked", completeSpecial.getState());
    assertEquals("foursquare", completeSpecial.getProvider());
    assertEquals("standard", completeSpecial.getRedemption()); 
  }

  @Test
  public final void testSpecialsSearch() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    SpecialGroup specialGroup = foursquareApi.specialsSearch("40.7,-73.9", null, null, null, null).getResult();
    
    assertEquals(specialGroup.getCount(), new Long(28));
    assertEquals(specialGroup.getItems().length, 28);
    
    CompleteSpecial completeSpecial = specialGroup.getItems()[0];
    
    assertNotNull(completeSpecial);
    assertEquals("4da37ddb15ad530c110a9d52", completeSpecial.getId());
    assertEquals("Want 20% off? Check in at RadioShack for the first time & get 20% off your qualifying purchase. Ends 6/30/11. Exclusions apply. Ask associate for details.", completeSpecial.getMessage());
    assertEquals("newbie", completeSpecial.getIcon());
    assertEquals("Newbie Special", completeSpecial.getTitle());
    assertEquals("foursquare", completeSpecial.getProvider());
    assertEquals("standard", completeSpecial.getRedemption());
    
    CompactVenue compactVenue = completeSpecial.getVenue();
    
    assertNotNull(compactVenue);
    assertEquals("4cb38bf20cdc721ea943234f", compactVenue.getId());
    
    completeSpecial = specialGroup.getItems()[4];
    
    assertEquals("Offer valid thru 6/8/2011. One coupon per purchase. Not valid on prior purchases. Offer excludes Shopko locations. Offer valid in-store or online at Payless.com", completeSpecial.getFinePrint());
    
  }

}
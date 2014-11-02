package fi.foyt.foursquare.api.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

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
    assertEquals("frequency", completeSpecial.getType());
    assertEquals("Want 20% off? Check in at RadioShack for the first time & get 20% off your qualifying purchase. Ends 6/30/11. Exclusions apply. Ask associate for details.", completeSpecial.getMessage());
    assertEquals("", completeSpecial.getDescription());
    assertEquals(true, completeSpecial.getUnlocked());
    assertEquals("default", completeSpecial.getIcon());
    assertEquals("Special", completeSpecial.getTitle());
    assertEquals("unlocked", completeSpecial.getState());
    assertEquals("foursquare", completeSpecial.getProvider());
    assertEquals("webview", completeSpecial.getRedemption());
  }

  @Test
  public final void testSpecialsSearch() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    SpecialGroup specialGroup = foursquareApi.specialsSearch("40.7,-73.9", null, null, null, null).getResult();

    assertEquals(new Long(8), specialGroup.getCount());
    assertEquals(8, specialGroup.getItems().length);

    CompleteSpecial completeSpecial = specialGroup.getItems()[0];

    assertNotNull(completeSpecial);
    assertEquals("4e7c83e16365116cef70bfe1", completeSpecial.getId());
    assertEquals("Foursquare members 18 and over who check-in to the B&H Superstore during business hours will receive a B&H Signature Backpack or Tote bag with the purchase of $50 or more. One item per person and employees not eligible.", completeSpecial.getMessage());
    assertEquals("default", completeSpecial.getIcon());
    assertEquals("Special", completeSpecial.getTitle());
    assertEquals("foursquare", completeSpecial.getProvider());
    assertEquals("webview", completeSpecial.getRedemption());

    CompactVenue compactVenue = completeSpecial.getVenue();

    assertNotNull(compactVenue);
    assertEquals("4adb6119f964a520a72621e3", compactVenue.getId());

    completeSpecial = specialGroup.getItems()[2];

    assertEquals("One book per check-in, per person. Thank you!", completeSpecial.getFinePrint());

  }

}
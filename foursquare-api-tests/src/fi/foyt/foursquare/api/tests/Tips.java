package fi.foyt.foursquare.api.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompleteTip;

public class Tips {

  @Test
  public final void testTip() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAnonymousFoursquareApi();
    Result<CompleteTip> result = foursquareApi.tip("4b5e662a70c603bba7d790b4");
    
    assertEquals(new Integer(200), result.getMeta().getCode());
    assertEquals("4b5e662a70c603bba7d790b4", result.getResult().getId());
    assertEquals(new Long(1264477738), result.getResult().getCreatedAt());
    assertEquals("Get two slices and a can of soda for only $2.75!", result.getResult().getText());
    assertEquals("4a89cd2ff964a520100920e3", result.getResult().getVenue().getId());
    assertEquals("38141", result.getResult().getUser().getId());
    assertEquals(new Long(0), result.getResult().getTodo().getCount());
    assertEquals(new Long(7), result.getResult().getDone().getCount());
    assertEquals("318537", result.getResult().getDone().getGroups()[1].getItems()[0].getId());
  }

}

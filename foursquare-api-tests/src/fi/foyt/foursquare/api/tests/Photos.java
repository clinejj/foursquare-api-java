package fi.foyt.foursquare.api.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.Photo;

public class Photos {

  @Test
  public final void testPhoto() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<Photo> result = foursquareApi.photo("4d0fb8162d39a340637dc42b");

    assertEquals(new Integer(200), result.getMeta().getCode());
    assertEquals("4d0fb8162d39a340637dc42b", result.getResult().getId());
    assertEquals(new Long(1292875798), result.getResult().getCreatedAt());
    assertEquals("https://playfoursquare.s3.amazonaws.com/pix/UYU54GYOCURPAUYXH5V2U3EOM4DCX4VZPT3YOMN43H555KU2.jpg", result.getResult().getUrl());
    assertEquals(new Long(4), result.getResult().getSizes().getCount());
    assertEquals("https://playfoursquare.s3.amazonaws.com/pix/UYU54GYOCURPAUYXH5V2U3EOM4DCX4VZPT3YOMN43H555KU2.jpg", result.getResult().getSizes().getItems()[0].getUrl());
    assertEquals(new Integer(540), result.getResult().getSizes().getItems()[0].getWidth());
    assertEquals(new Integer(720), result.getResult().getSizes().getItems()[0].getHeight());
    assertEquals("2102", result.getResult().getUser().getId());
    assertEquals("439e8590f964a5200b2c1fe3", result.getResult().getVenue().getId());
    assertEquals("4d0fb80b6331a0932b685294", result.getResult().getTip().getId());
  }

}

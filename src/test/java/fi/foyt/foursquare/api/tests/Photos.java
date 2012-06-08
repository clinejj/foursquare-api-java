package fi.foyt.foursquare.api.tests;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

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
  
  @Test
  public final void testPhotoCheckin() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<Photo> result = foursquareApi.photo("4df3a350fd286d19d4beb926");

    assertEquals(new Integer(200), result.getMeta().getCode());
    assertEquals("4df3a350fd286d19d4beb926", result.getResult().getId());
    assertEquals("4de470c0ae60e7f3ac1f0fa7", result.getResult().getCheckin().getId());
  }

  @Test
  public final void testPhotosAdd() throws FoursquareApiException, IOException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    byte[] data = TestUtils.getFileData("/data/photos/test.jpg");
    Result<Photo> result = foursquareApi.photosAdd("4de470c0ae60e7f3ac1f0fa7", null, null, null, null, null, null, null, data);
  
    assertEquals(new Integer(200), result.getMeta().getCode());
    assertEquals("4df3a350fd286d19d4beb926", result.getResult().getId());
    assertEquals(new Long(1307812688), result.getResult().getCreatedAt());
    assertEquals("https://playfoursquare.s3.amazonaws.com/pix/XZVRBK41YC4BEQULXHQWHBTR4RMH03RF3QBEPS5FQBACHFMU.jpg", result.getResult().getUrl());
    assertEquals(new Long(4), result.getResult().getSizes().getCount());
    assertEquals("https://playfoursquare.s3.amazonaws.com/pix/XZVRBK41YC4BEQULXHQWHBTR4RMH03RF3QBEPS5FQBACHFMU.jpg", result.getResult().getSizes().getItems()[0].getUrl());
    assertEquals(new Integer(450), result.getResult().getSizes().getItems()[0].getWidth());
    assertEquals(new Integer(664), result.getResult().getSizes().getItems()[0].getHeight());
    assertEquals("Test Bench", result.getResult().getSource().getName());
    assertEquals("http://localhost", result.getResult().getSource().getUrl());
  }
  

}

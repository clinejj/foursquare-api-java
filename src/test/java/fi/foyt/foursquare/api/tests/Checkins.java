package fi.foyt.foursquare.api.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.Checkin;
import fi.foyt.foursquare.api.entities.CheckinGroup;
import fi.foyt.foursquare.api.entities.Comment;
import fi.foyt.foursquare.api.entities.CommentGroup;

public class Checkins {

  @Test
  public void testCheckin() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Checkin checkin = foursquareApi.checkin("4d627f6814963704dc28ff94", "LPtzP4edmpbaspdKhI9-892UoFM").getResult();

    assertEquals("4d627f6814963704dc28ff94", checkin.getId());
    assertEquals(new Long(1298300776), checkin.getCreatedAt());
    assertEquals("checkin", checkin.getType());
    assertEquals("Another one of these days. #snow", checkin.getShout());
    assertEquals("America/New_York", checkin.getTimeZone());
    assertEquals("32", checkin.getUser().getId());
    assertEquals("408c5100f964a520c6f21ee3", checkin.getVenue().getId());
    assertNotNull(checkin.getSource());
    assertEquals(new Long(1), checkin.getPhotos().getCount());
  }
  
  @Test
  public void testCheckinVenueless() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<Checkin> result = foursquareApi.checkinsAdd(null, "Test", null, "public", "40,40", null, null, null);
    Checkin checkin = result.getResult();
    
    assertEquals("4de470c0ae60e7f3ac1f0fa7", checkin.getId());
    assertEquals(new Long(1306816704), checkin.getCreatedAt());
    assertEquals("venueless", checkin.getType());
    assertEquals("Europe/Istanbul", checkin.getTimeZone());
    assertEquals("Test", checkin.getLocation().getName());
    assertEquals(new Double(40), checkin.getLocation().getLat());
    assertEquals(new Double(40), checkin.getLocation().getLng());
  }
  
  @Test
  public  void testCheckinComments() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Checkin checkin = foursquareApi.checkin("4d7b44d7f260a0932e5024ba", null).getResult();

    assertEquals("4d7b44d7f260a0932e5024ba", checkin.getId());
    CommentGroup comments = checkin.getComments();
    assertEquals(new Long(1), comments.getCount());
    Comment comment = comments.getItems()[0];
    assertEquals("4d7b457a8f89224b8ab38d26", comment.getId());
    assertEquals(new Long(1299924346), comment.getCreatedAt());
    assertEquals("7613255", comment.getUser().getId());
    assertEquals("Test", comment.getText());
  }
  
  @Test
  public void testCheckinOverlaps() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Checkin checkin = foursquareApi.checkin("4de4762d52b1d38d299e6000", null).getResult();

    assertEquals("4de4762d52b1d38d299e6000", checkin.getId());
    CheckinGroup checkinGroup = checkin.getOverlaps();
    assertEquals(new Long(1), checkinGroup.getCount());
    Checkin overlap = checkinGroup.getItems()[0];
    assertEquals("4de476257d8b2547eb33ea23", overlap.getId());
    assertEquals(new Long(1306818085), overlap.getCreatedAt());
    assertEquals("checkin", overlap.getType());
    assertEquals("America/New_York", overlap.getTimeZone());
    assertEquals("7613255", overlap.getUser().getId());
  }
    
  @Test
  public void testCheckinsAdd() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<Checkin> result = foursquareApi.checkinsAdd("4c6bbfafa48420a1b09a0a0b", null, null, "private", "61.68777583849969,27.273173332214355", null, null, null);
    Checkin checkin = result.getResult();
    
    assertEquals("4de3212d2271bfb844acdf5d", checkin.getId());
    assertEquals(new Long(1306730797), checkin.getCreatedAt());
    assertEquals("checkin", checkin.getType());
    assertEquals("Europe/Helsinki", checkin.getTimeZone());
    assertEquals(true, checkin.isPrivate());
    assertEquals("4c6bbfafa48420a1b09a0a0b", checkin.getVenue().getId());
  }

  @Test
  public void testCheckinsRecent() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Checkin[] checkins = foursquareApi.checkinsRecent(null, null, null).getResult();
    assertEquals(1, checkins.length);
    Checkin checkin = checkins[0];
    assertEquals("4d7b7746f260a093ae7827ba", checkin.getId());
  }

  @Test
  public void testCheckinsAddComment() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<Comment> result = foursquareApi.checkinsAddComment("4de470c0ae60e7f3ac1f0fa7", "That's very testy!");
    
    assertEquals(new Integer(200), result.getMeta().getCode());
    assertEquals("4df3393b14954f21cf2d7543", result.getResult().getId());
    assertEquals(new Long(1307785531), result.getResult().getCreatedAt());
    assertEquals("10078668", result.getResult().getUser().getId());
    assertEquals("That's very testy!", result.getResult().getText());
  }

  @Test
  public void testCheckinsDeleteComment() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<Checkin> result = foursquareApi.checkinsDeleteComment("4de470c0ae60e7f3ac1f0fa7", "4df3393b14954f21cf2d7543");
    
    assertEquals(new Integer(200), result.getMeta().getCode());
    assertEquals("4de470c0ae60e7f3ac1f0fa7", result.getResult().getId());
    assertEquals(new Long(0), result.getResult().getComments().getCount());
    
  }
}

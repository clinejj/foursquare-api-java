package fi.foyt.foursquare.api.tests;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompleteTip;
import fi.foyt.foursquare.api.entities.Todo;

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
  
  @Test
  public final void testTipsAdd() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    
    String text = "Wonderful festival called Beautiful Days here at 19, 20 & 21 of August 2011";
    String url = "http://www.beautifuldays.org";
    
    Result<CompleteTip> result = foursquareApi.tipsAdd("4bb73a402ea19521b1a6ac2f", text, url);

    assertEquals(new Integer(200), result.getMeta().getCode());
    assertEquals(new Long(1307700304), result.getResult().getCreatedAt());
    assertEquals(text, result.getResult().getText());
    assertEquals(url, result.getResult().getUrl());
    assertEquals(new Long(0), result.getResult().getTodo().getCount());
    assertEquals(new Long(1), result.getResult().getDone().getCount());
    assertEquals("7613255", result.getResult().getUser().getId());
  }
  
  @Test
  public final void testTipsMarkTodo() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<Todo> result = foursquareApi.tipsMarkTodo("4bb8f41970c603bb64bf96b4");
    
    assertEquals(new Integer(200), result.getMeta().getCode());
    assertEquals("4df203b045dd4e26933a50ed", result.getResult().getId());
    assertEquals(new Long(1307706288), result.getResult().getCreatedAt());
    assertEquals("4bb8f41970c603bb64bf96b4", result.getResult().getTip().getId());
  }
  
  @Test
  public final void testTipsSearch() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<CompleteTip[]> result = foursquareApi.tipsSearch("40.7,-74", null, null, null, null);
    
    assertEquals(new Integer(200), result.getMeta().getCode());
    assertEquals("4d136a80d1848cfa57b1c371", result.getResult()[0].getId());
    assertEquals(new Long(1293118080), result.getResult()[0].getCreatedAt());
    assertEquals("4a7198f9f964a5204ad91fe3", result.getResult()[0].getVenue().getId());
    assertEquals("4773473", result.getResult()[0].getUser().getId());
  }

  @Test
  public final void testTipsMarkDone() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<CompleteTip> result = foursquareApi.tipsMarkDone("4d13a1edf898b1f73ac3e181");
    assertEquals(new Integer(200), result.getMeta().getCode());
    
    assertEquals("4d13a1edf898b1f73ac3e181", result.getResult().getId());
    assertEquals(new Long(1293132269), result.getResult().getCreatedAt());
    assertEquals("", result.getResult().getText());
    assertEquals("done", result.getResult().getStatus());
    assertEquals(new Long(7), result.getResult().getTodo().getCount());
    assertEquals(new Long(3), result.getResult().getDone().getCount());
    assertEquals("4b81ea40f964a520e0c330e3", result.getResult().getVenue().getId());
  }

  @Test
  public final void testTipsUnmark() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Result<CompleteTip> result = foursquareApi.tipsUnmark("4d13a1edf898b1f73ac3e181");
    assertEquals(new Integer(200), result.getMeta().getCode());
    
    assertEquals("4d13a1edf898b1f73ac3e181", result.getResult().getId());
    assertEquals(new Long(1293132269), result.getResult().getCreatedAt());
    assertEquals("", result.getResult().getText());
    assertEquals("done", result.getResult().getStatus());
    assertEquals(new Long(7), result.getResult().getTodo().getCount());
    assertEquals(new Long(3), result.getResult().getDone().getCount());
    assertEquals("4b81ea40f964a520e0c330e3", result.getResult().getVenue().getId());
  }

}

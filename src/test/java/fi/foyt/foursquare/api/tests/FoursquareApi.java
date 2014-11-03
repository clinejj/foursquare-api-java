package fi.foyt.foursquare.api.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.Result;
import fi.foyt.foursquare.api.entities.CompleteUser;
import fi.foyt.foursquare.api.entities.CompleteVenue;

public class FoursquareApi {

  @Test
  public final void testFoursquareApiStringStringString() {
    fi.foyt.foursquare.api.FoursquareApi foursquareApi = new fi.foyt.foursquare.api.FoursquareApi("clientId", "clientSecret", "redirectUrl");
    assertNull(foursquareApi.getOAuthToken());
  }

  @Test
  public final void testFoursquareApiStringStringStringIOHandler() {
    fi.foyt.foursquare.api.FoursquareApi foursquareApi = new fi.foyt.foursquare.api.FoursquareApi("clientId", "clientSecret", "redirectUrl");
    assertNull(foursquareApi.getOAuthToken());
  }

  @Test
  public final void testFoursquareApiStringStringStringStringIOHandler() {
    fi.foyt.foursquare.api.FoursquareApi foursquareApi = new fi.foyt.foursquare.api.FoursquareApi("clientId", "clientSecret", "redirectUrl", "token", new TestIO());
    assertEquals("token", foursquareApi.getOAuthToken());
  }

  @Test
  public final void testSetoAuthToken() {
    fi.foyt.foursquare.api.FoursquareApi foursquareApi = TestUtils.getAnonymousFoursquareApi();
    assertNull(foursquareApi.getOAuthToken());
    foursquareApi.setoAuthToken("token");
    assertEquals("token", foursquareApi.getOAuthToken());
  }

  @Test
  public final void testSetSkipNonExistingFields() {
    fi.foyt.foursquare.api.FoursquareApi foursquareApi = TestUtils.getAnonymousFoursquareApi();
    foursquareApi.setSkipNonExistingFields(true);
  }

  @Test
  public final void testGetAuthenticationUrl() {
    fi.foyt.foursquare.api.FoursquareApi foursquareApi = TestUtils.getAnonymousFoursquareApi();
    assertEquals("https://foursquare.com/oauth2/authenticate?client_id=FAKE_CLIENT_ID&response_type=code&redirect_uri=FAKE_REDIRECT_URL", foursquareApi.getAuthenticationUrl());
  }

  @Test
  public final void testAuthenticateCode() throws FoursquareApiException {
    fi.foyt.foursquare.api.FoursquareApi foursquareApi = TestUtils.getAnonymousFoursquareApi();
    foursquareApi.authenticateCode("FAKE_CODE");
    assertEquals("FAKE_TOKEN", foursquareApi.getOAuthToken());
  }

  @Test
  public final void testSetVersion() throws FoursquareApiException {
    fi.foyt.foursquare.api.FoursquareApi foursquareApi = TestUtils.getAnonymousFoursquareApi();
    foursquareApi.setVersion("20140131");
  }
  
  @Test
  public final void testCallback() throws FoursquareApiException {
    fi.foyt.foursquare.api.FoursquareApi foursquareApi = TestUtils.getAnonymousFoursquareApi();
    
    foursquareApi.setUseCallback(false);
    Result<CompleteVenue> result = foursquareApi.venue("5104");
    String venueIdNoCallback = result.getResult().getId();
    
    foursquareApi.setUseCallback(true);
    result = foursquareApi.venue("5104");
    String venueIdCallback = result.getResult().getId();
    
    assertEquals(venueIdCallback, venueIdNoCallback);
  }
  
  @Test
  public final void testIOError() throws FoursquareApiException {
    fi.foyt.foursquare.api.FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    foursquareApi.setUseCallback(false);
    assertFalse(foursquareApi.getUseCallback());
    Result<CompleteUser> result = foursquareApi.user("nonexisting");
    assertEquals(new Integer(404), result.getMeta().getCode());
    assertEquals("Not Found", result.getMeta().getErrorDetail());
    assertNull(result.getResult());
  }
  
  @Test
  public final void testIOErrorCallback() throws FoursquareApiException {
    fi.foyt.foursquare.api.FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    foursquareApi.setUseCallback(true);
    assertTrue(foursquareApi.getUseCallback());
    Result<CompleteUser> result = foursquareApi.user("gibberish");
    assertEquals(new Integer(400), result.getMeta().getCode());
    assertEquals("param_error", result.getMeta().getErrorType());
    assertEquals("Must provide a valid user ID or 'self.'", result.getMeta().getErrorDetail());
    assertNull(result.getResult());
  }
  
  @Test
  public final void testGetIOHandler() throws FoursquareApiException {
    fi.foyt.foursquare.api.FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    assertEquals(foursquareApi.getIOHandler().getClass(), TestIO.class);
  }
}

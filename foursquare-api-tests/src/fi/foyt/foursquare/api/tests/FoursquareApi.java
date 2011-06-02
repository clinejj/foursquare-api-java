package fi.foyt.foursquare.api.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fi.foyt.foursquare.api.FoursquareApiException;

public class FoursquareApi {

  @Test
  public final void testFoursquareApiStringStringString() {
    fi.foyt.foursquare.api.FoursquareApi foursquareApi = new fi.foyt.foursquare.api.FoursquareApi("clientId", "clientSecret", "redirectUrl");
    assertNull(foursquareApi.getOAuthToken());
  }

  @Test
  public final void testFoursquareApiStringStringStringIOHandler() {
    fi.foyt.foursquare.api.FoursquareApi foursquareApi = new fi.foyt.foursquare.api.FoursquareApi("clientId", "clientSecret", "redirectUrl", new TestIO());
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

}

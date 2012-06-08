package fi.foyt.foursquare.api.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.entities.Setting;

public class Settings {

  @Test
  public final void testSettingSet() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    
    Setting setting = foursquareApi.settingSet("receivePings", false).getResult();
    
    assertEquals(false, setting.getReceivePings());
    assertEquals(true, setting.getReceiveCommentPings());
    assertEquals(false, setting.getSendToTwitter());
    assertEquals(false, setting.getSendMayorshipsToTwitter());
    assertEquals(false, setting.getSendBadgesToTwitter());
    assertEquals(true, setting.getSendToFacebook());
    assertEquals(true, setting.getSendMayorshipsToFacebook());
    assertEquals(true, setting.getSendBadgesToFacebook());
    assertEquals("undetermined", setting.getForeignConsent());
  }

  @Test
  public final void testSettingsAll() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Setting setting = foursquareApi.settingsAll().getResult();
    
    assertEquals(true, setting.getReceivePings());
    assertEquals(true, setting.getReceiveCommentPings());
    assertEquals(false, setting.getSendToTwitter());
    assertEquals(false, setting.getSendMayorshipsToTwitter());
    assertEquals(false, setting.getSendBadgesToTwitter());
    assertEquals(true, setting.getSendToFacebook());
    assertEquals(true, setting.getSendMayorshipsToFacebook());
    assertEquals(true, setting.getSendBadgesToFacebook());
    assertEquals("undetermined", setting.getForeignConsent());
  }

}

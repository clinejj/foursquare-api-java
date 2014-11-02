package fi.foyt.foursquare.api.tests;

import static org.junit.Assert.assertEquals;

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
    assertEquals(false, setting.getSendToTwitter());
    assertEquals(false, setting.getSendToFacebook());
    assertEquals("undetermined", setting.getForeignConsent());
  }

  @Test
  public final void testSettingsAll() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthenticatedFoursquareApi();
    Setting setting = foursquareApi.settingsAll().getResult();

    assertEquals(false, setting.getReceivePings());
    assertEquals(false, setting.getSendToTwitter());
    assertEquals(false, setting.getSendToFacebook());
    assertEquals("undetermined", setting.getForeignConsent());
  }

}

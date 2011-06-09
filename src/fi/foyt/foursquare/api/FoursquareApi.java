/*
 * FoursquareAPI - Foursquare API for Java
 * Copyright (C) 2008 - 2011 Antti Leppä / Foyt
 * http://www.foyt.fi
 * 
 * License: 
 * 
 * Licensed under GNU Lesser General Public License Version 3 or later (the "LGPL")
 * http://www.gnu.org/licenses/lgpl.html
 */
package fi.foyt.foursquare.api;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fi.foyt.foursquare.api.entities.Category;
import fi.foyt.foursquare.api.entities.Checkin;
import fi.foyt.foursquare.api.entities.CheckinGroup;
import fi.foyt.foursquare.api.entities.CompactUser;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.CompleteSpecial;
import fi.foyt.foursquare.api.entities.CompleteTip;
import fi.foyt.foursquare.api.entities.CompleteUser;
import fi.foyt.foursquare.api.entities.CompleteVenue;
import fi.foyt.foursquare.api.entities.KeywordGroup;
import fi.foyt.foursquare.api.entities.Photo;
import fi.foyt.foursquare.api.entities.RecommendationGroup;
import fi.foyt.foursquare.api.entities.Recommended;
import fi.foyt.foursquare.api.entities.Setting;
import fi.foyt.foursquare.api.entities.SpecialGroup;
import fi.foyt.foursquare.api.entities.UserGroup;
import fi.foyt.foursquare.api.entities.VenueGroup;
import fi.foyt.foursquare.api.entities.Warning;
import fi.foyt.foursquare.api.entities.notifications.Notification;
import fi.foyt.foursquare.api.io.DefaultIOHandler;
import fi.foyt.foursquare.api.io.IOHandler;
import fi.foyt.foursquare.api.io.Method;
import fi.foyt.foursquare.api.io.Response;

/**
 * Entry point for FoursquareAPI
 */
public class FoursquareApi {

  private static final String DEFAULT_VERSION = "20110525";

  public FoursquareApi(String clientId, String clientSecret, String redirectUrl) {
    this(clientId, clientSecret, redirectUrl, new DefaultIOHandler());
  }

  public FoursquareApi(String clientId, String clientSecret, String redirectUrl, IOHandler ioHandler) {
    this(clientId, clientSecret, redirectUrl, null, ioHandler);
  }

  public FoursquareApi(String clientId, String clientSecret, String redirectUrl, String oAuthToken, IOHandler ioHandler) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.redirectUrl = redirectUrl;
    this.oAuthToken = oAuthToken;
    this.ioHandler = ioHandler;
  }

  /**
   * Returns OAuthToken
   * 
   * @return OAuthToken
   */
  public String getOAuthToken() {
    return oAuthToken;
  }

  /**
   * Sets OAuthToken
   * 
   * @param oAuthToken
   *          OAuthToken
   */
  public void setoAuthToken(String oAuthToken) {
    this.oAuthToken = oAuthToken;
  }

  /**
   * Sets debugging flag what ever parser should disregard non-existing fields
   * 
   * @param skipNonExistingFields
   *          debugging flag what ever parser should disregard non-existing fields
   */
  public void setSkipNonExistingFields(boolean skipNonExistingFields) {
    this.skipNonExistingFields = skipNonExistingFields;
  }

  /**
   * Sets Foursquare API version
   * 
   * @param version
   *          Foursquare API version
   */
  public void setVersion(String version) {
    this.version = version;
  }

  /**
   * Change JSON request mode to callback or normal
   * 
   * @param useCallback
   */
  public void setUseCallback(boolean useCallback) {
    this.useCallback = useCallback;
  }

  /**
   * Returns if JSON request mode is callback
   * 
   * @return if JSON request mode is callback
   */
  public boolean getUseCallback() {
    return useCallback;
  }

  /* Users */

  // TODO: users/leaderboard (https://code.google.com/p/foursquare-api-java/issues/detail?id=26)
  // TODO: users/badges (https://code.google.com/p/foursquare-api-java/issues/detail?id=27)

  public Result<CheckinGroup> usersCheckins(String userId, Integer limit, Integer offset, Long afterTimestamp, Long beforeTimestamp) throws FoursquareApiException {
    try {
      if (userId == null)
        userId = "self";

      ApiRequestResponse response = doApiRequest(Method.GET, "users/" + userId + "/checkins", true, "limit", limit, "offset", offset, "afterTimestamp", afterTimestamp, "beforeTimestamp", beforeTimestamp);
      CheckinGroup result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CheckinGroup) JSONFieldParser.parseEntity(CheckinGroup.class, response.getResponse().getJSONObject("checkins"), this.skipNonExistingFields);
      }

      return new Result<CheckinGroup>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  // TODO: users/tips (https://code.google.com/p/foursquare-api-java/issues/detail?id=29)
  // TODO: users/todos (https://code.google.com/p/foursquare-api-java/issues/detail?id=30)
  // TODO: users/venuehistory (https://code.google.com/p/foursquare-api-java/issues/detail?id=21)

  public Result<CompleteUser> usersRequest(String id) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "users/" + id + "/request", true);
      CompleteUser result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompleteUser) JSONFieldParser.parseEntity(CompleteUser.class, response.getResponse().getJSONObject("user"), this.skipNonExistingFields);
      }

      return new Result<CompleteUser>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  public Result<CompleteUser> usersUnfriend(String id) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "users/" + id + "/unfriend", true);
      CompleteUser result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompleteUser) JSONFieldParser.parseEntity(CompleteUser.class, response.getResponse().getJSONObject("user"), this.skipNonExistingFields);
      }

      return new Result<CompleteUser>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  public Result<CompleteUser> usersApprove(String id) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "users/" + id + "/approve", true);
      CompleteUser result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompleteUser) JSONFieldParser.parseEntity(CompleteUser.class, response.getResponse().getJSONObject("user"), this.skipNonExistingFields);
      }

      return new Result<CompleteUser>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  public Result<CompleteUser> usersDeny(String id) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "users/" + id + "/deny", true);
      CompleteUser result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompleteUser) JSONFieldParser.parseEntity(CompleteUser.class, response.getResponse().getJSONObject("user"), this.skipNonExistingFields);
      }

      return new Result<CompleteUser>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  // TODO: users/setpings (https://code.google.com/p/foursquare-api-java/issues/detail?id=36)

  public Result<CompleteUser> user(String id) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "users/" + id, true);
      CompleteUser result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompleteUser) JSONFieldParser.parseEntity(CompleteUser.class, response.getResponse().getJSONObject("user"), this.skipNonExistingFields);
      }

      return new Result<CompleteUser>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  public Result<CompactUser[]> usersSearch(String phone, String email, String twitter, String twitterSource, String fbid, String name) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "users/search", true, "phone", phone, "email", email, "twitter", twitter, "twitterSource", twitterSource, "fbid", fbid, "name", name);
      CompactUser[] result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompactUser[]) JSONFieldParser.parseEntities(CompactUser.class, response.getResponse().getJSONArray("results"), this.skipNonExistingFields);
      }

      return new Result<CompactUser[]>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  public Result<CompactUser[]> usersRequests() throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "users/requests", true);
      CompactUser[] result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompactUser[]) JSONFieldParser.parseEntities(CompactUser.class, response.getResponse().getJSONArray("requests"), this.skipNonExistingFields);
      }

      return new Result<CompactUser[]>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Returns an array of a user's friends.
   * 
   * @param id
   *          User id (can be 'self' in case of the current user, assumed 'self' if null)
   * @return List of friends
   * @throws FoursquareApiException
   */
  public Result<UserGroup> usersFriends(String id) throws FoursquareApiException {
    try {
      if (id == null) {
        id = "self";
      }

      ApiRequestResponse response = doApiRequest(Method.GET, "users/" + id + "/friends", true);
      UserGroup result = null;

      if (response.getMeta().getCode() == 200) {
        result = (UserGroup) JSONFieldParser.parseEntity(UserGroup.class, response.getResponse().getJSONObject("friends"), this.skipNonExistingFields);
      }

      return new Result<UserGroup>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /* Venues */

  public Result<CompleteVenue> venue(String id) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "venues/" + id, isAuthenticated());
      CompleteVenue result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompleteVenue) JSONFieldParser.parseEntity(CompleteVenue.class, response.getResponse().getJSONObject("venue"), this.skipNonExistingFields);
      }

      return new Result<CompleteVenue>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }
  
  public Result<Recommended> venuesExplore(String ll, Double llAcc, Double alt, Double altAcc, Integer radius, String section, String query, Integer limit, String basis) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "venues/explore", isAuthenticated(), "ll", ll, "llAcc",llAcc, "alt", alt, "altAcc", altAcc, "radius", radius, "section", section, "query", query, "limit", limit, "basis", basis);
      Recommended result = null;

      if (response.getMeta().getCode() == 200) {
        KeywordGroup keywords = (KeywordGroup) JSONFieldParser.parseEntity(KeywordGroup.class, response.getResponse().getJSONObject("keywords"), this.skipNonExistingFields);
        RecommendationGroup[] groups = (RecommendationGroup[]) JSONFieldParser.parseEntities(RecommendationGroup.class, response.getResponse().getJSONArray("groups"), this.skipNonExistingFields);
        Warning warning = response.getResponse().has("warning") ? (Warning) JSONFieldParser.parseEntity(Warning.class, response.getResponse().getJSONObject("warning"), this.skipNonExistingFields) : null;
        result = new Recommended(keywords, groups, warning);
      }

      return new Result<Recommended>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }
  
  public Result<CheckinGroup> venuesHereNow(String id, Integer limit, Integer offset, Long afterTimestamp) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "venues/" + id + "/herenow", isAuthenticated(), "limit", limit, "offset", offset, "afterTimestamp", afterTimestamp);
      CheckinGroup result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CheckinGroup) JSONFieldParser.parseEntity(CheckinGroup.class, response.getResponse().getJSONObject("hereNow"), this.skipNonExistingFields);
      }

      return new Result<CheckinGroup>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }
  
  // TODO: venues/tips (https://code.google.com/p/foursquare-api-java/issues/detail?id=39)
  // TODO: venues/photos (https://code.google.com/p/foursquare-api-java/issues/detail?id=40)
  // TODO: venues/links (https://code.google.com/p/foursquare-api-java/issues/detail?id=41)
  // TODO: venues/marktodo (https://code.google.com/p/foursquare-api-java/issues/detail?id=42)
  // TODO: venues/flag (https://code.google.com/p/foursquare-api-java/issues/detail?id=43)

  public Result<Object> venuesProposeEdit(String id, String name, String address, String crossStreet, String city, String state, String zip, String phone, String ll, String primaryCategoryId) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "venues/" + id + "/proposeedit", true, "name", name, "address", address, "crossStreet", crossStreet, "city", city, "state", state, "zip", zip, "phone", phone, "ll", ll, "primaryCategoryId", primaryCategoryId);
      return new Result<Object>(response.getMeta(), null);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  public Result<CompleteVenue> venuesAdd(String name, String address, String crossStreet, String city, String state, String zip, String phone, String ll, String primaryCategoryId) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "venues/add", true, "name", name, "address", address, "crossStreet", crossStreet, "city", city, "state", state, "zip", zip, "phone", phone, "ll", ll, "primaryCategoryId", primaryCategoryId);
      CompleteVenue result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompleteVenue) JSONFieldParser.parseEntity(CompleteVenue.class, response.getResponse().getJSONObject("venue"), this.skipNonExistingFields);
      }

      return new Result<CompleteVenue>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  public Result<Category[]> venuesCategories() throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "venues/categories", isAuthenticated());
      Category[] result = null;

      if (response.getMeta().getCode() == 200) {
        result = (Category[]) JSONFieldParser.parseEntities(Category.class, response.getResponse().getJSONArray("categories"), this.skipNonExistingFields);
      }

      return new Result<Category[]>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  public Result<VenueGroup[]> venuesSearch(String ll, Double llAcc, Double alt, Double altAcc, String query, Integer limit, String intent) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "venues/search", isAuthenticated(), "ll", ll, "llAcc", llAcc, "alt", alt, "altAcc", altAcc, "query", query, "limit", limit, "intent", intent);
      VenueGroup[] result = null;

      if (response.getMeta().getCode() == 200) {
        if (response.getResponse().has("groups"))
          result = (VenueGroup[]) JSONFieldParser.parseEntities(VenueGroup.class, response.getResponse().getJSONArray("groups"), this.skipNonExistingFields);
        else
          result = new VenueGroup[0];
      }

      return new Result<VenueGroup[]>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  public Result<CompactVenue[]> venuesTrending(String ll, Integer limit, Integer radius) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "venues/trending", isAuthenticated(), "ll", ll, "limit", limit, "radius", radius);
      CompactVenue[] result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompactVenue[]) JSONFieldParser.parseEntities(CompactVenue.class, response.getResponse().getJSONArray("venues"), this.skipNonExistingFields);
      }

      return new Result<CompactVenue[]>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /* Checkins */

  public Result<Checkin> checkin(String checkinId, String signature) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "checkins/" + checkinId, true, "signature", signature);
      Checkin result = null;

      if (response.getMeta().getCode() == 200) {
        result = (Checkin) JSONFieldParser.parseEntity(Checkin.class, response.getResponse().getJSONObject("checkin"), this.skipNonExistingFields);
      }

      return new Result<Checkin>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  public Result<Checkin> checkinsAdd(String venueId, String venue, String shout, String broadcast, String ll, Double llAcc, Double alt, Double altAcc) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "checkins/add", true, "venueId", venueId, "venue", venue, "shout", shout, "broadcast", broadcast, "ll", ll, "llAcc", llAcc, "alt", alt, "altAcc", altAcc);
      Checkin result = null;
      List<Notification<?>> notifications = null;

      if (response.getMeta().getCode() == 200) {
        result = (Checkin) JSONFieldParser.parseEntity(Checkin.class, response.getResponse().getJSONObject("checkin"), this.skipNonExistingFields);
        notifications = NotificationsParser.parseNotifications(response.getNotifications(), skipNonExistingFields);
      }

      return new Result<Checkin>(response.getMeta(), result, notifications);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  public Result<Checkin[]> checkinsRecent(String ll, Integer limit, Long afterTimestamp) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "checkins/recent", true, "ll", ll, "limit", limit, "afterTimestamp", afterTimestamp);
      Checkin[] result = null;

      if (response.getMeta().getCode() == 200) {
        result = (Checkin[]) JSONFieldParser.parseEntities(Checkin.class, response.getResponse().getJSONArray("recent"), this.skipNonExistingFields);
      }

      return new Result<Checkin[]>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  // TODO: checkins/addcomment (https://code.google.com/p/foursquare-api-java/issues/detail?id=14)
  // TODO: checkins/deletecomment (https://code.google.com/p/foursquare-api-java/issues/detail?id=15)

  /* Tips */

  public Result<CompleteTip> tip(String id) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "tips/" + id, false);
      CompleteTip result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompleteTip) JSONFieldParser.parseEntity(CompleteTip.class, response.getResponse().getJSONObject("tip"), this.skipNonExistingFields);
      }

      return new Result<CompleteTip>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  // TODO: tips/ID (https://code.google.com/p/foursquare-api-java/issues/detail?id=16)
  // TODO: tips/add (https://code.google.com/p/foursquare-api-java/issues/detail?id=17)
  // TODO: tips/search (https://code.google.com/p/foursquare-api-java/issues/detail?id=18)
  // TODO: tips/marktodo (https://code.google.com/p/foursquare-api-java/issues/detail?id=25)
  // TODO: tips/markdone (https://code.google.com/p/foursquare-api-java/issues/detail?id=46)
  // TODO: tips/unmark (https://code.google.com/p/foursquare-api-java/issues/detail?id=47)

  /* Photos */
  
  public Result<Photo> photo(String id) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "photos/" + id, true);
      Photo result = null;

      if (response.getMeta().getCode() == 200) {
        result = (Photo) JSONFieldParser.parseEntity(Photo.class, response.getResponse().getJSONObject("photo"), this.skipNonExistingFields);
      }

      return new Result<Photo>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  // TODO: photos/add (https://code.google.com/p/foursquare-api-java/issues/detail?id=20)

  /* Settings */

  public Result<Setting> settingSet(String settingId, Boolean value) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "settings/" + settingId + "/set", true, "value", value ? 1 : 0);
      Setting result = null;

      if (response.getMeta().getCode() == 200) {
        result = (Setting) JSONFieldParser.parseEntity(Setting.class, response.getResponse().getJSONObject("settings"), this.skipNonExistingFields);
      }

      return new Result<Setting>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  public Result<Setting> settingsAll() throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "settings/all", true);
      Setting result = null;

      if (response.getMeta().getCode() == 200) {
        result = (Setting) JSONFieldParser.parseEntity(Setting.class, response.getResponse().getJSONObject("settings"), this.skipNonExistingFields);
      }

      return new Result<Setting>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /* Specials */

  public Result<CompleteSpecial> special(String id, String venueId) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "specials/" + id, isAuthenticated(), "venueId", venueId);
      CompleteSpecial result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompleteSpecial) JSONFieldParser.parseEntity(CompleteSpecial.class, response.getResponse().getJSONObject("special"), this.skipNonExistingFields);
      }

      return new Result<CompleteSpecial>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  public Result<SpecialGroup> specialsSearch(String ll, Double llAcc, Double alt, Double altAcc, Integer limit) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "specials/search", true, "ll", ll, "llAcc", llAcc, "alt", alt, "altAcc", altAcc, "limit", limit);
      SpecialGroup result = null;

      if (response.getMeta().getCode() == 200) {
        result = (SpecialGroup) JSONFieldParser.parseEntity(SpecialGroup.class, response.getResponse().getJSONObject("specials"), this.skipNonExistingFields);
      }

      return new Result<SpecialGroup>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /* Authentication */

  private boolean isAuthenticated() {
    return oAuthToken != null && !"".equals(oAuthToken);
  }

  public String getAuthenticationUrl() {
    return new StringBuilder("https://foursquare.com/oauth2/authenticate?client_id=").append(this.clientId).append("&response_type=code").append("&redirect_uri=").append(this.redirectUrl).toString();
  }

  public void authenticateCode(String code) throws FoursquareApiException {
    StringBuilder urlBuilder = new StringBuilder("https://foursquare.com/oauth2/access_token?client_id=").append(this.clientId).append("&client_secret=").append(this.clientSecret).append("&grant_type=authorization_code").append("&redirect_uri=").append(this.redirectUrl).append("&code=")
        .append(code);

    try {
      Response response = ioHandler.fetchData(urlBuilder.toString(), Method.GET);
      if (response.getResponseCode() == 200) {
        JSONObject responseObject = new JSONObject(response.getResponseContent());
        oAuthToken = responseObject.getString("access_token");
      } else {
        throw new IOException(response.getMessage());
      }
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    } catch (IOException e) {
      throw new FoursquareApiException(e);
    }
  }

  /* io */

  public IOHandler getIOHandler() {
    return ioHandler;
  }

  private ApiRequestResponse doApiRequest(Method method, String path, boolean auth, Object... params) throws JSONException, FoursquareApiException {
    StringBuilder urlBuilder = new StringBuilder(apiUrl);
    urlBuilder.append(path);
    urlBuilder.append('?');

    if (params.length > 0) {
      int paramIndex = 0;
      try {
        while (paramIndex < params.length) {
          Object value = params[paramIndex + 1];
          if (value != null) {
            urlBuilder.append(params[paramIndex]);
            urlBuilder.append('=');
            urlBuilder.append(URLEncoder.encode(value.toString(), "UTF-8"));
            urlBuilder.append('&');
          }

          paramIndex += 2;
        }
      } catch (UnsupportedEncodingException e) {
        throw new FoursquareApiException(e);
      }
    }

    if (auth) {
      urlBuilder.append("oauth_token=");
      urlBuilder.append(getOAuthToken());
    } else {
      urlBuilder.append("client_id=");
      urlBuilder.append(clientId);
      urlBuilder.append("&client_secret=");
      urlBuilder.append(clientSecret);
    }

    urlBuilder.append("&v=" + version);

    if (useCallback) {
      urlBuilder.append("&callback=c");
    }

    if (useCallback) {
      Response response = ioHandler.fetchData(urlBuilder.toString(), method);
      if (response.getResponseCode() == 200) {
        String responseContent = response.getResponseContent();
        String callbackPrefix = "c(";
        String callbackPostfix = ");";
        JSONObject responseObject = new JSONObject(responseContent.substring(callbackPrefix.length(), responseContent.length() - callbackPostfix.length()));

        JSONObject metaObject = responseObject.getJSONObject("meta");
        int code = metaObject.getInt("code");
        String errorType = metaObject.optString("errorType");
        String errorDetail = metaObject.optString("errorDetail");

        JSONObject responseJson = responseObject.getJSONObject("response");
        JSONArray notificationsJson = responseObject.optJSONArray("notifications");

        return new ApiRequestResponse(new ResultMeta(code, errorType, errorDetail), responseJson, notificationsJson);
      } else {
        return new ApiRequestResponse(new ResultMeta(response.getResponseCode(), "", response.getMessage()), null, null);
      }
    } else {
      JSONObject responseJson = null;
      JSONArray notificationsJson = null;
      String errorDetail = null;

      Response response = ioHandler.fetchData(urlBuilder.toString(), method);
      if (response.getResponseCode() == 200) {
        JSONObject responseObject = new JSONObject(response.getResponseContent());
        responseJson = responseObject.getJSONObject("response");
        notificationsJson = responseObject.optJSONArray("notifications");
      } else {
        errorDetail = response.getMessage();
      }

      return new ApiRequestResponse(new ResultMeta(response.getResponseCode(), "", errorDetail), responseJson, notificationsJson);
    }
  }

  private boolean skipNonExistingFields = true;
  private String clientId;
  private String clientSecret;
  private String redirectUrl;
  private String oAuthToken;
  private IOHandler ioHandler;
  private String version = DEFAULT_VERSION;
  private boolean useCallback = true;
  private static final String apiUrl = "https://api.foursquare.com/v2/";

  private class ApiRequestResponse {

    public ApiRequestResponse(ResultMeta meta, JSONObject response, JSONArray notifications) throws JSONException {
      this.meta = meta;
      this.response = response;
      this.notifications = notifications;
    }

    public JSONObject getResponse() {
      return response;
    }

    public JSONArray getNotifications() {
      return notifications;
    }

    public ResultMeta getMeta() {
      return meta;
    }

    private JSONObject response;
    private JSONArray notifications;
    private ResultMeta meta;
  }
}

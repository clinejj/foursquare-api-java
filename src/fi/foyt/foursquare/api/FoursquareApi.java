/*
 * FoursquareAPI - Foursquare API for Java
 * Copyright (C) 2008 - 2011 Antti Leppä / Foyt
 * http://www.foyt.fi
 * 
 * License: 
 * 
 * Licensed under GNU Lesser General Public License Version 2.1 or later (the "LGPL") 
 * http://www.gnu.org/licenses/lgpl.html
 */
package fi.foyt.foursquare.api;

import fi.foyt.foursquare.api.entities.*;
import fi.foyt.foursquare.api.io.DefaultIOHandler;
import fi.foyt.foursquare.api.io.IOHandler;
import fi.foyt.foursquare.api.io.Method;
import fi.foyt.foursquare.api.io.Response;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

/**
 * Entry point for FoursquareAPI
 */
public class FoursquareApi {

  public FoursquareApi(String clientId, String clientSecret, String redirectUrl) {
    this(clientId, clientSecret, redirectUrl, new DefaultIOHandler());
  }

  public FoursquareApi(String clientId, String clientSecret, String redirectUrl, IOHandler ioHandler) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
    this.redirectUrl = redirectUrl;
    this.ioHandler = ioHandler;
  }

  private String getOAuthToken() {
    return oAuthToken;
  }

  public void setSkipNonExistingFields(boolean skipNonExistingFields) {
    this.skipNonExistingFields = skipNonExistingFields;
  }

  /* Users */

  public CompleteUser user(String id) throws FoursquareApiException {
    try {
      JSONObject response = doApiRequest(Method.GET, "users/" + id, true);
      return (CompleteUser) JSONFieldParser.parseEntity(CompleteUser.class, response.getJSONObject("user"), this.skipNonExistingFields);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  public CompactUser[] usersSearch(String phone, String email, String twitter, String twitterSource, String fbid, String name) throws FoursquareApiException {
    try {
      JSONObject response = doApiRequest(Method.GET, "users/search", true, "phone", phone, "email", email, "twitter", twitter, "twitterSource", twitterSource,
          "fbid", fbid, "name", name);
      return (CompactUser[]) JSONFieldParser.parseEntities(CompactUser.class, response.getJSONArray("users"), this.skipNonExistingFields);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  public CompactUser[] usersRequests() throws FoursquareApiException {
    try {
      JSONObject response = doApiRequest(Method.GET, "users/requests", true);
      return (CompactUser[]) JSONFieldParser.parseEntities(CompactUser.class, response.getJSONArray("users"), this.skipNonExistingFields);
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
  public CompactUser[] usersFriends(String id) throws FoursquareApiException {
    try {
      if (id == null) {
        id = "self";
      }

      JSONObject response = doApiRequest(Method.GET, "users/" + id + "/friends", true);
      return (CompactUser[]) JSONFieldParser.parseEntities(CompactUser.class, response.getJSONArray("friends"), this.skipNonExistingFields);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /* Venues */

  public CompleteVenue venue(String id) throws FoursquareApiException {
    try {
      JSONObject response = doApiRequest(Method.GET, "venues/" + id, isAuthenticated());
      return (CompleteVenue) JSONFieldParser.parseEntity(CompleteVenue.class, response.getJSONObject("venue"), this.skipNonExistingFields);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  public CompleteVenue venuesAdd(String name, String address, String crossStreet, String city, String state, String zip, String phone, String ll,
      String primaryCategoryId) throws FoursquareApiException {
    try {
      JSONObject response = doApiRequest(Method.POST, "venues/add", true, "name", name, "address", address, "crossStreet", crossStreet, "city", city, "state",
          state, "zip", zip, "phone", phone, "ll", ll, "primaryCategoryId", primaryCategoryId);
      return (CompleteVenue) JSONFieldParser.parseEntity(CompactVenue.class, response.getJSONObject("venue"), this.skipNonExistingFields);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  public Category[] venuesCategories() throws FoursquareApiException {
    try {
      JSONObject response = doApiRequest(Method.GET, "venues/categories", isAuthenticated());
      return (Category[]) JSONFieldParser.parseEntities(Category.class, response.getJSONArray("categories"), this.skipNonExistingFields);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  public VenueGroup[] venuesSearch(String ll, Double llAcc, Double alt, Double altAcc, String query, Integer limit, String intent)
      throws FoursquareApiException {
    try {
      JSONObject response = doApiRequest(Method.GET, "venues/search", isAuthenticated(), "ll", ll, "llAcc", llAcc, "alt", alt, "altAcc", altAcc, "query",
          query, "limit", limit, "intent", intent);
      return (VenueGroup[]) JSONFieldParser.parseEntities(VenueGroup.class, response.getJSONArray("groups"), this.skipNonExistingFields);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  public CompactVenue[] venuesTrending(String ll, Integer limit, Integer radius) throws FoursquareApiException {
    try {
      JSONObject response = doApiRequest(Method.GET, "venues/trending", isAuthenticated(), "ll", ll, "limit", limit, "radius", radius);
      return (CompactVenue[]) JSONFieldParser.parseEntities(CompactVenue.class, response.getJSONArray("venues"), this.skipNonExistingFields);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /* Checkins */

  // TODO: checkin/ID

  public Checkin checkinsAdd(String venueId, String venue, String shout, String broadcast, String ll, Double llAcc, Double alt, Double altAcc)
      throws FoursquareApiException {
    try {
      JSONObject response = doApiRequest(Method.POST, "checkins/add", true, "venueId", venueId, "venue", venue, "shout", shout, "broadcast", broadcast, "ll",
          ll, "llAcc", llAcc, "alt", alt, "altAcc", altAcc);
      return (Checkin) JSONFieldParser.parseEntity(Checkin.class, response.getJSONObject("checkin"), this.skipNonExistingFields);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  // TODO: checkins/recent
  // TODO: checkins/addcomment
  // TODO: checkins/deletecomment

  /* Tips */

  // TODO: tips/ID
  // TODO: tips/add
  // TODO: tips/search

  /* Photos */

  // TODO: photos/ID
  // TODO: photos/add

  /* Settings */

  // TODO: settings/all
  // TODO: settings/set

  /* Specials */

  // TODO: specials/ID
  // TODO: specials/search

  /* Authentication */

  private boolean isAuthenticated() {
    return oAuthToken != null && !"".equals(oAuthToken);
  }

  public String getAuthenticationUrl() {
    return new StringBuilder("https://foursquare.com/oauth2/authenticate?client_id=")
      .append(this.clientId)
      .append("&response_type=code")
      .append("&redirect_uri=")
      .append(this.redirectUrl).toString();
  }

  public void authenticateCode(String code) throws FoursquareApiException {
    StringBuilder urlBuilder = new StringBuilder("https://foursquare.com/oauth2/access_token?client_id=")
      .append(this.clientId)
      .append("&client_secret=")
      .append(this.clientSecret)
      .append("&grant_type=authorization_code")
      .append("&redirect_uri=")
      .append(this.redirectUrl)
      .append("&code=")
      .append(code);

    try {
      JSONObject response = doJsonRequest(Method.GET, urlBuilder.toString());
      oAuthToken = response.getString("access_token");
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    } catch (IOException e) {
      throw new FoursquareApiException(e);
    }
  }

  /* io */

  private JSONObject doApiRequest(Method method, String path, boolean auth, Object... params) throws JSONException, FoursquareApiException {
    StringBuilder urlBuilder = new StringBuilder(apiUrl);
    urlBuilder.append(path);
    urlBuilder.append('?');

    if (params.length > 0) {
      int paramIndex = 0;
      while (paramIndex < params.length) {
        Object value = params[paramIndex + 1];
        if (value != null) {
          urlBuilder.append(params[paramIndex]);
          urlBuilder.append('=');
          urlBuilder.append(value);
          urlBuilder.append('&');
        }

        paramIndex += 2;
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

    try {
      return doJsonRequest(method, urlBuilder.toString());
    } catch (IOException e) {
      throw new FoursquareApiException(e);
    }
  }

  private JSONObject doJsonRequest(Method method, String url) throws JSONException, IOException {
    Response response = ioHandler.fetchData(url, method);
    if ((response.getResponseCode() >= 200) && (response.getResponseCode() <= 299)) {
      JSONObject responseObject = new JSONObject(response.getResponseContent());
      return responseObject.getJSONObject("response");
    } else {
      throw new IOException("Request canceled with error code " + response.getResponseCode() + " / " + response.getMessage());
    }
  }

  private boolean skipNonExistingFields = true;
  private String clientId;
  private String clientSecret;
  private String redirectUrl;
  private String oAuthToken;
  private IOHandler ioHandler;
  private static final String apiUrl = "https://api.foursquare.com/v2/";

}

/*
 * FoursquareAPI - Foursquare API for Java
 * Copyright (C) 2008 - 2011 Antti Leppä / Foyt
 * http://www.foyt.fi
 * Copyright (C) 2014 - Blake Dy / Wallaby
 * http://walla.by
 * Copyright (C) 2014 - John Cline
 * https://github.com/clinejj
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fi.foyt.foursquare.api.entities.Badge;
import fi.foyt.foursquare.api.entities.BadgeSets;
import fi.foyt.foursquare.api.entities.Badges;
import fi.foyt.foursquare.api.entities.Category;
import fi.foyt.foursquare.api.entities.Checkin;
import fi.foyt.foursquare.api.entities.CheckinGroup;
import fi.foyt.foursquare.api.entities.Comment;
import fi.foyt.foursquare.api.entities.CompactUser;
import fi.foyt.foursquare.api.entities.CompactVenue;
import fi.foyt.foursquare.api.entities.CompleteSpecial;
import fi.foyt.foursquare.api.entities.CompleteTip;
import fi.foyt.foursquare.api.entities.CompleteUser;
import fi.foyt.foursquare.api.entities.CompleteVenue;
import fi.foyt.foursquare.api.entities.GeoCode;
import fi.foyt.foursquare.api.entities.KeywordGroup;
import fi.foyt.foursquare.api.entities.LeaderboardItemGroup;
import fi.foyt.foursquare.api.entities.LinkGroup;
import fi.foyt.foursquare.api.entities.MiniVenue;
import fi.foyt.foursquare.api.entities.Photo;
import fi.foyt.foursquare.api.entities.PhotoGroup;
import fi.foyt.foursquare.api.entities.RecommendationGroup;
import fi.foyt.foursquare.api.entities.Recommended;
import fi.foyt.foursquare.api.entities.Setting;
import fi.foyt.foursquare.api.entities.SpecialGroup;
import fi.foyt.foursquare.api.entities.TipGroup;
import fi.foyt.foursquare.api.entities.Todo;
import fi.foyt.foursquare.api.entities.TodoGroup;
import fi.foyt.foursquare.api.entities.UserGroup;
import fi.foyt.foursquare.api.entities.VenueGroup;
import fi.foyt.foursquare.api.entities.VenueHistoryGroup;
import fi.foyt.foursquare.api.entities.VenuesAutocompleteResult;
import fi.foyt.foursquare.api.entities.VenuesSearchResult;
import fi.foyt.foursquare.api.entities.Warning;
import fi.foyt.foursquare.api.entities.notifications.Notification;
import fi.foyt.foursquare.api.io.DefaultIOHandler;
import fi.foyt.foursquare.api.io.IOHandler;
import fi.foyt.foursquare.api.io.Method;
import fi.foyt.foursquare.api.io.MultipartParameter;
import fi.foyt.foursquare.api.io.Response;

/**
 * Entry point for FoursquareAPI.
 *
 * @see <a href="https://developer.foursquare.com/docs/index_docs.html" target="_blank">https://developer.foursquare.com/docs/index_docs.html</a>
 */
public class FoursquareApi {

  private static final String DEFAULT_VERSION = "20140131";

  /**
   * CDI eyes only
   * @deprecated CDI eyes only
   */
  public FoursquareApi() {
  }

  /**
   * Constructor.
   *
   * @param clientId Foursquare Client id
   * @param clientSecret Foursquare Client secret
   * @param redirectUrl Foursquare Redirect URL
   */
  public FoursquareApi(String clientId, String clientSecret, String redirectUrl) {
    this(clientId, clientSecret, redirectUrl, new DefaultIOHandler());
  }

  /**
   * Constructor.
   *
   * @param clientId Foursquare Client id
   * @param clientSecret Foursquare Client secret
   * @param redirectUrl Foursquare Redirect URL
   * @param ioHandler IOHandler
   */
  public FoursquareApi(String clientId, String clientSecret, String redirectUrl, IOHandler ioHandler) {
    this(clientId, clientSecret, redirectUrl, null, ioHandler);
  }

  /**
   * Constructor.
   *
   * @param clientId Foursquare Client id
   * @param clientSecret Foursquare Client secret
   * @param redirectUrl Foursquare Redirect URL
   * @param oAuthToken Previously retrieved OAuthToken
   * @param ioHandler IOHandler
   */
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
   * @param useCallback set false to enable normal mode
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

  /**
   * Returns profile information for a given user, including selected badges and mayorships.
   *
   * @see <a href="https://developer.foursquare.com/docs/users/users.html" target="_blank">https://developer.foursquare.com/docs/users/users.html</a>
   *
   * @param userId User id (can be 'self' in case of the current user, assumed 'self' if null)
   * @return CompleteUser entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<CompleteUser> user(String userId) throws FoursquareApiException {
    try {
      if (userId == null) {
        userId = "self";
      }

      ApiRequestResponse response = doApiRequest(Method.GET, "users/" + userId, true);
      CompleteUser result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompleteUser) JSONFieldParser.parseEntity(CompleteUser.class, response.getResponse().getJSONObject("user"), this.skipNonExistingFields);
      }

      return new Result<CompleteUser>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Returns the user's leaderboard.
   *
   * @see <a href="https://developer.foursquare.com/docs/users/leaderboard.html" target="_blank">https://developer.foursquare.com/docs/users/leaderboard.html</a>
   *
   * @param neighbors number of friends' scores to return that are adjacent to user's score
   * @return LeaderboardItemGroup entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<LeaderboardItemGroup> usersLeaderboard(Integer neighbors) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "users/leaderboard", true, "neighbors", neighbors);
      LeaderboardItemGroup result = null;

      if (response.getMeta().getCode() == 200) {
        result = (LeaderboardItemGroup) JSONFieldParser.parseEntity(LeaderboardItemGroup.class, response.getResponse().getJSONObject("leaderboard"), this.skipNonExistingFields);
      }

      return new Result<LeaderboardItemGroup>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Returns badges for a given user.
   *
   * @see <a href="https://developer.foursquare.com/docs/users/badges.html" target="_blank">https://developer.foursquare.com/docs/users/badges.html</a>
   *
   * @param userId User id (can be 'self' in case of the current user, assumed 'self' if null)
   * @return Badges entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<Badges> usersBadges(String userId) throws FoursquareApiException {
    try {
      if (userId == null) {
        userId = "self";
      }

      ApiRequestResponse response = doApiRequest(Method.GET, "users/" + userId + "/badges", true);
      Badges result = null;

      if (response.getMeta().getCode() == 200) {
        BadgeSets sets = (BadgeSets) JSONFieldParser.parseEntity(BadgeSets.class, response.getResponse().getJSONObject("sets"), this.skipNonExistingFields);
        Badge[] badges = (Badge[]) JSONFieldParser.parseEntitiesHash(Badge.class, response.getResponse().getJSONObject("badges"), this.skipNonExistingFields);
        String defaultSetType = response.getResponse().getString("defaultSetType");

        result = new Badges(sets, badges, defaultSetType);
      }

      return new Result<Badges>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Returns a history of checkins for the authenticated user.
   *
   * @see <a href="https://developer.foursquare.com/docs/users/checkins.html" target="_blank">https://developer.foursquare.com/docs/users/checkins.html</a>
   *
   * @param userId User id (For now, only 'self' is supported, 'self' assumed if null)
   * @param limit number of results to return
   * @param offset used to page through results.
   * @param afterTimestamp retrieve the first results to follow these seconds since epoch.
   * @param beforeTimestamp retrieve the first results prior to these seconds since epoch.
   * @return CheckinGroup entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<CheckinGroup> usersCheckins(String userId, Integer limit, Integer offset, Long afterTimestamp, Long beforeTimestamp) throws FoursquareApiException {
    try {
      if (userId == null) {
        userId = "self";
      }

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

  /**
   * Returns tips from a user.
   *
   * @see <a href="https://developer.foursquare.com/docs/users/tips.html" target="_blank">https://developer.foursquare.com/docs/users/tips.html</a>
   *
   * @param userId User id (can be 'self' in case of the current user, assumed 'self' if null)
   * @param sort one of recent, nearby, or popular. Nearby requires ll to be provided.
   * @param ll latitude and longitude of the user's location.
   * @param limit number of results to return, up to 500.
   * @param offset used to page through results.
   * @return TipGroup entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<TipGroup> usersTips(String userId, String sort, String ll, Integer limit, Integer offset) throws FoursquareApiException {
    try {
      if (userId == null) {
        userId = "self";
      }

      ApiRequestResponse response = doApiRequest(Method.GET, "users/" + userId + "/tips", true, "sort", sort, "ll", ll, "limit", limit, "offset", offset);
      TipGroup result = null;

      if (response.getMeta().getCode() == 200) {
        result = (TipGroup) JSONFieldParser.parseEntity(TipGroup.class, response.getResponse().getJSONObject("tips"), this.skipNonExistingFields);
      }

      return new Result<TipGroup>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Returns todos from a user.
   *
   * @see <a href="https://developer.foursquare.com/docs/users/todos.html" target="_blank">https://developer.foursquare.com/docs/users/todos.html</a>
   *
   * @param userId User id (can be 'self' in case of the current user, assumed 'self' if null)
   * @param sort one of recent or popular. Nearby requires ll to be provided.
   * @param ll latitude and longitude of the user's location
   * @return TodoGroup entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<TodoGroup> usersTodos(String userId, String sort, String ll) throws FoursquareApiException {
    try {
      if (userId == null) {
        userId = "self";
      }

      ApiRequestResponse response = doApiRequest(Method.GET, "users/" + userId + "/todos", true, "sort", sort, "ll", ll);
      TodoGroup result = null;

      if (response.getMeta().getCode() == 200) {
        result = (TodoGroup) JSONFieldParser.parseEntity(TodoGroup.class, response.getResponse().getJSONObject("todos"), this.skipNonExistingFields);
      }

      return new Result<TodoGroup>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Returns a list of all venues visited by the specified user, along with how many visits and when they were last there.
   *
   * @see <a href="https://developer.foursquare.com/docs/users/venuehistory.html" target="_blank">https://developer.foursquare.com/docs/users/venuehistory.html</a>
   *
   * @param userId User id (For now, only 'self' is supported, 'self' assumed if null)
   * @param beforeTimestamp seconds since epoch.
   * @param afterTimestamp seconds after epoch.
   * @return VenueHistoryGroup entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<VenueHistoryGroup> usersVenueHistory(String userId, Long beforeTimestamp, Long afterTimestamp) throws FoursquareApiException {
    try {
      if (userId == null) {
        userId = "self";
      }

      ApiRequestResponse response = doApiRequest(Method.GET, "users/" + userId + "/venuehistory", true, "beforeTimestamp", beforeTimestamp, "afterTimestamp", afterTimestamp);
      VenueHistoryGroup result = null;

      if (response.getMeta().getCode() == 200) {
        result = (VenueHistoryGroup) JSONFieldParser.parseEntity(VenueHistoryGroup.class, response.getResponse().getJSONObject("venues"), this.skipNonExistingFields);
      }

      return new Result<VenueHistoryGroup>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Sends a friend request to another user.
   *
   * @see <a href="https://developer.foursquare.com/docs/users/request.html" target="_blank">https://developer.foursquare.com/docs/users/request.html</a>
   *
   * @param id user id to which a request will be sent.
   * @return CompleteUser entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
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

  /**
   * Cancels any relationship between the acting user and the specified user.
   *
   * @see <a href="https://developer.foursquare.com/docs/users/unfriend.html" target="_blank">https://developer.foursquare.com/docs/users/unfriend.html</a>
   *
   * @param userId user id of the user to be unfriended.
   * @return CompleteUser entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<CompleteUser> usersUnfriend(String userId) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "users/" + userId + "/unfriend", true);
      CompleteUser result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompleteUser) JSONFieldParser.parseEntity(CompleteUser.class, response.getResponse().getJSONObject("user"), this.skipNonExistingFields);
      }

      return new Result<CompleteUser>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Approves a pending friend request from another user.
   *
   * @see <a href="https://developer.foursquare.com/docs/users/approve.html" target="_blank">https://developer.foursquare.com/docs/users/approve.html</a>
   *
   * @param userId the user id of a pending friend.
   * @return CompleteUser entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<CompleteUser> usersApprove(String userId) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "users/" + userId + "/approve", true);
      CompleteUser result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompleteUser) JSONFieldParser.parseEntity(CompleteUser.class, response.getResponse().getJSONObject("user"), this.skipNonExistingFields);
      }

      return new Result<CompleteUser>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Denies a pending friend request
   *
   * @see <a href="https://developer.foursquare.com/docs/users/deny.html" target="_blank">https://developer.foursquare.com/docs/users/deny.html</a>
   *
   * @param userId the user id of a pending friend.
   * @return CompleteUser entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<CompleteUser> usersDeny(String userId) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "users/" + userId + "/deny", true);
      CompleteUser result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompleteUser) JSONFieldParser.parseEntity(CompleteUser.class, response.getResponse().getJSONObject("user"), this.skipNonExistingFields);
      }

      return new Result<CompleteUser>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Changes whether the acting user will receive pings (phone notifications) when the specified user checks in.
   *
   * @see <a href="https://developer.foursquare.com/docs/users/setpings.html" target="_blank">https://developer.foursquare.com/docs/users/setpings.html</a>
   *
   * @param userId the user id of a friend.
   * @param value true or false.
   * @return CompleteUser entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<CompleteUser> usersSetPings(String userId, String value) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "users/" + userId + "/setpings", true, "value", value);
      CompleteUser result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompleteUser) JSONFieldParser.parseEntity(CompleteUser.class, response.getResponse().getJSONObject("user"), this.skipNonExistingFields);
      }

      return new Result<CompleteUser>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Find users
   *
   * @see <a href="https://developer.foursquare.com/docs/users/search.html" target="_blank">https://developer.foursquare.com/docs/users/search.html</a>
   *
   * @param phone a comma-delimited list of phone numbers to look for.
   * @param email a comma-delimited list of email addresses to look for.
   * @param twitter a comma-delimited list of Twitter handles to look for.
   * @param twitterSource a single Twitter handle. Results will be friends of this user who use Foursquare.
   * @param fbid a comma-delimited list of Facebook id's to look for.
   * @param name a single string to search for in users' names.
   * @return array of CompactUser entities wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
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

  /**
   * Returns a list of users with whom they have a pending friend requests.
   *
   * @see <a href="https://developer.foursquare.com/docs/users/requests.html" target="_blank">https://developer.foursquare.com/docs/users/requests.html</a>
   *
   * @return array of CompactUser entities wrapped in a Result object
   * @throws FoursquareApiException when something unexpected happens
   */
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
   * Returns user's friends.
   *
   * @see <a href="https://developer.foursquare.com/docs/users/friends.html" target="_blank">https://developer.foursquare.com/docs/users/friends.html</a>
   *
   * @param userId User id (can be 'self' in case of the current user, assumed 'self' if null)
   * @return UserGroup entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<UserGroup> usersFriends(String userId) throws FoursquareApiException {
    try {
      if (userId == null) {
        userId = "self";
      }

      ApiRequestResponse response = doApiRequest(Method.GET, "users/" + userId + "/friends", true);
      UserGroup result = null;

      if (response.getMeta().getCode() == 200) {
        result = (UserGroup) JSONFieldParser.parseEntity(UserGroup.class, response.getResponse().getJSONObject("friends"), this.skipNonExistingFields);
      }

      return new Result<UserGroup>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Gives details about a venue, including location, mayorship, tags, tips, specials, and category.
   *
   * @see <a href="https://developer.foursquare.com/docs/venues/venues.html" target="_blank">https://developer.foursquare.com/docs/venues/venues.html</a>
   *
   * @param venueId id of venue to retrieve
   * @return CompleteVenue entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<CompleteVenue> venue(String venueId) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "venues/" + venueId, isAuthenticated());
      CompleteVenue result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompleteVenue) JSONFieldParser.parseEntity(CompleteVenue.class, response.getResponse().getJSONObject("venue"), this.skipNonExistingFields);
      }

      return new Result<CompleteVenue>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Returns a list of recommended venues near the specified location.
   *
   * @see <a href="https://developer.foursquare.com/docs/venues/explore.html" target="_blank">https://developer.foursquare.com/docs/venues/explore.html</a>
   *
   * @param ll latitude and longitude of the location in question, so response can include distance.
   * @param llAcc accuracy of latitude and longitude, in meters.
   * @param alt altitude of the user's location, in meters.
   * @param altAcc accuracy of the user's altitude, in meters.
   * @param radius radius to search within, in meters.
   * @param section one of food, drinks, coffee, shops, or arts. Choosing one of these limits results to venues with categories matching these terms.
   * @param query a search term to be applied against tips, category, tips, etc. at a venue.
   * @param limit number of results to return, up to 50.
   * @param basis if present and set to friends or me, limits results to only places where friends have visited or user has visited, respectively.
   * @return Recommended entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<Recommended> venuesExplore(String ll, Double llAcc, Double alt, Double altAcc, Integer radius, String section, String query, Integer limit, String basis) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "venues/explore", isAuthenticated(), "ll", ll, "llAcc", llAcc, "alt", alt, "altAcc", altAcc, "radius", radius, "section", section, "query", query, "limit", limit, "basis", basis);
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

  /**
   * Provides a count of how many people are at a given venue. If the request is user authenticated, also returns a list of the users there, friends-first.
   *
   * @see <a href="https://developer.foursquare.com/docs/venues/herenow.html" target="_blank">https://developer.foursquare.com/docs/venues/herenow.html</a>
   *
   * @param venueId id of venue to retrieve
   * @param limit number of results to return, up to 500.
   * @param offset used to page through results.
   * @param afterTimestamp retrieve the first results to follow these seconds since epoch
   * @return CheckinGroup entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<CheckinGroup> venuesHereNow(String venueId, Integer limit, Integer offset, Long afterTimestamp) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "venues/" + venueId + "/herenow", isAuthenticated(), "limit", limit, "offset", offset, "afterTimestamp", afterTimestamp);
      CheckinGroup result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CheckinGroup) JSONFieldParser.parseEntity(CheckinGroup.class, response.getResponse().getJSONObject("hereNow"), this.skipNonExistingFields);
      }

      return new Result<CheckinGroup>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Returns tips for a venue.
   *
   * @see <a href="https://developer.foursquare.com/docs/venues/tips.html" target="_blank">https://developer.foursquare.com/docs/venues/tips.html</a>
   *
   * @param venueId id of venue
   * @param sort one of recent or popular
   * @param limit number of results to return, up to 500.
   * @param offset used to page through results.
   * @return TipGroup entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<TipGroup> venuesTips(String venueId, String sort, Integer limit, Integer offset) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "venues/" + venueId + "/tips", isAuthenticated(), "sort", sort, "limit", limit, "offset", offset);
      TipGroup result = null;

      if (response.getMeta().getCode() == 200) {
        result = (TipGroup) JSONFieldParser.parseEntity(TipGroup.class, response.getResponse().getJSONObject("tips"), this.skipNonExistingFields);
      }

      return new Result<TipGroup>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Returns photos for a venue.
   *
   * @see <a href="https://developer.foursquare.com/docs/venues/photos.html" target="_blank">https://developer.foursquare.com/docs/venues/photos.html</a>
   *
   * @param venueId the venue you want photos for.
   * @param group pass checkin for photos added by friends on their recent checkins. Pass venue for public photos added to the venue by anyone. Use multi to fetch both.
   * @param limit number of results to return, up to 500.
   * @param offset used to page through results.
   * @return PhotoGroup entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<PhotoGroup> venuesPhotos(String venueId, String group, Integer limit, Integer offset) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "venues/" + venueId + "/photos", isAuthenticated(), "group", group, "limit", limit, "offset", offset);
      PhotoGroup result = null;

      if (response.getMeta().getCode() == 200) {
        result = (PhotoGroup) JSONFieldParser.parseEntity(PhotoGroup.class, response.getResponse().getJSONObject("photos"), this.skipNonExistingFields);
      }

      return new Result<PhotoGroup>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Returns URLs or identifiers from third parties that have been applied to this venue
   *
   * @see <a href="https://developer.foursquare.com/docs/venues/links.html" target="_blank">https://developer.foursquare.com/docs/venues/links.html</a>
   *
   * @param id id of the venue
   * @return LinkGroup entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<LinkGroup> venuesLinks(String id) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "venues/" + id + "/links", isAuthenticated());
      LinkGroup result = null;

      if (response.getMeta().getCode() == 200) {
        result = (LinkGroup) JSONFieldParser.parseEntity(LinkGroup.class, response.getResponse().getJSONObject("links"), this.skipNonExistingFields);
      }

      return new Result<LinkGroup>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Allows you to mark a venue to-do, with optional text.
   *
   * @see <a href="https://developer.foursquare.com/docs/venues/marktodo.html" target="_blank">https://developer.foursquare.com/docs/venues/marktodo.html</a>
   *
   * @param venuesId the venue id
   * @param text The text of the tip.
   * @return Todo entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<Todo> venuesMarkTodo(String venuesId, String text) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "venues/" + venuesId + "/marktodo", true, "text", text);
      Todo result = null;

      if (response.getMeta().getCode() == 200) {
        result = (Todo) JSONFieldParser.parseEntity(Todo.class, response.getResponse().getJSONObject("todo"), this.skipNonExistingFields);
      }

      return new Result<Todo>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Allows users to indicate a venue is incorrect in some way.
   *
   * @see <a href="https://developer.foursquare.com/docs/venues/flag.html" target="_blank">https://developer.foursquare.com/docs/venues/flag.html</a>
   *
   * @param id the venue id for which an edit is being proposed.
   * @param problem one of mislocated, closed, duplicate.
   * @param venueId of a duplicate, only valid for problem = duplicate
   * @return Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<Object> venuesFlag(String id, String problem, String venueId) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "venues/" + id + "/flag", true, "problem", problem, "venueId", venueId);
      return new Result<Object>(response.getMeta(), null);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Allows user to propose a change to a venue.
   *
   * @see <a href="https://developer.foursquare.com/docs/venues/proposeedit.html" target="_blank">https://developer.foursquare.com/docs/venues/proposeedit.html</a>
   *
   * @param id the venue id for which an edit is being proposed.
   * @param name the name of the venue.
   * @param address the address of the venue.
   * @param crossStreet the nearest intersecting street or streets.
   * @param city the city name where this venue is.
   * @param state the nearest state or province to the venue.
   * @param zip the nearest state or province to the venue.
   * @param phone the phone number of the venue.
   * @param ll latitude and longitude of the user's location, as accurate as is known.
   * @param primaryCategoryId the ID of the category to which you want to assign this venue.
   * @return Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<Object> venuesProposeEdit(String id, String name, String address, String crossStreet, String city, String state, String zip, String phone, String ll, String primaryCategoryId) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "venues/" + id + "/proposeedit", true, "name", name, "address", address, "crossStreet", crossStreet, "city", city, "state", state, "zip", zip, "phone", phone, "ll", ll, "primaryCategoryId", primaryCategoryId);
      return new Result<Object>(response.getMeta(), null);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Allows you to make changes to a venue (acting user must be a superuser or venue manager). Any blank parameter deletes an old value, any unspecified parameter does nothing.
   *
   * @see <a href="https://developer.foursquare.com/docs/venues/edit.html" target="_blank">https://developer.foursquare.com/docs/venues/edit.html</a>
   *
   * @param id the venue id for which an edit is being proposed.
   * @param name the name of the venue.
   * @param address the address of the venue.
   * @param crossStreet the nearest intersecting street or streets.
   * @param city the city name where this venue is.
   * @param state the nearest state or province to the venue.
   * @param zip the nearest state or province to the venue.
   * @param phone the phone number of the venue.
   * @param ll latitude and longitude of the user's location, as accurate as is known.
   * @param categoryId the ID of the category to which you want to assign this venue.
   * @param twitter The twitter handle of the venue.
   * @param description A freeform description of the venue, up to 300 characters.
   * @param url The url of the homepage of the venue.
   * @return Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<Object> venuesEdit(String id, String name, String address, String crossStreet, String city, String state, String zip, String phone, String ll, String categoryId, String twitter, String description, String url) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "venues/" + id + "/edit", true, "name", name, "address", address, "crossStreet", crossStreet, "city", city, "state", state, "zip", zip, "phone", phone, "ll", ll, "categoryId", categoryId, "twitter", twitter, "description", description, "url", url);
      return new Result<Object>(response.getMeta(), null);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Allows user to add a new venue.
   *
   * @see <a href="https://developer.foursquare.com/docs/venues/add.html" target="_blank">https://developer.foursquare.com/docs/venues/add.html</a>
   *
   * @param name the name of the venue
   * @param address the address of the venue.
   * @param crossStreet the nearest intersecting street or streets.
   * @param city the city name where this venue is.
   * @param state the nearest state or province to the venue.
   * @param zip the zip or postal code for the venue.
   * @param phone the phone number of the venue.
   * @param ll latitude and longitude of the venue, as accurate as is known.
   * @param primaryCategoryId the ID of the category to which you want to assign this venue.
   * @return CompleteVenue entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
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

  /**
   * Returns a hierarchical list of categories applied to venues.
   *
   * @see <a href="https://developer.foursquare.com/docs/venues/categories.html" target="_blank">https://developer.foursquare.com/docs/venues/categories.html</a>
   *
   * @return Array of Category entities wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
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

  /**
   * Returns a list of venues near the current location, optionally matching the search term.
   *
   * @see <a href="https://developer.foursquare.com/docs/venues/search.html" target="_blank">https://developer.foursquare.com/docs/venues/search.html</a>
   *
   * @param ll latitude and longitude of the user's location. (Required for query searches)
   * @param llAcc accuracy of latitude and longitude, in meters. (Does not currently affect search results.)
   * @param alt altitude of the user's location, in meters. (Does not currently affect search results.)
   * @param altAcc accuracy of the user's altitude, in meters. (Does not currently affect search results.)
   * @param query a search term to be applied against titles.
   * @param limit number of results to return, up to 50.
   * @param intent one of checkin, match or specials
   * @param categoryId a category to limit results to
   * @param url a third-party URL
   * @param providerId identifier for a known third party
   * @param linkedId identifier used by third party specifed in providerId parameter
   * @param radius Limit results to venues within this many meters of the specified location. Maximum is 100 000 meters.
   * @param near Required if ll is not provided. A string naming a place in the world. Will be geocodd. (required for query searches)
   * @return VenuesSearchResult object wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<VenuesSearchResult> venuesSearch(String ll, Double llAcc, Double alt, Double altAcc, String query, Integer limit, String intent, String categoryId, String url, String providerId, String linkedId, Integer radius, String near) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "venues/search", isAuthenticated(), "ll", ll, "llAcc", llAcc, "alt", alt, "altAcc", altAcc, "query", query, "limit", limit, "intent", intent, "categoryId", categoryId, "url", url, "providerId", providerId, "linkedId", linkedId, "radius", radius, "near", near);
      VenuesSearchResult result = null;

      if (response.getMeta().getCode() == 200) {
        CompactVenue[] venues = null;
        VenueGroup[] groups = null;

        if (response.getResponse().has("groups")) {
          groups = (VenueGroup[]) JSONFieldParser.parseEntities(VenueGroup.class, response.getResponse().getJSONArray("groups"), this.skipNonExistingFields);
        }

        if (response.getResponse().has("venues")) {
          venues = (CompactVenue[]) JSONFieldParser.parseEntities(CompactVenue.class, response.getResponse().getJSONArray("venues"), this.skipNonExistingFields);
        }

        result = new VenuesSearchResult(venues, groups);
      }

      return new Result<VenuesSearchResult>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }


  /**
   * handle parsing a venue search result and parsing the data
   * @param response
   * @return
   */
  private Result<VenuesSearchResult> handleVenueSearchResult(ApiRequestResponse response) throws FoursquareApiException, JSONException {
	  VenuesSearchResult result = null;

      if (response.getMeta().getCode() == 200) {
        CompactVenue[] venues = null;
        VenueGroup[] groups = null;
        GeoCode geocode = null;
        if (response.getResponse().has("groups")) {
          groups = (VenueGroup[]) JSONFieldParser.parseEntities(VenueGroup.class, response.getResponse().getJSONArray("groups"), this.skipNonExistingFields);
        }

        if (response.getResponse().has("venues")) {
          venues = (CompactVenue[]) JSONFieldParser.parseEntities(CompactVenue.class, response.getResponse().getJSONArray("venues"), this.skipNonExistingFields);
        }

        if(response.getResponse().has("geocode")) {
        	geocode = (GeoCode) JSONFieldParser.parseEntity(GeoCode.class, response.getResponse().getJSONObject("geocode"), this.skipNonExistingFields);
        }
        result = new VenuesSearchResult(venues, groups,geocode);
      }

      return new Result<VenuesSearchResult>(response.getMeta(), result);
  }

  /**
   * Generic search which takes a map of parameters
   * The map is converted into parameters for the search API call with key/value pairs matching
   * https://developer.foursquare.com/docs/venues/search
   *
   * For example:
   *
   * public Response foursquareSearchNamed(@QueryParam("place") String place, @QueryParam("term") String searchTerm) {
   *		Map<String,String> searchParams = new HashMap<String,String>();
   *		FoursquareApi foursquareApi = new FoursquareApi(<your client_id>, <your client_secret>, <your redirecturl>);
   *		searchParams.put("near", place);
	*		searchParams.put("query", searchTerm);
	*		searchParams.put("limit","50");
	*		try {
	*			Result<VenuesSearchResult> result = foursquareApi.venuesSearch(searchParams);
	*			if(result != null) {
	*				return Response.ok(result.getResult()).build();
	*			}
	*		} catch (Exception e) {
	*			e.printStackTrace();
	*			log.warning("Problem with foursquare search");
	*		}
	*		return Response.noContent().build();
	*	}
   *
   */
  public Result<VenuesSearchResult> venuesSearch(Map<String,String> params) throws FoursquareApiException {
	  List<String> argsList = new ArrayList<String>();
	  for(String s : params.keySet()) {
		  argsList.add(s);
		  argsList.add(params.get(s));
	  }

	  Object[] args = argsList.toArray();
	  try {
	      ApiRequestResponse response = doApiRequest(Method.GET, "venues/search", isAuthenticated(), args);
	      return handleVenueSearchResult(response);
	    } catch (JSONException e) {
	      throw new FoursquareApiException(e);
	    }
  }

  /**
   * Returns a list of venues near the current location identified by place (i.e. Chicago, IL, optionally matching the search term.
   *
   * @see <a href="https://developer.foursquare.com/docs/venues/search.html" target="_blank">https://developer.foursquare.com/docs/venues/search.html</a>
   *
   * @param near the name of a city or town which can be geocoded by foursquare
   * @param query a search term to be applied against titles.
   * @param limit number of results to return, up to 50.
   * @param intent one of checkin, match or specials
   * @param categoryId a category to limit results to
   * @param url a third-party URL
   * @param providerId identifier for a known third party
   * @param linkedId identifier used by third party specifed in providerId parameter
   * @return VenuesSearchResult object wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<VenuesSearchResult> venuesSearch(String near, String query, Integer limit, String intent, String categoryId, String url, String providerId, String linkedId) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "venues/search", isAuthenticated(), "near", near, "query", query, "limit", limit, "intent", intent, "categoryId", categoryId, "url", url, "providerId", providerId, "linkedId", linkedId);
      return handleVenueSearchResult(response);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }


  /**
   * Venues Autocomplete
   * https://developer.foursquare.com/docs/venues/suggestcompletion
   * @param ll
   * @param llAcc
   * @param alt
   * @param altAcc
   * @param query
   * @param limit
   * @return Result<VenuesAutocompleteResult>  -- this is only minivenues as per the API!
   *
   */
  public Result<VenuesAutocompleteResult> venuesSuggestCompletion(String ll, Double llAcc, Double alt, Double altAcc, String query, int limit) throws FoursquareApiException {
	  try {
	      ApiRequestResponse response = doApiRequest(Method.GET, "venues/suggestcompletion", isAuthenticated(), "ll", ll, "llAcc", llAcc, "alt", alt, "altAcc", altAcc, "query", query, "limit", limit);
	      VenuesAutocompleteResult result = null;

	      if (response.getMeta().getCode() == 200) {
	        MiniVenue[] venues = null;

	        if (response.getResponse().has("minivenues")) {
	          venues = (MiniVenue[]) JSONFieldParser.parseEntities(MiniVenue.class, response.getResponse().getJSONArray("minivenues"), this.skipNonExistingFields);
	        }

	        result = new VenuesAutocompleteResult(venues);
	      }

	      return new Result<VenuesAutocompleteResult>(response.getMeta(), result);
	    } catch (JSONException e) {
	      throw new FoursquareApiException(e);
	    }
  }

  /**
   * Returns a list of venues near the current location with the most people currently checked in.
   *
   * @see <a href="https://developer.foursquare.com/docs/venues/trending.html" target="_blank">https://developer.foursquare.com/docs/venues/trending.html</a>
   *
   * @param ll latitude and longitude of the user's location.
   * @param limit number of results to return, up to 50.
   * @param radius radius in meters, up to approximately 2000 meters.
   * @return Array of CompactVenue entities wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
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

  /**
   * Get details of a checkin.
   *
   * @see <a href="https://developer.foursquare.com/docs/checkins/checkins.html" target="_blank">https://developer.foursquare.com/docs/checkins/checkins.html</a>
   *
   * @param checkinId the ID of the checkin to retrieve additional information for.
   * @param signature when checkins are sent to public feeds such as Twitter, Foursquare appends a signature (s=XXXXXX) allowing users to bypass the friends-only access check on checkins. The same value can be used here for programmatic access to otherwise inaccessible checkins.
   * @return Checkin entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
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

  /**
   * Allows you to check in to a place.
   *
   * @see <a href="https://developer.foursquare.com/docs/checkins/add.html" target="_blank">https://developer.foursquare.com/docs/checkins/add.html</a>
   *
   * @param venueId the venue where the user is checking in. No venueid is needed if shouting or just providing a venue name. Find venue IDs by searching or from historical APIs.
   * @param venue if are not shouting, but you don't have a venue ID or would rather prefer a 'venueless' checkin, pass the venue name as a string using this parameter
   * @param shout a message about your check-in. The maximum length of this field is 140 characters.
   * @param broadcast how much to broadcast this check-in, ranging from private (off-the-grid) to public,facebook,twitter. Can also be just public or public,facebook, for example. If no valid value is found, the default is public. Shouts cannot be private.
   * @param ll latitude and longitude of the user's location.
   * @param llAcc accuracy of the user's latitude and longitude, in meters.
   * @param alt altitude of the user's location, in meters.
   * @param altAcc vertical accuracy of the user's location, in meters.
   * @return Checkin entity wrapped in Result object. Result also contains list of Notifications related to this checkin.
   * @throws FoursquareApiException when something unexpected happens
   */
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

  /**
   * Returns a list of recent checkins from friends.
   *
   * @see <a href="https://developer.foursquare.com/docs/checkins/recent.html" target="_blank">https://developer.foursquare.com/docs/checkins/recent.html</a>
   *
   * @param ll latitude and longitude of the user's location, so response can include distance.
   * @param limit number of results to return, up to 100.
   * @param afterTimestamp seconds after which to look for checkins
   * @return Array of Checkin entities wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
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

  /**
   * Comment on a checkin-in
   *
   * @see <a href="https://developer.foursquare.com/docs/checkins/addcomment.html" target="_blank">https://developer.foursquare.com/docs/checkins/addcomment.html</a>
   *
   * @param checkinId the ID of the checkin to add a comment to
   * @param text the text of the comment, up to 200 characters.
   * @return Comment entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<Comment> checkinsAddComment(String checkinId, String text) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "checkins/" + checkinId + "/addcomment", true, "text", text);
      Comment result = null;

      if (response.getMeta().getCode() == 200) {
        result = (Comment) JSONFieldParser.parseEntity(Comment.class, response.getResponse().getJSONObject("comment"), this.skipNonExistingFields);
      }

      return new Result<Comment>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Remove a comment from a checkin, if the acting user is the author or the owner of the checkin.
   *
   * @see <a href="https://developer.foursquare.com/docs/checkins/deletecomment.html" target="_blank">https://developer.foursquare.com/docs/checkins/deletecomment.html</a>
   *
   * @param checkinId the ID of the checkin to remove a comment from.
   * @param commentId the id of the comment to remove.
   * @return Checkin entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<Checkin> checkinsDeleteComment(String checkinId, String commentId) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "checkins/" + checkinId + "/deletecomment", true, "commentId", commentId);
      Checkin result = null;

      if (response.getMeta().getCode() == 200) {
        result = (Checkin) JSONFieldParser.parseEntity(Checkin.class, response.getResponse().getJSONObject("checkin"), this.skipNonExistingFields);
      }

      return new Result<Checkin>(response.getMeta(), result, null);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Gives details about a tip, including which users (especially friends) have marked the tip to-do or done.
   *
   * @see <a href="https://developer.foursquare.com/docs/tips/tips.html" target="_blank">https://developer.foursquare.com/docs/tips/tips.html</a>
   *
   * @param id id of tip to retrieve
   * @return CompleteTip entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
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

  /**
   * Allows user to add a new tip at a venue.
   *
   * @see <a href="https://developer.foursquare.com/docs/tips/add.html" target="_blank">https://developer.foursquare.com/docs/tips/add.html</a>
   *
   * @param venueId the venue where you want to add this tip.
   * @param text the text of the tip.
   * @param url a URL related to this tip.
   * @return CompleteTip entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<CompleteTip> tipsAdd(String venueId, String text, String url) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "tips/add", true, "venueId", venueId, "text", text, "url", url);
      CompleteTip result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompleteTip) JSONFieldParser.parseEntity(CompleteTip.class, response.getResponse().getJSONObject("tip"), this.skipNonExistingFields);
      }

      return new Result<CompleteTip>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Returns a list of tips near the area specified.
   *
   * @see <a href="https://developer.foursquare.com/docs/tips/search.html" target="_blank">https://developer.foursquare.com/docs/tips/search.html</a>
   *
   * @param ll latitude and longitude of the user's location.
   * @param limit number of results to return, up to 500.
   * @param offset used to page through results.
   * @param filter if set to friends, only show nearby tips from friends.
   * @param query only find tips matching the given term, cannot be used in conjunction with friends filter.
   * @return Array of CompleteTip entities wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<CompleteTip[]> tipsSearch(String ll, Integer limit, Integer offset, String filter, String query) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.GET, "tips/search", isAuthenticated(), "ll", ll, "limit", limit, "offset", offset, "filter", filter, "query", query);
      CompleteTip[] result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompleteTip[]) JSONFieldParser.parseEntities(CompleteTip.class, response.getResponse().getJSONArray("tips"), this.skipNonExistingFields);
      }

      return new Result<CompleteTip[]>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Allows you to mark a tip to-do.
   *
   * @see <a href="https://developer.foursquare.com/docs/tips/marktodo.html" target="_blank">https://developer.foursquare.com/docs/tips/marktodo.html</a>
   *
   * @param tipId the tip you want to mark to-do.
   * @return Todo entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<Todo> tipsMarkTodo(String tipId) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "tips/" + tipId + "/marktodo", true);
      Todo result = null;

      if (response.getMeta().getCode() == 200) {
        result = (Todo) JSONFieldParser.parseEntity(Todo.class, response.getResponse().getJSONObject("todo"), this.skipNonExistingFields);
      }

      return new Result<Todo>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Allows the acting user to mark a tip done.
   *
   * @see <a href="https://developer.foursquare.com/docs/tips/markdone.html" target="_blank">https://developer.foursquare.com/docs/tips/markdone.html</a>
   *
   * @param tipId the tip you want to mark done
   * @return CompleteTip entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<CompleteTip> tipsMarkDone(String tipId) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "tips/" + tipId + "/markdone", true);
      CompleteTip result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompleteTip) JSONFieldParser.parseEntity(CompleteTip.class, response.getResponse().getJSONObject("tip"), this.skipNonExistingFields);
      }

      return new Result<CompleteTip>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Allows user to remove a tip from your to-do list or done list.
   *
   * @see <a href="https://developer.foursquare.com/docs/tips/unmark.html" target="_blank">https://developer.foursquare.com/docs/tips/unmark.html</a>
   *
   * @param tipId the tip you want to unmark.
   * @return CompleteTip entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<CompleteTip> tipsUnmark(String tipId) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiRequest(Method.POST, "tips/" + tipId + "/unmark", true);
      CompleteTip result = null;

      if (response.getMeta().getCode() == 200) {
        result = (CompleteTip) JSONFieldParser.parseEntity(CompleteTip.class, response.getResponse().getJSONObject("tip"), this.skipNonExistingFields);
      }

      return new Result<CompleteTip>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Get details of a photo.
   *
   * @see <a href="https://developer.foursquare.com/docs/photos/photos.html" target="_blank">https://developer.foursquare.com/docs/photos/photos.html</a>
   *
   * @param id the id of the photo to retrieve additional information for.
   * @return Photo entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
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

  /**
   * Allows user to add a new photo to a checkin, tip, or a venue in general.
   *
   * @see <a href="https://developer.foursquare.com/docs/photos/add.html" target="_blank">https://developer.foursquare.com/docs/photos/add.html</a>
   *
   * @param checkinId the id of a checkin owned by the user
   * @param tipId the id of a tip owned by the user
   * @param venueId the id of a venue, provided only when adding a public photo of the venue
   * @param broadcast twitter, facebook or twitter,facebook if you want to send to both.
   * @param ll latitude and longitude of the user's location.
   * @param llAcc accuracy of the user's latitude and longitude, in meters.
   * @param alt altitude of the user's location, in meters.
   * @param altAcc vertical accuracy of the user's location, in meters.
   * @param data data of the image. Image should be "image/jpeg"
   * @return Photo entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
  public Result<Photo> photosAdd(String checkinId, String tipId, String venueId, String broadcast, String ll, Double llAcc, Double alt, Double altAcc, byte[] data) throws FoursquareApiException {
    try {
      ApiRequestResponse response = doApiMultipartMimeRequest("photos/add", true, "checkinId", checkinId, "tipId", tipId, "venueId", venueId, "broadcast", broadcast, "ll", ll, "llAcc", llAcc, "alt", alt, "altAcc", altAcc, new MultipartParameter("photo", "image/jpeg", data));
      Photo result = null;

      if (response.getMeta().getCode() == 200) {
        result = (Photo) JSONFieldParser.parseEntity(Photo.class, response.getResponse().getJSONObject("photo"), this.skipNonExistingFields);
      }

      return new Result<Photo>(response.getMeta(), result);
    } catch (JSONException e) {
      throw new FoursquareApiException(e);
    }
  }

  /**
   * Change a setting for the given user.
   *
   * @see <a href="https://developer.foursquare.com/docs/settings/set.html" target="_blank">https://developer.foursquare.com/docs/settings/set.html</a>
   *
   * @param settingId one of sendToTwitter, sendMayorshipsToTwitter, sendBadgesToTwitter, sendToFacebook, sendMayorshipsToFacebook, sendBadgesToFacebook, receivePings, receiveCommentPings.
   * @param value true or false
   * @return Setting entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
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

  /**
   * Returns the settings of the acting user.
   *
   * @see <a href="https://developer.foursquare.com/docs/settings/all.html" target="_blank">https://developer.foursquare.com/docs/settings/all.html</a>
   *
   * @return Setting entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
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

  /**
   * Gives details about a special, including text and whether it is unlocked for the current user.
   *
   * @see <a href="https://developer.foursquare.com/docs/specials/specials.html" target="_blank">https://developer.foursquare.com/docs/specials/specials.html</a>
   *
   * @param id id of special to retrieve
   * @param venueId id of a venue the special is running at
   * @return CompleteSpecial entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
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

  /**
   * Returns a list of specials near the current location.
   *
   * @see <a href="https://developer.foursquare.com/docs/specials/search.html" target="_blank">https://developer.foursquare.com/docs/specials/search.html</a>
   *
   * @param ll latitude and longitude to search near.
   * @param llAcc accuracy of latitude and longitude, in meters.
   * @param alt altitude of the user's location, in meters.
   * @param altAcc accuracy of the user's altitude, in meters.
   * @param limit number of results to return, up to 50
   * @return SpecialGroup entity wrapped in Result object
   * @throws FoursquareApiException when something unexpected happens
   */
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

  /**
   * Returns true or false whether oAuthToken has been sat or not
   *
   * @return true or false whether oAuthToken has been sat or not
   */
  private boolean isAuthenticated() {
    return oAuthToken != null && !"".equals(oAuthToken);
  }

  /**
   * Returns user authentication URL
   *
   * @return user authentication URL
   */
  public String getAuthenticationUrl() {
    return new StringBuilder("https://foursquare.com/oauth2/authenticate?client_id=").append(this.clientId).append("&response_type=code").append("&redirect_uri=").append(this.redirectUrl).toString();
  }

  /**
   * Exchanges code for oAuthToken
   *
   * @param code code
   * @throws FoursquareApiException when something unexpected happens
   */
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

  /**
   * Returns current IOHandler
   *
   * @return current IOHandler
   */
  public IOHandler getIOHandler() {
    return ioHandler;
  }

  /**
   * Multipart/mime API request
   *
   * @param path API endpoint
   * @param auth whether request should send oAuthToken or not
   * @param params request parameters. URL parameters should be added in parameter name, parameter value pairs and multipart parameters should be added as instances of MultipartParameters
   * @return response
   * @throws JSONException when JSON parsing error occurs
   * @throws FoursquareApiException when something unexpected happens
   */
  private ApiRequestResponse doApiMultipartMimeRequest(String path, boolean auth, Object... params) throws JSONException, FoursquareApiException {
    List<Object> parameters = new ArrayList<Object>();
    List<MultipartParameter> multipartParameters = new ArrayList<MultipartParameter>();

    for (Object param : params) {
      if (param instanceof MultipartParameter) {
        multipartParameters.add((MultipartParameter) param);
      } else {
        parameters.add(param);
      }
    }

    String url = getApiRequestUrl(path, auth, parameters.toArray());
    Response response = ioHandler.fetchDataMultipartMime(url, multipartParameters.toArray(new MultipartParameter[0]));

    if (useCallback) {
      return handleCallbackApiResponse(response);
    } else {
      return handleApiResponse(response);
    }
  }

  /**
   * API Request
   *
   * @param method method used in request
   * @param path API endpoint
   * @param auth whether request should send oAuthToken or not
   * @param params request parameters. Parameters should be added in parameter name, parameter value pairs
   * @return response
   * @throws JSONException when JSON parsing error occurs
   * @throws FoursquareApiException when something unexpected happens
   */
  private ApiRequestResponse doApiRequest(Method method, String path, boolean auth, Object... params) throws JSONException, FoursquareApiException {
    String url = getApiRequestUrl(path, auth, params);
    Response response = ioHandler.fetchData(url, method);

    if (useCallback) {
      return handleCallbackApiResponse(response);
    } else {
      return handleApiResponse(response);
    }
  }

  /**
   * Builds request URL
   *
   * @param path API endpoint
   * @param auth whether add oAuthToken parameter or not
   * @param params request parameters. Parameters should be added in parameter name, parameter value pairs
   * @return URL
   * @throws FoursquareApiException when something unexpected happens
   */
  private String getApiRequestUrl(String path, boolean auth, Object... params) throws FoursquareApiException {
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

    return urlBuilder.toString();
  }

  /**
   * Handles normal API request response
   *
   * @param response raw response
   * @return ApiRequestResponse
   * @throws JSONException when JSON parsing error occurs
   */
  private ApiRequestResponse handleApiResponse(Response response) throws JSONException {
    JSONObject responseJson = null;
    JSONArray notificationsJson = null;
    String errorDetail = null;

    if (response.getResponseCode() == 200) {
      JSONObject responseObject = new JSONObject(response.getResponseContent());
      responseJson = responseObject.getJSONObject("response");
      notificationsJson = responseObject.optJSONArray("notifications");
    } else {
      errorDetail = response.getMessage();
    }

    return new ApiRequestResponse(new ResultMeta(response.getResponseCode(), "", errorDetail, response.getResponseHeaderRateLimit(), response.getResponseHeaderRateLimitRemaining()), responseJson, notificationsJson);
  }

  /**
   * Handles callback API request response
   *
   * @param response raw response
   * @return ApiRequestResponse
   * @throws JSONException when JSON parsing error occurs
   */
  private ApiRequestResponse handleCallbackApiResponse(Response response) throws JSONException {
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

      return new ApiRequestResponse(new ResultMeta(code, errorType, errorDetail, response.getResponseHeaderRateLimit(), response.getResponseHeaderRateLimitRemaining()), responseJson, notificationsJson);
    } else {
      return new ApiRequestResponse(new ResultMeta(response.getResponseCode(), "", response.getMessage(), response.getResponseHeaderRateLimit(), response.getResponseHeaderRateLimitRemaining()), null, null);
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

  /**
   * Class that holds API request response
   *
   * @author Antti Leppä / Blake Dy
   */
  private class ApiRequestResponse {

    /**
     * Constructor
     *
     * @param meta status information
     * @param response response JSON Object
     * @param notifications notifications JSON Object
     * @throws JSONException when JSON parsing error occurs
     */
    public ApiRequestResponse(ResultMeta meta, JSONObject response, JSONArray notifications) throws JSONException {
      this.meta = meta;
      this.response = response;
      this.notifications = notifications;
    }

    /**
     * Returns response JSON Object
     *
     * @return response JSON Object
     */
    public JSONObject getResponse() {
      return response;
    }

    /**
     * Returns notifications JSON Object
     *
     * @return notifications JSON Object
     */
    public JSONArray getNotifications() {
      return notifications;
    }

    /**
     * Returns status information
     *
     * @return status information
     */
    public ResultMeta getMeta() {
      return meta;
    }

    private JSONObject response;
    private JSONArray notifications;
    private ResultMeta meta;
  }
}

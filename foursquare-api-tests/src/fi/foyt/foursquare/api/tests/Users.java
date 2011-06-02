package fi.foyt.foursquare.api.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import fi.foyt.foursquare.api.FoursquareApi;
import fi.foyt.foursquare.api.FoursquareApiException;
import fi.foyt.foursquare.api.entities.Checkin;
import fi.foyt.foursquare.api.entities.CheckinGroup;
import fi.foyt.foursquare.api.entities.CompactUser;
import fi.foyt.foursquare.api.entities.CompleteUser;
import fi.foyt.foursquare.api.entities.FriendGroup;
import fi.foyt.foursquare.api.entities.FriendGroups;
import fi.foyt.foursquare.api.entities.UserGroup;

public class Users {

  @Test
  public final void testUser() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthorizedFoursquareApi();
    CompleteUser user = foursquareApi.user("self").getResult();
    assertEquals("7613255", user.getId());
    assertEquals("Foyt", user.getFirstName());
    assertEquals("Development", user.getLastName());
    assertEquals("https://foursquare.com/img/blank_boy.png", user.getPhoto());
    assertEquals("none", user.getGender());
    assertEquals("Mikkeli, Suomi", user.getHomeCity());
    assertEquals("self", user.getRelationship());
    assertEquals("user", user.getType());
    assertEquals(false, user.getPings());
    assertEquals("development@foyt.fi", user.getContact().getEmail());
    assertEquals("100002112406948", user.getContact().getFacebook());
    assertEquals(new Long(1), user.getBadges().getCount());
    assertEquals(new Long(0), user.getMayorships().getCount());
    assertEquals(new Long(9), user.getCheckins().getCount());
    assertEquals(new Long(0), user.getFollowing().getCount());
    assertEquals(new Long(0), user.getRequests().getCount());
    assertEquals(new Long(0), user.getTips().getCount());
    assertEquals(new Long(0), user.getTodos().getCount());
    assertEquals(new Long(35), user.getScores().getRecent());
    assertEquals(new Long(35), user.getScores().getMax());
    assertEquals(new Long(50), user.getScores().getGoal());
    assertEquals(new Long(3), user.getScores().getCheckinsCount());

    FriendGroups friends = user.getFriends();

    assertEquals(new Long(2), friends.getCount());
    FriendGroup friendsGroup = friends.getGroups()[0];

    assertEquals(new Long(2), friendsGroup.getCount());
    assertEquals("others", friendsGroup.getType());
    assertEquals("other friends", friendsGroup.getName());
  }

  @Test
  public final void testUserBrand() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthorizedFoursquareApi();
    CompleteUser user = foursquareApi.user("1504602").getResult();
    assertEquals("1504602", user.getId());
    assertEquals("Mashable", user.getFirstName());
    assertEquals("https://playfoursquare.s3.amazonaws.com/userpix_thumbs/1ARKWZ4Q1IIIFS5D.png", user.getPhoto());
    assertEquals("none", user.getGender());
    assertEquals("New York City, NY", user.getHomeCity());
    assertEquals("followingThem", user.getRelationship());
    assertEquals("brand", user.getType());
    assertEquals(true, user.getPings());
    assertEquals("mashable", user.getContact().getTwitter());
    assertEquals(new Long(0), user.getBadges().getCount());
    assertEquals(new Long(0), user.getMayorships().getCount());
    assertEquals(new Long(71), user.getCheckins().getCount());
    assertEquals(new Long(0), user.getFollowing().getCount());
    assertEquals(new Long(93702), user.getFollowers().getCount());
    assertEquals(new Long(110), user.getTips().getCount());
    assertEquals(new Long(0), user.getTodos().getCount());
    assertEquals(new Long(0), user.getScores().getRecent());
    assertEquals(new Long(0), user.getScores().getMax());
    assertEquals(new Long(50), user.getScores().getGoal());
    assertEquals(new Long(0), user.getScores().getCheckinsCount());

    FriendGroups friends = user.getFriends();
    FriendGroup mutualFriendGroup = friends.getGroups()[0];
    FriendGroup otherFriendGroup = friends.getGroups()[1];

    assertEquals(new Long(93702), otherFriendGroup.getCount());
    assertEquals("others", otherFriendGroup.getType());
    assertEquals("other friends", otherFriendGroup.getName());

    assertEquals(new Long(0), mutualFriendGroup.getCount());
    assertEquals("friends", mutualFriendGroup.getType());
    assertEquals("mutual friends", mutualFriendGroup.getName());
  }

  @Test
  public final void testUsersCheckins() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthorizedFoursquareApi();
    CheckinGroup checkins = foursquareApi.usersCheckins("self", null, null, null, null).getResult();
    assertEquals(new Long(6), checkins.getCount());
    Checkin checkin = checkins.getItems()[0];
    assertEquals("4de3212d2271bfb844acdf5d", checkin.getId());
    assertEquals(new Long(1306730797), checkin.getCreatedAt());
    assertEquals(true, checkin.isPrivate());
    assertEquals("Europe/Helsinki", checkin.getTimeZone());
    assertEquals("4c6bbfafa48420a1b09a0a0b", checkin.getVenue().getId());
  }

  @Test
  public final void testUsersSearch() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthorizedFoursquareApi();
    CompactUser[] users = foursquareApi.usersSearch(null, null, "naveen", null, null, null).getResult();
    assertEquals(1, users.length);
    assertEquals("33", users[0].getId());
    assertEquals("Naveen", users[0].getFirstName());
    assertEquals("https://playfoursquare.s3.amazonaws.com/userpix_thumbs/YSTILJBL51CMM024.jpg", users[0].getPhoto());
    assertEquals("male", users[0].getGender());
    assertEquals("New York, NY", users[0].getHomeCity());
  }

  @Test
  public final void testUsersFriends() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthorizedFoursquareApi();
    UserGroup users = foursquareApi.usersFriends("self").getResult();
    assertEquals(new Long(2), users.getCount());
    CompactUser user1 = users.getItems()[0];

    assertEquals("7613255", user1.getId());
    assertEquals("Foyt", user1.getFirstName());
    assertEquals("Development", user1.getLastName());
    assertEquals("https://foursquare.com/img/blank_boy.png", user1.getPhoto());
    assertEquals("none", user1.getGender());
    assertEquals("Mikkeli, Suomi", user1.getHomeCity());
    assertEquals("friend", user1.getRelationship());

    CompactUser user2 = users.getItems()[1];

    assertEquals("1504602", user2.getId());
    assertEquals("Mashable", user2.getFirstName());
    assertEquals("https://playfoursquare.s3.amazonaws.com/userpix_thumbs/1ARKWZ4Q1IIIFS5D.png", user2.getPhoto());
    assertEquals("none", user2.getGender());
    assertEquals("New York City, NY", user2.getHomeCity());
    assertEquals("followingThem", user2.getRelationship());
  }

  @Test
  public final void testUsersRequest() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthorizedFoursquareApi();
    CompleteUser user = foursquareApi.usersRequest("7613255").getResult();
    assertEquals("7613255", user.getId());
  }

  @Test
  public final void testUsersApprove() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthorizedFoursquareApi();
    CompleteUser user = foursquareApi.usersApprove("10078668").getResult();
    assertEquals("10078668", user.getId());
  }

  @Test
  public final void testUsersDeny() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthorizedFoursquareApi();
    CompleteUser user = foursquareApi.usersDeny("10078668").getResult();
    assertEquals("10078668", user.getId());
  }

  @Test
  public final void testUsersUnfriend() throws FoursquareApiException {
    FoursquareApi foursquareApi = TestUtils.getAuthorizedFoursquareApi();
    CompleteUser user = foursquareApi.usersUnfriend("7613255").getResult();
    assertEquals("7613255", user.getId());
  }

}
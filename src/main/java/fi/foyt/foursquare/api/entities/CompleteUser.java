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

package fi.foyt.foursquare.api.entities;

/**
 * Class representing CompleteUser entity
 * 
 * @see <a href="https://developer.foursquare.com/docs/responses/user.html" target="_blank">https://developer.foursquare.com/docs/responses/user.html</a>
 * 
 * @author Antti Leppä
 */
public class CompleteUser extends CompactUser {

  private static final long serialVersionUID = 8689854240168137995L;

  /**
   * Returns type of the user. One of brand, celebrity or user.
   * 
   * @return type of the user
   */
  public String getType() {
    return type;
  }

  /**
   * Returns user's contact information
   * 
   * @return user's contact information
   */
  public Contact getContact() {
    return contact;
  }

  /**
   * Returns whether user will receive pings from this user
   * 
   * @return whether user will receive pings from this user
   */
  public Boolean getPings() {
    return pings;
  }

  /**
   * Returns count of badges for the user
   * 
   * @return count of badges for the user
   */
  public Count getBadges() {
    return badges;
  }

  /**
   * Returns the count of checkins by this user. Contains most recent checkin as an array of items, if the user is a friend.
   * 
   * @return checkins by this user
   */
  public CheckinGroup getCheckins() {
    return checkins;
  }

  /**
   * Returns the count of mayorships by this user. Contains selected mayorships as an array of items, if the user is a friend.
   * 
   * @return mayorships by this user
   */
  public VenueGroup getMayorships() {
    return mayorships;
  }

  /**
   * Returns for count of tips by this user. Contains selected tips as an array of items, if the user is friend.
   * 
   * @return tips by this user.
   */
  public TipGroup getTips() {
    return tips;
  }

  /**
   * Returns for count of todos by this user. Contains selected todos as an array of items, if the user is friend.
   * 
   * @return todos by this user
   */
  public TodoGroup getTodos() {
    return todos;
  }

  /**
   * Returns users for this friend
   * 
   * @return users for this friend
   */
  public UserGroups getFriends() {
    return friends;
  }

  /**
   * Returns count of followers for this user
   * 
   * @return count of followers for this user
   */
  public Count getFollowers() {
    return followers;
  }
  
  /**
   * Returns count how may users this user follows
   * 
   * @return count how may users this user follows
   */
  public Count getFollowing() {
    return following;
  }
  
  /**
   * Returns friend request count for this user
   * 
   * @return friend request count for this user
   */
  public Count getRequests() {
    return requests;
  }
  
  /**
   * Returns user's scores
   * 
   * @return user's scores
   */
  public Scores getScores() {
    return scores;
  }

  private String type;
  private Contact contact;
  private Boolean pings;
  private Count badges;
  private CheckinGroup checkins;
  private VenueGroup mayorships;
  private TipGroup tips;
  private TodoGroup todos;
  private UserGroups friends;
  private Count followers;
  private Count following;
  private Count requests;
  private Scores scores;
}

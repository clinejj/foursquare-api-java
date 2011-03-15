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

package fi.foyt.foursquare.api.entities;

public class CompleteUser extends CompactUser {

  public String getType() {
    return type;
  }

  public Contact getContact() {
    return contact;
  }

  public Boolean getPings() {
    return pings;
  }

  public Count getBadges() {
    return badges;
  }

  public CheckinGroup getCheckins() {
    return checkins;
  }

  public VenueGroup getMayorships() {
    return mayorships;
  }

  public Count getTips() {
    return tips;
  }

  public Count getTodos() {
    return todos;
  }

  public Count getFriends() {
    return friends;
  }

  public Count getFollowers() {
    return followers;
  }

  public Count getRequests() {
    return requests;
  }

  private String type;
  private Contact contact;
  private Boolean pings;
  private Count badges;
  private CheckinGroup checkins;
  private VenueGroup mayorships;
  private Count tips;
  private Count todos;
  private Count friends;
  private Count followers;
  private Count requests;
}

/*
 * FoursquareAPI - Foursquare API for Java
 * Copyright (C) 2008 - 2011 Antti Leppä / Foyt
 * http://www.foyt.fi
 * Copyright (C) 2014 - Blake Dy / Wallaby
 * http://walla.by
 * 
 * License: 
 * 
 * Licensed under GNU Lesser General Public License Version 3 or later (the "LGPL")
 * http://www.gnu.org/licenses/lgpl.html
 */

package fi.foyt.foursquare.api.entities;

import fi.foyt.foursquare.api.FoursquareEntity;

/**
 * Class representing User entity
 * 
 * @see <a href="https://developer.foursquare.com/docs/responses/user.html" target="_blank">https://developer.foursquare.com/docs/responses/user.html</a>
 * 
 * @author Antti Leppä / Blake Dy
 */
public class CompactUser implements FoursquareEntity {

  private static final long serialVersionUID = 477096997911461087L;

  /**
   * Returns user's id
   * 
   * @return user's id
   */
  public String getId() {
    return id;
  }
  
  /**
   * Returns user's first name
   * 
   * @return user's first name
   */
  public String getFirstName() {
    return firstName;
  }
  
  /**
   * Returns user's last name
   * 
   * @return user's last name
   */
  public String getLastName() {
    return lastName;
  }
  
  /**
   * Returns user's home city
   * 
   * @return user's home city
   */
  public String getHomeCity() {
    return homeCity;
  }
  
  /**
   * Returns URL of a profile picture for this user.
   * 
   * @return URL of a profile picture for this user.
   */
  public String getPhoto() {
    return photo;
  }
  
  /**
   * Returns male or female
   * 
   * @return male or female
   */
  public String getGender() {
    return gender;
  }
  
  /**
   * The relationship of the acting user to this user. One of self, friend, pendingMe, pendingThem or followingThem 
   * 
   * @return relationship of the acting user to this user
   */
  public String getRelationship() {
    return relationship;
  }
  
  protected String id;
  protected String firstName;
  protected String lastName;
  protected String homeCity;
  protected String photo;
  protected String gender;
  protected String relationship;
  
  // TODO
  protected String type;

  @Override
  public String toString() {
    return "CompactUser{" +
            "id='" + id + '\'' +
            ", firstName='" + firstName + '\'' +
            ", lastName='" + lastName + '\'' +
            ", homeCity='" + homeCity + '\'' +
            ", photo='" + photo + '\'' +
            ", gender='" + gender + '\'' +
            ", relationship='" + relationship + '\'' +
            ", type='" + type + '\'' +
            '}';
  }
}

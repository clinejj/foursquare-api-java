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

import fi.foyt.foursquare.api.FoursquareEntity;

/**
 * Class representing CompleteSpecial entity
 * 
 * @see <a href="https://developer.foursquare.com/docs/responses/special.html" target="_blank">https://developer.foursquare.com/docs/responses/special.html</a>
 * 
 * @author Antti Leppä
 */
public class CompleteSpecial implements FoursquareEntity {
  
  private static final long serialVersionUID = 3919231353933056022L;
  
  /**
   * Returns id of this venue
   * 
   * @return id of this venue
   */
  public String getId() {
    return id;
  }

  /**
   * Returns type of the venue. One of mayor, count, frequency, regular, friends, swarm, flash or other.
   * 
   * @return type of the venue
   */
  public String getType() {
    return type;
  }

  /**
   * Returns a message describing the special.
   * @return a message describing the special
   */
  public String getMessage() {
    return message;
  }
  
  /**
   * Returns specific rules for this special.
   * @return specific rules for this special.
   */
  public String getFinePrint() {
    return finePrint;
  }

  /**
   * Returns a description of how to unlock the special.
   * 
   * @return description of how to unlock the special.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Returns true or false indicating if this special is unlocked for the acting user.
   * 
   * @return true or false indicating if this special is unlocked for the acting user.
   */
  public Boolean getUnlocked() {
    return unlocked;
  }
  
  /**
   * Returns the name of the icon to use: http://foursquare.com/img/specials/<b>icon</b>.png
   * 
   * @return the name of the icon
   */
  public String getIcon() {
    return icon;
  }
  
  /**
   * Returns a title of the special
   * 
   * @return a title of the special
   */
  public String getTitle() {
    return title;
  }
  
  /**
   * Returns state. One of unlocked, before start, in progress, taken, locked
   * 
   * @return state
   */
  public String getState() {
    return state;
  }
  
  /**
   * Returns provider
   * 
   * @return provider
   */
  public String getProvider() {
    return provider;
  }
 
  /**
   * Returns redemption
   * 
   * @return redemption
   */
  public String getRedemption() {
    return redemption;
  }
  
  /**
   * Returns venue 
   * 
   * @return venue 
   */
  public CompactVenue getVenue() {
    return venue;
  }
  
  private String id;
  private String type;
  private String message;
  private String finePrint;
  private String description;
  private Boolean unlocked;
  private String icon;
  private String title;
  private String state;
  private String provider;
  private String redemption;
  private CompactVenue venue;
}

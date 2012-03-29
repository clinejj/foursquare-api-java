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
 * Class representing CompactVenue entity
 * 
 * @see <a href="https://developer.foursquare.com/docs/responses/venue.html" target="_blank">https://developer.foursquare.com/docs/responses/venue.html</a>
 * 
 * @author Antti Leppä
 */
public class CompactVenue implements FoursquareEntity {
  
  private static final long serialVersionUID = -7714811839778109046L;
  
  /**
   * Returns id of the venue
   * 
   * @return id of the venue
   */
  public String getId() {
    return id;
  }

  /**
   * Returns name of the venue
   * 
   * @return name of the venue
   */
  public String getName() {
    return name;
  }

  /**
   * Returns true if this venue has been verified
   * 
   * @return true if this venue has been verified
   */
  public Boolean getVerified() {
    return verified;
  }

  /**
   * Returns contact information for the venue
   * 
   * @return contact information for the venue
   */
  public Contact getContact() {
    return contact;
  }

  /**
   * Returns venue's contact information
   *  
   * @return venue's contact information
   */
  public Location getLocation() {
    return location;
  }

  /**
   * Returns array of venue's categories
   *  
   * @return array of venue's categories
   */
  public Category[] getCategories() {
    return categories;
  }

  /**
   * Returns array of specials at this venue
   * @return array of specials at this venue
   */
  public CompleteSpecial[] getSpecials() {
    return specials;
  }

  /**
   * Returns information about who is here now
   * 
   * @return information about who is here now
   */
  public HereNow getHereNow() {
    return hereNow;
  }
  
  /**
   * Returns statistical information about this venue
   * 
   * @return statistical information about this venue
   */
  public Stats getStats() {
    return stats;
  }
  
  /**
   * Returns url for this venue
   * 
   * @return url for this venue
   */
  public String getUrl() {
    return url;
  }

  /**
   * Returns tips for the venue
   * 
   * @return tips for the venue
   */
  public Tips getTips() {
    return tips;
  }

  /**
   * Returns todos for the venue
   * 
   * @return todos for the venue
   */
  public TodoGroup getTodos() {
    return todos;
  }

  private String id;
  private String name;
  private Boolean verified;
  private Contact contact;
  private Location location;
  private Category[] categories;
  private CompleteSpecial[] specials;
  private HereNow hereNow;
  private Stats stats;
  private String url;
  private Tips tips;
  private TodoGroup todos;
}

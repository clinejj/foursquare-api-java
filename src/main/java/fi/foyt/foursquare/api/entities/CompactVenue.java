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
import fi.foyt.foursquare.api.entities.venue.*;

/**
 * Class representing CompactVenue entity
 * 
 * @see <a href="https://developer.foursquare.com/docs/responses/venue.html" target="_blank">https://developer.foursquare.com/docs/responses/venue.html</a>
 * 
 * @author Antti Leppä / Blake Dy
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
   * Returns true if this venue has been verified
   * 
   * @return true if this venue has been verified
   */
  public Boolean getVerified() {
    return verified;
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
   * Returns the hours during the week that the venue is open along with any named hours segments in a human-readable format.
   * 
   * @return the hours during the week that the venue is open along with any named hours segments in a human-readable format.
   */
  public Hours getHours() {
    return hours;
  }
  
  /**
   * Returns the hours during the week when people usually go to the venue.
   * 
   * @return the hours during the week when people usually go to the venue.
   */
  public Hours getPopular() {
    return popular;
  }
  
  /**
   * Returns an object containing url and mobileUrl that display the menu information for this venue.
   * 
   * @return an object containing url and mobileUrl that display the menu information for this venue.
   */
  public Menu getMenu() {
    return menu;
  }
  
  /**
   * Returns an object containing the price tier from 1 (least pricey) - 4 (most pricey) and a message describing the price tier.
   * 
   * @return an object containing the price tier from 1 (least pricey) - 4 (most pricey) and a message describing the price tier.
   */
  public Price getPrice() {
    return price;
  }
  
  /**
   * Returns a numerical rating of the venue (0 through 10).
   * 
   * @return a numerical rating of the venue (0 through 10).
   */
  public Integer getRating() {
    return rating;
  }

  /**
   * Returns array of specials at this venue
   * @return array of specials at this venue
   */
  public SpecialGroup getSpecials() {
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

  private String id;
  private String name;
  private Contact contact;
  private Location location;
  private Category[] categories;
  private Boolean verified;
  private Stats stats;
  private String url;
  private Hours hours;
  private Hours popular;
  private Menu menu;
  private Price price;
  private Integer rating;
  private SpecialGroup specials;
  private HereNow hereNow;
  
  // TODO
  private String page;
}

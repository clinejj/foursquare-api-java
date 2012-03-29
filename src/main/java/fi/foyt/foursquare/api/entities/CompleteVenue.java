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
 * Class representing CompleteVenue entity
 * 
 * @see <a href="https://developer.foursquare.com/docs/responses/venue.html" target="_blank">https://developer.foursquare.com/docs/responses/venue.html</a>
 * 
 * @author Antti Leppä
 */
public class CompleteVenue extends CompactVenue {

  private static final long serialVersionUID = -3318179465794411655L;

  /**
   * Returns description of the venue provided by venue owner. 
   * 
   * @return description of the venue provided by venue owner.
   */
  public String getDescription() {
    return description;
  }
  
  /**
   * Returns mayor of this venue
   * 
   * @return mayor of this venue
   */
  public Mayor getMayor() {
    return mayor;
  }
  
  /**
   * Returns venue tags
   * 
   * @return venue tags
   */
  public String[] getTags() {
    return tags;
  }
  
  /**
   * Returns count of the number of times the acting user has been here.
   * 
   * @return count of the number of times the acting user has been here.
   */
  public Count getBeenHere() {
    return beenHere;
  }
  
  /**
   * Returns a short URL for this venue, e.g. http://4sq.com/Ab123D
   * 
   * @return a short URL for this venue, e.g. http://4sq.com/Ab123D
   */
  public String getShortUrl() {
    return shortUrl;
  }
  
  /**
   * Returns an array of specials near this venue. 
   * 
   * @return an array of specials near this venue.
   */
  public CompleteSpecial[] getSpecialsNearby() {
    return specialsNearby;
  }
  
  /**
   * Returns photos for this venue
   * 
   * @return photos for this venue
   */
  public Photos getPhotos() {
    return photos;
  }
  
  /**
   * Returns time zone
   * 
   * @return time zone
   */
  public String getTimeZone() {
    return timeZone;
  }

  private String description;
  private Mayor mayor;
  private String[] tags;
  private Count beenHere;
  private String shortUrl;
  private CompleteSpecial[] specialsNearby;
  private Photos photos;
  private String timeZone;
}

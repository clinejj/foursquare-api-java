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

/**
 * Class representing CompleteVenue entity
 * 
 * @see <a href="https://developer.foursquare.com/docs/responses/venue.html" target="_blank">https://developer.foursquare.com/docs/responses/venue.html</a>
 * 
 * @author Antti Leppä / Blake Dy
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
   * Returns seconds since epoch when the venue was created.
   * 
   * @return seconds since epoch when the venue was created.
   */
  public Long getCreatedAt() {
    return createdAt;
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
   * Returns tips for the venue
   * 
   * @return tips for the venue
   */
  public Tips getTips() {
    return tips;
  }
  
  /**
   * Returns a grouped response of lists that contain this venue.
   * 
   * @return a grouped response of lists that contain this venue.
   */
  public ListGroups getListed() {
    return listed;
  }
  
  /**
   * Returns venue tags.
   * 
   * @return venue tags.
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
   * Returns the canonical URL for this venue, e.g. https://foursquare.com/v/foursquare-hq/4ab7e57cf964a5205f7b20e3
   * 
   * @return the canonical URL for this venue, e.g. https://foursquare.com/v/foursquare-hq/4ab7e57cf964a5205f7b20e3
   */
  public String getCanonicalUrl() {
    return canonicalUrl;
  }
  
  /**
   * Returns an array of specials near this venue. 
   * 
   * @return an array of specials near this venue.
   */
  public SpecialGroup getSpecialsNearby() {
    return specialsNearby;
  }
  
  /**
   * Returns a count and groups of photos for this venue.
   * 
   * @return a count and groups of photos for this venue.
   */
  public Photos getPhotos() {
    return photos;
  }
  
  /**
   * Returns the count of users who have liked this venue, and groups containing any friends and others who have liked it.
   * 
   * @return the count of users who have liked this venue, and groups containing any friends and others who have liked it.
   */
  public UserGroups getLikes() {
    return likes;
  }
  
  /**
   * Returns true if the current user has liked this venue.
   * 
   * @return true if the current user has liked this venue.
   */
  public Boolean getLike() {
    return like;
  }
  
  /**
   * Returns true if the current user has disliked this venue.
   * 
   * @return true if the current user has disliked this venue.
   */
  public Boolean getDislike() {
    return dislike;
  }
  
  /**
   * Returns time zone.
   * 
   * @return time zone.
   */
  public String getTimeZone() {
    return timeZone;
  }

  private String description;
  private Long createdAt;
  private Mayor mayor;
  private Tips tips;
  private ListGroups listed;
  private String[] tags;
  private Count beenHere;
  private String shortUrl;
  private String canonicalUrl;
  private SpecialGroup specialsNearby;
  private Photos photos;
  private UserGroups likes;
  private Boolean like;
  private Boolean dislike;
  private String timeZone;
  
  // TODO
  private String reasons;
  private String flags;
  private String roles;
  private String restricted;
  private String pageUpdates;
  private String phrases;
  private String attributes;
  private String storeId;
  private String venuePage; // verify
}

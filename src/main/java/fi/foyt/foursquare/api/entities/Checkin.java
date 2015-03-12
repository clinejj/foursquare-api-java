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
 * Class representing Checkin entity
 * 
 * @see <a href="https://developer.foursquare.com/docs/responses/checkin.html" target="_blank">https://developer.foursquare.com/docs/responses/checkin.html</a>
 * 
 * @author Antti Leppä / Blake Dy
 */
public class Checkin implements FoursquareEntity {

  private static final long serialVersionUID = 4805612286360679516L;

  /**
   * Returns checkin id
   * 
   * @return checkin id
   */
  public String getId() {
    return id;
  }

  /**
   * Returns checkin type (checkin, shout, or venueless)
   * 
   * @return checkin type
   */
  public String getType() {
    return type;
  }
  
  /**
   * Returns is this a private checkin
   * 
   * @return is this a private checkin
   */
  public Boolean isPrivate() {
    return isPrivate;
  }
  
  /**
   * Returns user who checked in 
   * 
   * @return user who checked in 
   */
  public CompactUser getUser() {
    return user;
  }
  
  /**
   * Returns is the checked user mayor
   * 
   * @return is the checked user mayor
   */
  public Boolean getIsMayor() {
    return isMayor;
  }
  
  /**
   * Returns timezone of the checkin
   * 
   * @return timezone of the checkin
   */
  public String getTimeZone() {
    return timeZone;
  }
  
  /**
   * Returns timezone offset of the checkin
   * 
   * @return timezone offset of the checkin
   */
  public Integer getTimeZoneOffset() {
    return timeZoneOffset;
  }
  
  /**
   * Returns venue if any
   * 
   * @return venue if any
   */
  public CompactVenue getVenue() {
    return venue;
  }
  
  /**
   * Returns Location of the checkin. Field may be present if the type of this checkin is shout or venueless.
   * 
   * @return Location of the checkin
   */
  public Location getLocation() {
    return location;
  }
  
  /**
   * Returns shout text
   * 
   * @return shout text
   */
  public String getShout() {
    return shout;
  }
  
  /**
   * Returns when this checkin was created (epoch)
   * 
   * @return when this checkin was created
   */
  public Long getCreatedAt() {
    return createdAt;
  }
  
  /**
   * Returns photos of the checkin
   * 
   * @return photos of the checkin
   */
  public PhotoGroup getPhotos() {
    return photos;
  }
  
  /**
   * Returns comments for checkin
   * 
   * @return comments for checkin
   */
  public CommentGroup getComments() {
    return comments;
  }
  
  /**
   * Returns Source object containing application name and url that created this checkin
   * 
   * @return Checkin source
   */
  public Source getSource() {
    return source;
  }
  
  /**
   * Returns checkins from friends checked into the same venue around the same time.
   * 
   * @return Overlapping checkins
   */
  public CheckinGroup getOverlaps() {
    return overlaps;
  }
  
  /**
   * Setter method for "private" field. 
   * 
   * Method is needed because "private" is reserved word and thus normal field access can not be used to set field value
   * 
   * @param isPrivate field value
   */
  @SuppressWarnings("unused")
  private void setPrivate(Boolean isPrivate) {
    this.isPrivate = isPrivate;
  }
  
  private String id;
  private String type;
  private Boolean isPrivate;
  private CompactUser user;
  private Boolean isMayor;
  private String timeZone;
  private Integer timeZoneOffset;
  private CompleteVenue venue;
  private Location location;
  private String shout;
  private Long createdAt;
  private Source source;
  private PhotoGroup photos;
  private CommentGroup comments;
  private CheckinGroup overlaps;
  
  // TODO
  private String posts;
  private String score;
  private String likes;
  private String like;
  private String replies;
}

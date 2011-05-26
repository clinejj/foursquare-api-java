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

public class Checkin implements FoursquareEntity {

  private static final long serialVersionUID = 4805612286360679516L;

  public String getId() {
    return id;
  }
  
  public String getType() {
    return type;
  }
  
  public Boolean isPrivate() {
    return isPrivate;
  }
  
  public CompactUser getUser() {
    return user;
  }
  
  public String getTimeZone() {
    return timeZone;
  }
  public CompactVenue getVenue() {
    return venue;
  }
  
  public Location getLocation() {
    return location;
  }
  
  public String getShout() {
    return shout;
  }
  
  public Long getCreatedAt() {
    return createdAt;
  }
  
  public PhotoGroup getPhotos() {
    return photos;
  }
  
  public CommentGroup getComments() {
    return comments;
  }
  
  public Source getSource() {
    return source;
  }
  
  public CheckinGroup getOverlaps() {
    return overlaps;
  }
  
  @SuppressWarnings("unused")
  private void setPrivate(Boolean isPrivate) {
    this.isPrivate = isPrivate;
  }
  
  private String id;
  private String type;
  private Boolean isPrivate;
  private CompactUser user;
  private String timeZone;
  private CompleteVenue venue;
  private Location location;
  private String shout;
  private Long createdAt;
  private Source source;
  private PhotoGroup photos;
  private CommentGroup comments;
  private CheckinGroup overlaps;
}

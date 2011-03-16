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

import fi.foyt.foursquare.api.FoursquareEntity;

public class CompactTip extends FoursquareEntity {

  public String getId() {
    return id;
  }
  
  public String getText() {
    return text;
  }
  
  public Long getCreatedAt() {
    return createdAt;
  }
  
  public String getStatus() {
    return status;
  }
  
  public Photo getPhoto() {
    return photo;
  }
  
  public CompactUser getUser() {
    return user;
  }
  
  public CompactVenue getVenue() {
    return venue;
  }
  
  private String id;
  private String text;
  private Long createdAt;
  private String status;
  private Photo photo;
  private CompactUser user;
  private CompactVenue venue;
}

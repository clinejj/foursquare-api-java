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

public class Comment {

  public String getId() {
    return id;
  }
  
  public Long getCreatedAt() {
    return createdAt;
  }
  
  public CompactUser getUser() {
    return user;
  }
  
  public String getText() {
    return text;
  }
  
  private String id;
  private Long createdAt;
  private CompactUser user;
  private String text;
}

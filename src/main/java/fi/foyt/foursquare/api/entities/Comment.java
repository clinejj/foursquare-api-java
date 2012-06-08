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
 * Class representing Comment entity
 * 
 * @author Antti Leppä
 */
public class Comment implements FoursquareEntity {

  private static final long serialVersionUID = 6671734583007572548L;

  /**
   * Returns comment's id
   * 
   * @return comment's id
   */
  public String getId() {
    return id;
  }
  
  /**
   * Returns when this comment was created (epoch)
   * 
   * @return when this comment was created
   */
  public Long getCreatedAt() {
    return createdAt;
  }
  
  /**
   * Returns user that left the comment
   * 
   * @return user that left the comment
   */
  public CompactUser getUser() {
    return user;
  }
  
  /**
   * Returns comment's text
   * 
   * @return comment's text
   */
  public String getText() {
    return text;
  }
  
  private String id;
  private Long createdAt;
  private CompactUser user;
  private String text;
}

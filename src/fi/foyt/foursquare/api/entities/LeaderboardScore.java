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
 * Class representing LeaderboardScore entity
 * 
 * @author Antti Leppä
 */
public class LeaderboardScore implements FoursquareEntity {

  private static final long serialVersionUID = -8569003387365797941L;

  /**
   * Returns points 
   *   
   * @return points 
   */
  public Integer getPoints() {
    return points;
  }
  
  /**
   * Returns icon
   * 
   * @return icon
   */
  public String getIcon() {
    return icon;
  }
  
  /**
   * Returns a message
   * 
   * @return message
   */
  public String getMessage() {
    return message;
  }
  
  private Integer points;
  private String icon;
  private String message;
}
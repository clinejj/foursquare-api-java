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

public class LeaderboardScore implements FoursquareEntity {

  private static final long serialVersionUID = -8569003387365797941L;

  public Integer getPoints() {
    return points;
  }
  
  public String getIcon() {
    return icon;
  }
  
  public String getMessage() {
    return message;
  }
  
  private Integer points;
  private String icon;
  private String message;
}
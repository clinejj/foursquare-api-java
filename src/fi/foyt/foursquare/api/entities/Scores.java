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

public class Scores implements FoursquareEntity {

  private static final long serialVersionUID = -6977397860701200711L;

  public Long getRecent() {
    return recent;
  }
  
  public Long getMax() {
    return max;
  }
  
  public Long getGoal() {
    return goal;
  }
  
  public Long getCheckinsCount() {
    return checkinsCount;
  }
  
  private Long recent;
  private Long max;
  private Long goal;
  private Long checkinsCount;
}

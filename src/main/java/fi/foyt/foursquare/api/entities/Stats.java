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
 * Class representing Stats entity
 * 
 * @author Antti Leppä
 */
public class Stats implements FoursquareEntity {

  private static final long serialVersionUID = 1191621361079941540L;

  /**
   * Returns checkins count
   * 
   * @return checkins count
   */
  public Integer getCheckinsCount() {
    return checkinsCount;
  }
  
  /**
   * Returns users count
   * 
   * @return users count
   */
  public Integer getUsersCount() {
    return usersCount;
  }
  
  /**
   * Returns tip count
   * 
   * @return tip count
   */
  public Integer getTipCount() {
    return tipCount;
  }
  
  private Integer checkinsCount;
  private Integer usersCount;
  private Integer tipCount;
}

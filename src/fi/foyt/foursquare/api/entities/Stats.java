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

public class Stats implements FoursquareEntity {

  private static final long serialVersionUID = 1191621361079941540L;

  public Integer getCheckinsCount() {
    return checkinsCount;
  }
  
  public Integer getUsersCount() {
    return usersCount;
  }
  
  private Integer checkinsCount;
  private Integer usersCount;
}

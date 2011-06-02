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

public class BadgeImage implements FoursquareEntity {

  private static final long serialVersionUID = 3839275051310645747L;

  public String getPrefix() {
    return prefix;
  }
  
  public Integer[] getSizes() {
    return sizes;
  }
  
  public String getName() {
    return name;
  }
  
  private String prefix;
  private Integer[] sizes;
  private String name;
}
